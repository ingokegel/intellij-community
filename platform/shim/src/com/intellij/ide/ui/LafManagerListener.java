package com.intellij.ide.ui;

import java.util.EventListener;

/**
 * If you are interested in listening UI changes you have to
 * use this listener instead of registening {@code PropertyChangeListener}
 * into {@code UIManager}
 *
 * @author Vladimir Kondratyev
 */
public interface LafManagerListener extends EventListener {
    void lookAndFeelChanged(LafManager source);
}
