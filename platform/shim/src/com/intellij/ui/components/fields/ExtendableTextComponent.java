package com.intellij.ui.components.fields;

import javax.swing.*;
import java.util.List;

import static com.intellij.util.ui.JBUI.scale;

public class ExtendableTextComponent extends JTextField {
    public static final String VARIANT = "extendable";

    public List<Extension> getExtensions() {
      throw new UnsupportedOperationException();
    }

    public interface Extension {
        Icon getIcon(boolean hovered);
        default int getPreferredSpace() {
            return 0;
        }
        default int getIconGap() {
          return scale(5);
        }
        default int getBeforeIconOffset() {
          return 0;
        }
        default boolean isIconBeforeText() {
          return false;
        }
        default String getTooltip() {
          return null;
        }
        default Runnable getActionOnClick() {
          return null;
        }
    }
}
