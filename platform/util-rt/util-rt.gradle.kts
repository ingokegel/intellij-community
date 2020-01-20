plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

dependencies {
    api("org.jetbrains:annotations-java5:17.0.0")
    api(kotlin("reflect"))
}