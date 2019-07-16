// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":indexing-api"))
    compile(project(":core-ui"))
    compile("org.jetbrains:annotations:17.0.0")
}