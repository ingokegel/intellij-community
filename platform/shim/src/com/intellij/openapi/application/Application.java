package com.intellij.openapi.application;

import com.intellij.openapi.components.ComponentManager;
import org.jetbrains.annotations.NotNull;

public class Application implements ComponentManager {
    @Override
    public <T> T getComponent(@NotNull Class<T> interfaceClass) {
        return null;
    }

    @Override
    public void dispose() {

    }
}
