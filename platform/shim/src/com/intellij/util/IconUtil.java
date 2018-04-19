package com.intellij.util;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class IconUtil {
    public static void paintInCenterOf(@NotNull Component c, @NotNull Graphics g, @NotNull Icon icon) {
      final int x = (c.getWidth() - icon.getIconWidth()) / 2;
      final int y = (c.getHeight() - icon.getIconHeight()) / 2;
      icon.paintIcon(c, g, x, y);
    }
}
