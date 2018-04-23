package com.intellij.openapi.application;

import com.intellij.openapi.components.ComponentManager;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Application implements ComponentManager {
    @Override
    public <T> T getComponent(@NotNull Class<T> interfaceClass) {
        return null;
    }

    @Override
    public void dispose() {

    }

    public boolean isUnitTestMode() {
        return false;
    }

    public boolean isActive() {
        return true;
    }

    public MessageBus getMessageBus() {
        throw new UnsupportedOperationException();
    }

    public void invokeLater(Runnable runnable, ModalityState state) {
        invokeLater(runnable);
    }

    public void invokeLater(Runnable runnable) {
        EventQueue.invokeLater(runnable);
    }

    public void executeOnPooledThread(Runnable runnable) {
        throw new UnsupportedOperationException();
    }

    public ModalityState getDefaultModalityState() {
        return null;
    }

    public boolean isDispatchThread() {
        return EventQueue.isDispatchThread();
    }
}
