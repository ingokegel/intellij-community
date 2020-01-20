plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":util-rt"))
    api("org.jetbrains.intellij.deps:trove4j:1.0.20160824")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}
