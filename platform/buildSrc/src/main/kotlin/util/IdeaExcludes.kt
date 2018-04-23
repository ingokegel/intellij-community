package util

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the
import org.gradle.plugins.ide.idea.model.IdeaModel
import java.io.File

@Suppress("unused")
fun IdeaModel.addExludesFromSourceSet() {
    apply {
        module {
            project.gradle.projectsEvaluated {
                val sourceSet = project.the<JavaPluginConvention>().sourceSets["main"].java
                val includes = sourceSet.includes
                if (includes.isNotEmpty()) {
                    excludeDirs = mutableSetOf<File>().apply {
                        val srcDir = "src"
                        val includeTree = buildIncludeTree(includes, srcDir)
                        addExcludes(includeTree, "$srcDir/", project)
                    } + sourceSet.excludes.map { project.file(it) }
                }
            }
        }
    }

}

private fun buildIncludeTree(includes: Set<String>, srcDir: String): FileTreeNode =
    FileTreeNode(srcDir).also { root ->
        includes.map { it.split("/") }.forEach {
            insertFileNode(it, root)
        }
    }

private tailrec fun insertFileNode(pathComponents: List<String>, node: FileTreeNode) {
    if (!pathComponents.isEmpty()) {
        val name = pathComponents.first()
        if (!name.startsWith("*")) {
            val childNode = node.children.getOrPut(name) { FileTreeNode(name) }
            insertFileNode(pathComponents.drop(1), childNode)
        }
    }
}

private fun MutableSet<File>.addExcludes(node: FileTreeNode, prefix: String, project: Project) {
    val file = project.file(prefix)
    addAll(file.listFiles { _, name ->
        node.children.keys.none { it == name }
    })

    for (child in node.children.values) {
        if (child.children.isNotEmpty()) {
            addExcludes(child, prefix + child.name + "/", project)
        }
    }
}

private class FileTreeNode(val name: String, val children: MutableMap<String, FileTreeNode> = mutableMapOf()) {
    @Suppress("Unused", "MemberVisibilityCanBePrivate")
    fun print(indent: Int = 0) {
        print("  ".repeat(indent))
        println(this)
        for (child in children.values) {
            child.print(indent + 1)
        }
    }

    override fun toString() = name
}

