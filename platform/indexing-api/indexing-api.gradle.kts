// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
plugins {
  kotlin("jvm")
}

dependencies {
  compile(project(":core-api"))
  compile(project(":projectModel-api"))
  compile("be.cyberelf.nanoxml:nanoxml:2.2.3")
}