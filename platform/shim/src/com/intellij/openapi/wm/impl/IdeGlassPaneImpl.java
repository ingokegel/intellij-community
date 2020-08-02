package com.intellij.openapi.wm.impl;

import javax.swing.*;

public class IdeGlassPaneImpl extends JPanel {
    public IdeGlassPaneImpl(JRootPane rootPane, boolean installPainters) {
        setOpaque(false);
        setVisible(false);
        setEnabled(false);
        setLayout(null);
    }
}
