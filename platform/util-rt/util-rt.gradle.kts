plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

dependencies {
    implementation("org.jetbrains:annotations-java5:19.0.0")
    api(kotlin("reflect"))
}