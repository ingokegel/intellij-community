package com.intellij.ui;

import java.awt.*;

public class ScreenUtil {
    public static Insets getScreenInsets(GraphicsConfiguration gc) {
        return Toolkit.getDefaultToolkit().getScreenInsets(gc);
    }
}
