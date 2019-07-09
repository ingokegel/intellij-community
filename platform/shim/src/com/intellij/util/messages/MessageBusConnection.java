package com.intellij.util.messages;

import org.jetbrains.annotations.NotNull;

public interface MessageBusConnection {
    <L> void subscribe(@NotNull Topic<L> topic, @NotNull L handler) throws IllegalStateException;
    <L> void subscribe(@NotNull Topic<L> topic) throws IllegalStateException;
    void disconnect();
}
