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
            include("com/intellij/laf/win10/win10intellijlaf.properties")
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
            "com/intellij/laf/win10/WinIntelliJOptionButtonUI.kt",
            "com/intellij/laf/win10/WinOnOffButtonUI.java"
        )
    }
}
