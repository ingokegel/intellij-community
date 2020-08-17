plugins {
  kotlin("jvm")
}

dependencies {
  api(project(":platform-impl"))
}

tasks {
  named<Copy>("processResources") {
    exclude("META-INF/**")
  }

  register<Copy>("dist") {
    dependsOn(":prepareDist")
    from(jar)
    into("${project(":platform-impl").buildDir}/dist")
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
