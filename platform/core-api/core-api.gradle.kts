plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":shim"))
}

sourceSets.main {
    java {
        include("com/intellij/openapi/editor/colors/ColorKey.java")
        include("com/intellij/openapi/project/DumbAware.java")
        include("com/intellij/openapi/project/DumbAwareRunnable.java")
        include("com/intellij/util/concurrency/**")
    }
}
