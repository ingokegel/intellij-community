// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.ui;

import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

public class SwingActionDelegate {
    public SwingActionDelegate(String actionId) {

    }

    protected JTable getComponent(AnActionEvent event) {
        return null;
    }
}
