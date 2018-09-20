plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(project(":core-api"))
    compile("com.fasterxml.jackson.core:jackson-databind:2.9.5")
}

sourceSets["main"].java {
    include(
        "com/intellij/openapi/ui/Painter.java",
        "com/intellij/ui/components/JBViewport.java",
        "com/intellij/ui/components/Magnificator.java",
        "com/intellij/ui/components/ZoomableViewport.java",
        "com/intellij/ui/components/ZoomingDelegate.java",
        "com/intellij/ui/components/JBScrollBar.java",
        "com/intellij/ui/components/MacScrollBarUI.java",
        "com/intellij/ui/IdeBorderFactory.java",
        "com/intellij/ui/RoundedLineBorder.java",
        "com/intellij/ui/components/JBScrollPane.java",
        "com/intellij/ui/components/ScrollSource.java",
        "com/intellij/ui/components/Interpolator.java",
        "com/intellij/ui/components/ScrollSettings.java",
        "com/intellij/ui/components/ScrollColorProducer.java",
        "com/intellij/ui/components/TwoWayAnimator.java",
        "com/intellij/ui/components/ScrollPainter.java",
        "com/intellij/ui/components/DefaultScrollBarUI.java",
        "com/intellij/ui/components/Interpolable.java",
        "com/intellij/ui/border/IdeaTitledBorder.java",
        "com/intellij/ui/TitledSeparator.java",
        "com/intellij/util/ui/DialogUtil.java",
        "com/intellij/util/ui/ComponentWithEmptyText.java",
        "com/intellij/util/Alarm.java",
        "com/intellij/util/ui/ButtonlessScrollBarUI.java",
        "com/intellij/util/ui/Animator.java",
        "com/intellij/util/ui/NSScrollerHelper.java",
        "com/intellij/util/ui/LafIconLookup.kt",
        "com/intellij/openapi/wm/IdeGlassPane.java",
        "com/intellij/openapi/application/ApplicationActivationListener.java",
        "com/intellij/util/concurrency/QueueProcessor.java",
        "com/intellij/util/ui/update/UiNotifyConnector.java",
        "com/intellij/util/ui/update/Activatable.java",
        "com/intellij/ui/ScreenUtil.java",
        "com/intellij/ide/ui/UITheme.java",
        "com/intellij/ide/ui/UIThemeProvider.java"
    )
}
