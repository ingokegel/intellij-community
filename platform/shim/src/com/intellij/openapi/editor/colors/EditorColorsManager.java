package com.intellij.openapi.editor.colors;

public class EditorColorsManager {

    public static EditorColorsManager getInstance() {
        return new EditorColorsManager();
    }

    public EditorColorsScheme getGlobalScheme() {
        return new EditorColorsScheme();
    }

    public EditorColorsScheme getScheme(String themeName) {
        return null;
    }

    public void setGlobalScheme(EditorColorsScheme scheme) {

    }
}
