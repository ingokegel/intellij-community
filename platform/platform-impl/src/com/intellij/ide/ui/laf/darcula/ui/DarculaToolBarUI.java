package com.intellij.ide.ui.laf.darcula.ui;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.*;

public class DarculaToolBarUI extends BasicToolBarUI {

  public static ComponentUI createUI(JComponent c) {
    return new DarculaToolBarUI();
  }

  @Override
  protected Border createRolloverBorder() {
    return new RolloverButtonBorder();
  }

  public static class RolloverButtonBorder extends AbstractBorder implements UIResource {

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
      AbstractButton b = (AbstractButton)c;
      ButtonModel model = b.getModel();

      if (b.isEnabled() && (model.isRollover() || model.isSelected() || model.isPressed() || b.hasFocus())) {
        Color oldColor = g.getColor();
        g.translate(x, y);

        Color color;
        if (model.isPressed() || model.isSelected()) {
          color = UIManager.getColor("ToolBarButton.intellij.pressedBorderColor");
        }
        else {
          color = UIManager.getColor("ToolBarButton.intellij.focusedBorderColor");
        }
        g.setColor(color);
        g.drawRect(0, 0, w - 1, h - 1);
        g.translate(-x, -y);
        g.setColor(oldColor);
      }
    }

    @Override
    public Insets getBorderInsets(Component c) {
      return JBUI.insets(4);
    }
  }
}
