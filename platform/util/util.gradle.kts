plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":util-rt"))
    compile("org.jdom:jdom:1.1.3")
    compile("log4j:log4j:1.2.17")
    compile("org.jetbrains.intellij.deps:trove4j:1.0.20160824")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}
