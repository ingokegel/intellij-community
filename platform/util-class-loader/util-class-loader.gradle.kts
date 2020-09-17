plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":util-rt"))
    api("org.jetbrains.intellij.deps:trove4j:1.0.20160824")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(6))
    }
}
