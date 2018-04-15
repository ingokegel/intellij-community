plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":util-rt"))
    compile("log4j:log4j:1.2.17")
    compile("org.jetbrains.intellij.deps:trove4j:1.0.20160824")
    compile("org.apache.commons:commons-compress:1.16.1")
    compile("net.jpountz.lz4:lz4:1.3.0")
    compile("net.java.dev.jna:jna-platform:4.5.0")
    compile("org.apache.xmlgraphics:batik-all:1.9.1")
    compile("oro:oro:2.0.8")
    compile("org.imgscalr:imgscalr-lib:4.2")
    compile(files("../../lib/jdom.jar"))
    compile(files("../../lib/eawtstub.jar"))
    compile(files("../../lib/snappy-in-java-0.5.1.jar"))
    compile(kotlin("stdlib"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}
