package com.intellij.openapi.editor.ex;

import com.intellij.openapi.editor.Editor;

public interface EditorEx extends Editor {
    void addFocusListener(FocusChangeListener focusChangeListener);
}
