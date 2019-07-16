package com.intellij.openapi.application.impl;

import com.intellij.openapi.application.ModalityState;
import org.jetbrains.annotations.NotNull;

public class ModalityStateEx extends ModalityState {
    public ModalityStateEx() {
    }

    @Override
    public boolean dominates(@NotNull ModalityState anotherState) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
