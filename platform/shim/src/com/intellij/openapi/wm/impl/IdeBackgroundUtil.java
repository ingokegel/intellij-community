package com.intellij.openapi.wm.impl;

public class IdeBackgroundUtil {
    public static void repaintAllWindows() {

    }

    public enum Fill {
      PLAIN, SCALE, TILE
    }

    public enum Anchor {
      TOP_LEFT, TOP_CENTER, TOP_RIGHT,
      MIDDLE_LEFT, CENTER, MIDDLE_RIGHT,
      BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
    }

    public static final String EDITOR_PROP = "idea.background.editor";
    public static final String FRAME_PROP = "idea.background.frame";

}
