package com.intellij.ide.ui.laf.darcula.ui;

import com.intellij.util.ui.UIUtil;

import java.awt.*;

// ejt: Added to replace com.intellij.ide.ui.UISettings;
public class UISettings {
    public static void setupAntialiasing(Graphics g) {
        UIUtil.applyRenderingHints(g);
    }
}
