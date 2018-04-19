package com.intellij.openapi.editor.ex;

import com.intellij.openapi.editor.Editor;

public interface FocusChangeListener {
    void focusGained(Editor editor);
    void focusLost(Editor editor);
}
