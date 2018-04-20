package com.intellij.openapi.components;

import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.Disposable;
import org.jetbrains.annotations.NotNull;

public interface ComponentManager extends Disposable {
    <T> T getComponent(@NotNull Class<T> interfaceClass);
}
