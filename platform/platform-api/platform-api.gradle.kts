plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":core-api"))
}

java {
    sourceSets {
        "main" {
            java {
                include("com/intellij/openapi/ui/Painter.java")
                include("com/intellij/ui/components/JBScrollBar.java")
                include("com/intellij/ui/IdeBorderFactory.java")
                include("com/intellij/ui/RoundedLineBorder.java")
                include("com/intellij/ui/components/JBScrollPane.java")
                include("com/intellij/ui/components/ScrollSource.java")
                include("com/intellij/ui/components/Interpolator.java")
                include("com/intellij/ui/components/ScrollSettings.java")
                include("com/intellij/ui/components/ScrollColorProducer.java")
                include("com/intellij/ui/components/TwoWayAnimator.java")
                include("com/intellij/ui/components/ScrollPainter.java")
                include("com/intellij/ui/components/DefaultScrollBarUI.java")
                include("com/intellij/ui/components/Interpolable.java")
                include("com/intellij/ui/border/IdeaTitledBorder.java")
                include("com/intellij/ui/TitledSeparator.java")
                include("com/intellij/util/ui/DialogUtil.java")
                include("com/intellij/util/Alarm.java")
                include("com/intellij/util/ui/ButtonlessScrollBarUI.java")
                include("com/intellij/util/ui/Animator.java")
                include("com/intellij/util/ui/NSScrollerHelper.java")
                include("com/intellij/util/ui/IconCache.kt")
                include("com/intellij/openapi/wm/IdeGlassPane.java")
                include("com/intellij/openapi/application/ApplicationActivationListener.java")
                include("com/intellij/util/concurrency/QueueProcessor.java")
                include("com/intellij/util/ui/update/UiNotifyConnector.java")
                include("com/intellij/util/ui/update/Activatable.java")
                include("com/intellij/ui/ScreenUtil.java")
            }
        }
    }
}
