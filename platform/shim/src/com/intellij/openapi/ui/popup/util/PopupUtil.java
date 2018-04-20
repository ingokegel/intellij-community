package com.intellij.openapi.ui.popup.util;

import javax.swing.*;

public class PopupUtil {
    private static int popupWeight;

    public static void setPopupType(PopupFactory factory, int popupWeight) {
        PopupUtil.popupWeight = popupWeight;
    }

    public static int getPopupType(PopupFactory popupFactory) {
        return popupWeight;
    }

    public static JComponent getPopupContainerFor(JRootPane rootPane) {
        return null;
    }
}
