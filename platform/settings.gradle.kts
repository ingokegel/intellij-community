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
dir("bootstrap")
dir("forms_rt")
dir("extensions")
dir("core-api")
dir("projectModel-api")
dir("platform-impl")
dir("util-rt")
dir("util")
dir("annotations:java5")
dir("annotations:common")
dir("jps:model-api", "../jps/model-api")