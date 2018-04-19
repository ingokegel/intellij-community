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
dir("platform-impl")
dir("util-rt")
dir("util")
dir("annotations:java5")
dir("annotations:common")