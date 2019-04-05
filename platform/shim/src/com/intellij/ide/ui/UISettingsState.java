package com.intellij.ide.ui;

public class UISettingsState {
    public boolean getOverrideLafFonts() {
        return UISettings.getInstance().getOverrideLafFonts();
    }

    public String getFontFace() {
        return UISettings.getInstance().getFontFace();
    }

    public int getFontSize() {
        return UISettings.getInstance().getFontSize();
    }
}
