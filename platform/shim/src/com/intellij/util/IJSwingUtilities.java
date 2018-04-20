package com.intellij.util;

import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class IJSwingUtilities {
    public static void updateComponentTreeUI(@Nullable Component c) {
        if (c == null) {
            return;
        }
        for (Component component : UIUtil.uiTraverser(c).postOrderDfsTraversal()) {
            if (component instanceof JComponent) {
                ((JComponent)component).updateUI();
            }
        }
        c.invalidate();
        c.validate();
        c.repaint();
    }
}
