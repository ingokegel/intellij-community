package com.intellij.openapi.application;

public class ApplicationManager {

    public static final Application INSTANCE = new Application();

    public static Application getApplication() {
        return INSTANCE;
    }
}
