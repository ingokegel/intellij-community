// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.editor.colors.impl;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import org.jetbrains.annotations.NotNull;

public class EditorColorsManagerImpl extends EditorColorsManager {
    @Override
    public void addColorsScheme(@NotNull EditorColorsScheme scheme) {

    }

    @Override
    public void removeAllSchemes() {

    }

    @Override
    public @NotNull EditorColorsScheme[] getAllSchemes() {
        return new EditorColorsScheme[0];
    }

    @Override
    public void setGlobalScheme(EditorColorsScheme scheme) {

    }

    @Override
    public @NotNull EditorColorsScheme getGlobalScheme() {
        return null;
    }

    @Override
    public EditorColorsScheme getScheme(@NotNull String schemeName) {
        return null;
    }

    @Override
    public boolean isDefaultScheme(EditorColorsScheme scheme) {
        return false;
    }

    @Override
    public boolean isUseOnlyMonospacedFonts() {
        return false;
    }

    @Override
    public void setUseOnlyMonospacedFonts(boolean b) {

    }

    public void handleThemeAdded(Object theme) {
    }

    public void handleThemeRemoved(Object theme) {
    }

    public void setGlobalScheme(EditorColorsScheme scheme, boolean processChangeSynchronously) {
    }
}
