package com.intellij.ide.ui.laf.darcula.ui;

import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DarculaJBPopupComboPopup<T> implements ComboPopup {

    public DarculaJBPopupComboPopup(JComponent component) {
    }

    public static final String CLIENT_PROP = "ComboBox.jbPopup";

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public JList getList() {
        return null;
    }

    @Override
    public MouseListener getMouseListener() {
        return null;
    }

    @Override
    public MouseMotionListener getMouseMotionListener() {
        return null;
    }

    @Override
    public KeyListener getKeyListener() {
        return null;
    }

    @Override
    public void uninstallingUI() {

    }

    protected void configureList(@NotNull JList<T> list) {

    }

    protected void customizeListRendererComponent(JComponent component) {
    }
}
