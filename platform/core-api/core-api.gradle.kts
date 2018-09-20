plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":shim"))
}

sourceSets["main"].java {
    include("com/intellij/openapi/project/DumbAware.java")
    include("com/intellij/openapi/project/DumbAwareRunnable.java")
}
