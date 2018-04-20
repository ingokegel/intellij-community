package com.intellij.ide;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class IdeBundle {
    public static String message(String key) {
        switch (key) {
            case "idea.dark.look.and.feel":
                return "Darcula";
            case "idea.default.look.and.feel":
                return "IDEA Default";
            case "idea.intellij.look.and.feel":
                return "IntelliJ";
            case "error.cannot.set.look.and.feel":
                return "Cannot set look and feel {0}, error message: {1}";
            default:
                return "";

        }
    }

    public static String message(String key, @NotNull Object... params) {
        return MessageFormat.format(message(key), params);
    }
}
