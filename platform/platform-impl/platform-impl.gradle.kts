plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":util"))
}

idea {
    module {
        excludeDirs = excludeDirs +
                file("src/com/intellij").listFiles { _, name -> name != "ide" } +
                file("src/com/intellij/ide").listFiles { _, name -> name != "ui" } +
                file("src/com/intellij/ide/ui").listFiles { _, name -> name != "laf" }
    }
}