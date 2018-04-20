package com.intellij.internal.statistic.collectors.fus.actions.persistence;

import com.intellij.openapi.actionSystem.AnAction;

public class MainMenuCollector {

    public static final MainMenuCollector INSTANCE = new MainMenuCollector();

    public static MainMenuCollector getInstance() {
        return INSTANCE;
    }

    public void record(AnAction action) {

    }
}
