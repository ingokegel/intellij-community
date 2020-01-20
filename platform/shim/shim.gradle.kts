plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":util"))
    api(project(":extensions"))
    api(project(":core-api"))
    api(project(":editor-ui-api"))
    api(kotlin("stdlib"))
}