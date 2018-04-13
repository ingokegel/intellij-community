fun dir(path: String) {
    include(path)
    project(":$path").apply {
        buildFileName = projectDir.name + ".gradle.kts"
    }
}

dir("platform-impl")
dir("util-rt")
dir("util")
dir("annotations:java5")
dir("annotations:common")