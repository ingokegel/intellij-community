package com.intellij.ide.ui;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class UISettings {

    public static final UISettings INSTANCE = new UISettings();
    private static final UISettingsState DUMMY_SETTINGS_STATE = new UISettingsState();
    private static boolean overrideLafFonts = false;
    private static String fontFace;
    private static int fontSize;

    public static void setupAntialiasing(Graphics g) {
    }

    public static UISettings getShadowInstance() {
        return INSTANCE;
    }

    public static UISettings getInstance() {
        return INSTANCE;
    }

    public static UISettings getInstanceOrNull() {
        return INSTANCE;
    }

    public void fireUISettingsChanged() {
    }

    public boolean getOverrideLafFonts() {
        return overrideLafFonts;
    }

    public static void setOverrideLafFonts(@NotNull String fontFace, int fontSize) {
        overrideLafFonts = true;
        UISettings.fontFace = fontFace;
        UISettings.fontSize = fontSize;
    }

    public String getFontFace() {
        return fontFace;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean getDisableMnemonicsInControls() {
        return true;
    }

    public boolean getSmoothScrolling() {
        return true;
    }

    public UISettingsState getState() {
        return DUMMY_SETTINGS_STATE;
    }
}
