// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
plugins {
  kotlin("jvm")
}

dependencies {
  api(project(":core-api"))
  api(project(":projectModel-api"))
  api("be.cyberelf.nanoxml:nanoxml:2.2.3")
}