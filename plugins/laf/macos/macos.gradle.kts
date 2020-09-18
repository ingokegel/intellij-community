plugins {
    kotlin("jvm")
}

val distDir: File by project

dependencies {
    api(project(":platform-impl"))
}

tasks {
    named<Copy>("processResources") {
        from("resources") {
            include("icons/**")
            exclude("icons/icon-robots.txt")
        }
        from("src") {
            include("com/intellij/laf/macos/macintellijlaf.properties")
        }
    }

    register<Copy>("dist") {
        from(jar)
        into(distDir)
    }
}

sourceSets.main {
    java {
        exclude(
            "com/intellij/laf/macos/MacIntelliJOptionButtonUI.kt"
        )
    }
}
