plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":util"))
    compile(project(":extensions"))
    compile(project(":core-api"))
    compile(project(":editor-ui-api"))
    compile(kotlin("stdlib"))
}