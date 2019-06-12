package com.intellij.ide.ui.laf;

import javax.swing.*;

public class TempUIThemeBasedLookAndFeelInfo extends UIManager.LookAndFeelInfo {
    public TempUIThemeBasedLookAndFeelInfo(String name, String className) {
        super(name, className);
    }

    public UIManager.LookAndFeelInfo getPreviousLaf() {
        return null;
    }
}
