package com.intellij.ide.ui;

import java.awt.*;

public class UISettings {

    public static final UISettings INSTANCE = new UISettings();
    private static final UISettingsState DUMMY_SETTINGS_STATE = new UISettingsState();

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
        return false;
    }

    public String getFontFace() {
        throw new UnsupportedOperationException();
    }

    public int getFontSize() {
        throw new UnsupportedOperationException();
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
