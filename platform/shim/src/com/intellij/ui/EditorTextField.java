package com.intellij.ui;

import com.intellij.openapi.editor.Editor;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;

public class EditorTextField extends JBPanel {
    public JComponent getFocusTarget() {
        throw new UnsupportedOperationException();
    }

    public Editor getEditor() {
        return null;
    }
}
