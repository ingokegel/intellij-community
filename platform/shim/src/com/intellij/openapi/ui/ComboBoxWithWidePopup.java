package com.intellij.openapi.ui;

import javax.swing.*;

public class ComboBoxWithWidePopup<E> extends JComboBox<E> {
    public int getMinimumPopupWidth() {
        throw new UnsupportedOperationException();
    }
}
