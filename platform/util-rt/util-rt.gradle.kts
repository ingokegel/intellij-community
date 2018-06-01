plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

dependencies {
    compile("org.jetbrains:annotations-java5:16.0.2")
    compile(kotlin("reflect"))
}