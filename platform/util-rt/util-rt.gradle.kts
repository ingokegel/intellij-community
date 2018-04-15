plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

dependencies {
    compile(project(":annotations:java5"))
    compile(project(":annotations:common"))
    compile(kotlin("reflect"))
}