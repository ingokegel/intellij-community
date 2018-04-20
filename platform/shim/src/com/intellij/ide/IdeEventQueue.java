package com.intellij.ide;

import com.intellij.openapi.Disposable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class IdeEventQueue {
    public static IdeEventQueue getInstance() {
        return new IdeEventQueue();
    }

    public void addDispatcher(@NotNull EventDispatcher dispatcher, Disposable parent) {

    }

    public AWTEvent getTrueCurrentEvent() {
        return null;
    }

    @FunctionalInterface
    public interface EventDispatcher {
        boolean dispatch(@NotNull AWTEvent e);
    }
}
