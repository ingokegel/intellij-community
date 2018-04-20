package com.intellij.openapi.ui;

import javax.swing.*;

public class Messages {
    public static Icon getErrorIcon() {
        return null;
    }

    public static void showMessageDialog(String message, String title, Icon errorIcon) {
        System.err.println(message);
    }
}
