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
                include("com/intellij/openapi/project/DumbAware.java")
                include("com/intellij/openapi/project/DumbAwareRunnable.java")
            }
        }
    }
}
