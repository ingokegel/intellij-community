import org.gradle.plugins.ide.idea.model.IdeaModel

plugins {
    kotlin("jvm") version "1.2.31" apply false
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
    plugins.withType<JavaPlugin>().whenObjectAdded {
        the<JavaPluginConvention>().apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            sourceSets {
                "main" {
                    java {
                        srcDirs("src")
                    }
                }
            }
        }

        tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }
    plugins.withType<IdeaPlugin>().whenObjectAdded {
        the<IdeaModel>().apply {
            module {
                jdkName = "1.8"
            }
        }
    }
}