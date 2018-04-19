plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":shim"))
}

idea {
    module {
        excludeDirs = excludeDirs +
                file("src/com/intellij").listFiles { _, name -> name !in listOf("ide", "ui") } +
                file("src/com/intellij/ide").listFiles { _, name -> name != "ui" } +
                file("src/com/intellij/ide/ui").listFiles { _, name -> name != "laf" } +
                file("src/com/intellij/ide/ui/laf/darcula/ui/DarculaOptionButtonUI.kt") +
                file("src/com/intellij/ui").listFiles { _, name -> name !in listOf("WindowMoveListener.java", "WindowMouseListener.java", "WindowResizeListener.java") }
    }
}