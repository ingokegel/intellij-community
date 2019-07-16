fun dir(path: String, dir: String? = null) {
    include(path)
    project(":$path").apply {
        buildFileName = projectDir.name + ".gradle.kts"
        if (dir != null) {
            projectDir = File(settingsDir, dir)
        }
    }
}

dir("shim")
dir("core-api")
dir("core-ui")
dir("editor-ui-api")
dir("extensions")
dir("indexing-api")
dir("jps:model-api", dir = "../jps/model-api")
dir("object-serializer")
dir("platform-api")
dir("platform-impl")
dir("projectModel-api")
dir("util-rt")
dir("util-class-loader")
dir("util")
