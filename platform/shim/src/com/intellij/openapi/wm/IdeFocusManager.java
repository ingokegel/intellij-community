package com.intellij.openapi.wm;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class IdeFocusManager {

    public static final IdeFocusManager INSTANCE = new IdeFocusManager();

    public static IdeFocusManager findInstance() {
        return INSTANCE;
    }

    public static IdeFocusManager getGlobalInstance() {
        return INSTANCE;
    }

    public Component getFocusOwner() {
        return null;
    }

    public void doWhenFocusSettlesDown(@NotNull Runnable runnable) {

    }

    public void requestFocus(@NotNull Component c, boolean forced) {

    }
}
