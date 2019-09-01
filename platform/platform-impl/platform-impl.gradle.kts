
plugins {
    kotlin("jvm")
    idea
}

defaultTasks = mutableListOf("dist")

dependencies {
    compile(project(":platform-api"))
    compile(project(":projectModel-api"))
    compile(project(":core-ui"))
    compile(project(":object-serializer"))
    compile("com.google.code.gson:gson:2.8.5")
    compileOnly("com.miglayout:miglayout-swing:5.1")
}

sourceSets.main {
    java {
        include("com/intellij/ide/ui/laf/**")
        exclude("com/intellij/ide/ui/laf/darcula/ui/DarculaJBPopupComboPopup.java")
        exclude(
                "com/intellij/ide/ui/laf/TempUIThemeBasedLookAndFeelInfo.java",
                "com/intellij/ide/ui/laf/darcula/ui/DarculaOptionButtonUI.kt",
                "com/intellij/ide/ui/laf/intellij/MacIntelliJOptionButtonUI.kt",
                "com/intellij/ide/ui/laf/intellij/WinIntelliJOptionButtonUI.kt",
                "com/intellij/ide/ui/laf/intellij/WinOnOffButtonUI.java"
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
                "com/intellij/ide/ui/UIThemeProvider.java"
        )
    }
}

tasks {

    val jar by existing(Jar::class)

    register<Copy>("dist") {
        dependsOn(jar)
        from(configurations.compile.map { compile ->
            compile.filterNot { file ->
                listOf(
                        "annotations",
                        "java5",
                        "jython",
                        "rhino",
                        "batik",
                        "xalan",
                        "xmlgraphics",
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


    named<Copy>("processResources") {
        duplicatesStrategy = DuplicatesStrategy.FAIL
        from("../platform-resources-en/src") {
            include("messages/IdeBundle.properties")
        }
        from("src") {
            include("**/com/intellij/ide/ui/laf/**/*.properties")
            include("**/com/intellij/ide/ui/laf/**/*.css")
            include("**/com/intellij/ide/ui/laf/icons/**")
        }
        from("../icons/src") {
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
