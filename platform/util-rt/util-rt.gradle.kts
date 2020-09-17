plugins {
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(6))
    }
}

dependencies {
    implementation("org.jetbrains:annotations-java5:19.0.0")
    api(kotlin("reflect"))
}