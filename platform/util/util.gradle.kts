plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":util-class-loader"))
    api("log4j:log4j:1.2.17")
    api("org.jetbrains.intellij.deps:java-compatibility:1.0.1")
    api("org.apache.commons:commons-compress:1.16.1")
    api("org.lz4:lz4-java:1.7.1")
    api("net.java.dev.jna:jna-platform:5.6.0")
    api("org.jetbrains.intellij.deps.batik:batik-transcoder:1.12.0-8")
    api("org.jetbrains.intellij.deps.batik:batik-codec:1.12.0-8")
    api("oro:oro:2.0.8")
    api("org.imgscalr:imgscalr-lib:4.2")
    api("org.jetbrains.intellij.deps:jdom:2.0.6")
    api(files("../../lib/eawtstub.jar"))
    api(kotlin("stdlib"))
    api("org.jetbrains:annotations:19.0.0")
    api("org.jetbrains.intellij.deps.fastutil:intellij-deps-fastutil:8.3.1-1")
}

sourceSets.main {
    java {
        srcDir("ui/src")
        srcDir("collections/src")
        srcDir("strings/src")
        srcDir("diagnostic/src")
        srcDir("text-matching/src")
    }
    resources {
        srcDir("resources")
    }
}
