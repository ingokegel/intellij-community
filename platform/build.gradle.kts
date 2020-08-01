@file:Suppress("HasPlatformType")

import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.addExludesFromSourceSet

plugins {
    kotlin("jvm") version "1.3.70" apply false
}

val rootBuildDir = mkdir("build")

tasks {
    getByName<Wrapper>("wrapper") {
        gradleVersion = "6.5.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}

val prepareDist by tasks.creating

subprojects {
    buildDir = File(rootBuildDir, path.substring(1).replace(":", "/"))
    repositories {
        mavenCentral()
        maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }

    pluginManager.withPlugin("java") {
        configure<JavaPluginConvention> {
            sourceSets {
                "main" {
                    java {
                        srcDirs("src")
                    }
                }
            }
        }

        tasks.named("jar", Jar::class) {
            prepareDist.dependsOn(this)
        }

        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
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