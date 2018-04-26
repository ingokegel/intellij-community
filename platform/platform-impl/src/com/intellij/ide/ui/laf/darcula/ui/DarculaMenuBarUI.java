package com.intellij.ide.ui.laf.darcula.ui;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.awt.*;

public class DarculaMenuBarUI extends MetalMenuBarUI {

  public static ComponentUI createUI(JComponent c) {
      return new DarculaMenuBarUI();
  }

  @Override
  public void paint(Graphics g, JComponent c) {
    super.paint(g, c);
    g.setColor(UIManager.getColor("MenuItem.background"));
    g.fillRect(0, 0, c.getWidth(), c.getHeight());
  }
}
