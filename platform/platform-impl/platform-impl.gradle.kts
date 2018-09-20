import org.gradle.internal.impldep.org.bouncycastle.crypto.tls.BulkCipherAlgorithm.idea

plugins {
    kotlin("jvm")
    idea
}

defaultTasks = listOf("dist")

dependencies {
    compile(project(":platform-api"))
}

sourceSets["main"].java {
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

tasks {

    val jar by getting(Jar::class)

    create<Copy>("dist") {
        dependsOn(jar)
        from(configurations.compile.files.filterNot { file ->
            listOf(
                    "annotations",
                    "java5",
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


    named<Copy>("processResources") {
        from("src") {
            include("**/com/intellij/ide/ui/laf/**/*.properties")
            include("**/com/intellij/ide/ui/laf/**/*.css")
            include("**/com/intellij/ide/ui/laf/icons/**")
        }
        from("../icons/src") {
            include("darcula/*")
            include("nodes/*")
            include("mac/*")
            include("windows/*")
            include("general/*")
            include("fileTypes/*")
            include("actions/*")
            include("welcome/*")
            include("toolbarDecorator/*")
        }
        includeEmptyDirs = false
    }
}

