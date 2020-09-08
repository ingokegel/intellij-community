
plugins {
    kotlin("jvm")
    idea
}

defaultTasks = mutableListOf("dist-all")

dependencies {
    api(project(":platform-api"))
    api(project(":projectModel-api"))
    api(project(":core-ui"))
    api(project(":object-serializer"))
    api("com.google.code.gson:gson:2.8.5")
    compileOnly("com.miglayout:miglayout-swing:5.1")
    compileOnly("com.google.guava:guava:27.1-jre")
}

sourceSets.main {
    java {
        include("com/intellij/ide/ui/laf/**")
        exclude("com/intellij/ide/ui/laf/darcula/ui/DarculaJBPopupComboPopup.java")
        exclude(
                "com/intellij/ide/ui/laf/TempUIThemeBasedLookAndFeelInfo.java",
                "com/intellij/ide/ui/laf/darcula/ui/DarculaOptionButtonUI.kt"
        )

        include("com/intellij/ui/plaf/**")
        include(
                "com/intellij/ui/TableActions.java",
                "com/intellij/ui/WindowMoveListener.java",
                "com/intellij/ui/WindowMouseListener.java",
                "com/intellij/ui/WindowResizeListener.java",
                "com/intellij/ui/ColoredSideBorder.java",
                "com/intellij/ui/mac/MacPopupMenuUI.java",
                "com/intellij/ide/ui/UITheme.java",
                "com/intellij/ide/ui/UIThemeProvider.java",
                "com/intellij/ide/ui/LafProvider.java"
        )
    }
}

tasks {

    val jar by existing(Jar::class)

    val dist by registering(Copy::class) {
        dependsOn(":prepareDist")
        from(configurations.runtimeClasspath.map { compile ->
            compile.filterNot { file ->
                listOf(
                        "annotations",
                        "java5",
                        "jython",
                        "rhino",
                        "batik",
                        "xalan",
                        "xmlgraphics",
                        "intellij-deps-fastutil",
                        "xml-apis",
                        "jna",
                        "kotlin",
                        "jdom",
                        "gson"
                ).any { file.name.contains(it) }
            }
        })
        from(jar)
        into("$buildDir/dist")
    }

    register<Copy>("dist-all") {
        dependsOn(dist, ":plugins:laf:macos:dist", ":plugins:laf:win10:dist")
    }

    named<Copy>("processResources") {
        duplicatesStrategy = DuplicatesStrategy.FAIL
        from("../../plugins/laf/macos/resources") {
            include("icons/**")
            exclude("icons/icon-robots.txt")
        }
        from("../../plugins/laf/macos/src") {
            include("com/intellij/laf/macos/macintellijlaf.properties")
        }
        from("../../plugins/laf/win10/resources") {
            include("icons/**")
            exclude("icons/icon-robots.txt")
        }
        from("../../plugins/laf/win10/src") {
            include("com/intellij/laf/win10/win10intellijlaf.properties")
        }
        from("../platform-api/resources") {
            include("messages/IdeBundle.properties")
        }
        from("../editor-ui-api/resources") {
            include("messages/PlatformEditorBundle.properties")
        }
        from("../platform-resources/src") {
            include("themes/**")
        }
        from("src") {
            include("**/com/intellij/ide/ui/laf/**/*.properties")
            include("**/com/intellij/ide/ui/laf/**/*.css")
            include("**/com/intellij/ide/ui/laf/icons/**")
        }
        from("../icons/src") {
            include("icons/ide/*")
            include("darcula/*")
            include("nodes/*")
            include("mac/*")
            include("windows/*")
            include("general/*")
            include("fileTypes/*")
            include("actions/*")
            include("welcome/*")
            include("toolbarDecorator/*")
        }
        from("../icons/compatibilityResources") {
            include("mac/*")
            includeIconList("general/",
                    "bullet",
                    "configurableDefault",
                    "defaultKeymap",
                    "downloadPlugin",
                    "errorsInProgress",
                    "gearHover",
                    "hideWarnings",
                    "ijLogo",
                    "jdk",
                    "keyboardShortcut",
                    "keymap",
                    "macCorner",
                    "mdot-empty",
                    "mdot-white",
                    "mdot",
                    "mouseShortcut",
                    "passwordLock",
                    "pathVariables",
                    "pluginManager",
                    "progress",
                    "searchEverywhereGear",
                    "show_to_override",
                    "tab",
                    "settings",
                    "projectStructure",
                    "uninstallPlugin",
                    "webSettings"
            )
            includeIconList("actions/",
                    "addFacesSupport",
                    "allLeft",
                    "allRight",
                    "createPatch",
                    "erDiagram",
                    "fileStatus",
                    "findWhite",
                    "multicaret.svg",
                    "quickList",
                    "realIntentionOffBulb",
                    "showViewer"
            )
            includeIconList( "toolbarDecorator/",
                    "analyze"
            )
            includeIconList( "nodes/",
                    "disabledPointcut",
                    "newException",
                    "pluginUpdate",
                    "pointcut",
                    "ppWebLogo",
                    "treeDownArrow",
                    "treeRightArrow"
            )
            includeIconList( "fileTypes/",
                    "facelets",
                    "facesConfig",
                    "typeScript"
            )
        }
        includeEmptyDirs = false
    }
}

fun Copy.includeIconList(prefix: String, vararg names: String) {
    include(names.map { "$prefix$it*" })
}
