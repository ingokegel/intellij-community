plugins {
    java
}

dependencies {
    compile(project(":extensions"))
    compile("dk.brics:automaton:1.12-1")
    compile(files("../../lib/cglib-nodep-3.2.4.jar"))
}