// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
plugins {
  kotlin("jvm")
  idea
}

dependencies {
  api(project(":core-api"))
  api(project(":util"))
}

tasks {
  named<Copy>("processResources") {
    from("resources")
  }
}
