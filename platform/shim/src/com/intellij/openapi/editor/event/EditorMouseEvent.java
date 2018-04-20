package com.intellij.openapi.editor.event;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.awt.event.MouseEvent;
import java.util.EventObject;

public class EditorMouseEvent extends EventObject {
    public EditorMouseEvent(Object source) {
        super(source);
    }
}
