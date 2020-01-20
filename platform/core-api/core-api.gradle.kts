plugins {
    kotlin("jvm")
    idea
}

dependencies {
    api(project(":extensions"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.2.1")
    api("dk.brics:automaton:1.12-1")
}

sourceSets.main {
    java {
        java {
            srcDir("../util/concurrency")
            srcDir("../util-ex/src")
        }
    }
}
