// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":projectModel-api"))
    api(project(":core-api"))
}

sourceSets.main {
    java {
        include("stateProperties/**")
    }
    resources {
        srcDir("resources")
    }
}
