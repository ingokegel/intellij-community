plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":util-class-loader"))
    api("log4j:log4j:1.2.17")
    api("org.jetbrains.intellij.deps:java-compatibility:1.0.1")
    api("org.apache.commons:commons-compress:1.16.1")
    api("net.jpountz.lz4:lz4:1.3.0")
    api("net.java.dev.jna:jna-platform:5.3.1")
    api("org.apache.xmlgraphics:batik-all:1.11")
    api("oro:oro:2.0.8")
    api("org.imgscalr:imgscalr-lib:4.2")
    api("org.jetbrains.intellij.deps:jdom:2.0.6")
    api(files("../../lib/eawtstub.jar"))
    api(files("../../lib/snappy-in-java-0.5.1.jar"))
    api(kotlin("stdlib"))
    api("org.jetbrains:annotations-java5:17.0.0")
}

sourceSets.main {
    java {
        srcDir("ui/src")
    }
    resources {
        srcDir("resources")
    }
}
