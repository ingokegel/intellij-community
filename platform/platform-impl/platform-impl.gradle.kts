plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":shim"))
}

java {
    sourceSets {
        "main" {
            java {
                include("com/intellij/ide/ui/laf/**")
                exclude(
                        "com/intellij/ide/ui/laf/darcula/ui/DarculaOptionButtonUI.kt",
                        "com/intellij/ide/ui/laf/intellij/MacIntelliJOptionButtonUI.kt",
                        "com/intellij/ide/ui/laf/intellij/WinIntelliJOptionButtonUI.kt",
                        "com/intellij/ide/ui/laf/intellij/WinOnOffButtonUI.java"
                )

                include("com/intellij/ui/plaf/**")
                include(
                        "com/intellij/ui/WindowMoveListener.java",
                        "com/intellij/ui/WindowMouseListener.java",
                        "com/intellij/ui/WindowResizeListener.java",
                        "com/intellij/ui/ColoredSideBorder.java",
                        "com/intellij/ui/mac/MacPopupMenuUI.java"
                )
            }
        }
    }
}

tasks {

    val jar by getting(Jar::class)

    "dist"(Copy::class) {
        dependsOn(jar)
        from(configurations.compile.files.filterNot { file ->
            listOf(
                    "jython",
                    "rhino",
                    "batik",
                    "xalan",
                    "xmlgraphics",
                    "xml-apis",
                    "jna",
                    "kotlin"
            ).any { file.name.contains(it) }
        })
        from(jar)
        into("$buildDir/dist")
    }


    "processResources"(Copy::class) {
        from("src") {
            include("**/com/intellij/ide/ui/laf/*.properties")
            include("**/com/intellij/ide/ui/laf/*.css")
        }
        from("../icons/src") {
            include("darcula/*")
            include("nodes/*")
            include("general/*")
            include("fileTypes/*")
        }
        includeEmptyDirs = false
    }
}

idea {
    module {
        val sourceSet = java.sourceSets["main"].java
        excludeDirs = mutableSetOf<File>().apply {
            val srcDir = "src"
            val includeTree = buildIncludeTree(sourceSet.includes, srcDir)
            addExcludes(includeTree, "$srcDir/")
        } + sourceSet.excludes.map { file(it) }
    }
}

fun buildIncludeTree(includes: Set<String>, srcDir: String): FileTreeNode =
    FileTreeNode(srcDir).also { root ->
        includes.map { it.split("/") }.forEach {
            insertFileNode(it, root)
        }
    }

tailrec fun insertFileNode(pathComponents: List<String>, node: FileTreeNode) {
    if (!pathComponents.isEmpty()) {
        val name = pathComponents.first()
        if (!name.startsWith("*")) {
            val childNode = node.children.getOrPut(name) { FileTreeNode(name) }
            insertFileNode(pathComponents.drop(1), childNode)
        }
    }
}

fun MutableSet<File>.addExcludes(node: FileTreeNode, prefix: String) {
    val file = file(prefix)
    addAll(file.listFiles { _, name ->
        node.children.keys.none { it == name }
    })

    for (child in node.children.values) {
        if (child.children.isNotEmpty()) {
            addExcludes(child, prefix + child.name + "/")
        }
    }
}

class FileTreeNode(val name: String, val children: MutableMap<String, FileTreeNode> = mutableMapOf()) {
    fun print(indent: Int = 0) {
        print("  ".repeat(indent))
        println(this)
        for (child in children.values) {
            child.print(indent + 1)
        }
    }

    override fun toString() = name
}

