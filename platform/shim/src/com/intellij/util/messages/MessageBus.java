package com.intellij.util.messages;

import com.intellij.openapi.Disposable;
import org.jetbrains.annotations.NotNull;

public interface MessageBus {
    MessageBusConnection connect();
    MessageBusConnection connect(@NotNull Disposable parentDisposable);
}
