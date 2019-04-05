import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.addExludesFromSourceSet

plugins {
    kotlin("jvm") version "1.3.21" apply false
}

val rootBuildDir = mkdir("build")

tasks {
    getByName<Wrapper>("wrapper") {
        gradleVersion = "5.0"
        distributionType = Wrapper.DistributionType.ALL
    }
}

subprojects {
    buildDir = File(rootBuildDir, path.substring(1).replace(":", "/"))
    repositories {
        mavenCentral()
        maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
    pluginManager.withPlugin("java") {
        configure<JavaPluginConvention> {
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

        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        pluginManager.apply("idea")
        configure<IdeaModel> {
            module {
                jdkName = "1.8"
            }
            addExludesFromSourceSet()
        }
    }
}