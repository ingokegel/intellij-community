// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.editor.colors;

import java.awt.*;

public class EditorColorsScheme {
    public static final String DEFAULT_SCHEME_NAME = "Default";

    public Color getDefaultBackground() {
        return Color.WHITE;
    }

    public String getName() {
        return DEFAULT_SCHEME_NAME;
    }
}
