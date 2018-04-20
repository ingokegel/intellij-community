package com.intellij.openapi.editor;

import com.intellij.openapi.editor.event.EditorMouseListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface Editor {
    void addEditorMouseListener(@NotNull EditorMouseListener listener);
    JComponent getContentComponent();
}
