package com.intellij.util;

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.ImageUtil;
import com.intellij.util.ui.JBImageIcon;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IconUtil {
    public static void paintInCenterOf(@NotNull Component c, @NotNull Graphics g, @NotNull Icon icon) {
      final int x = (c.getWidth() - icon.getIconWidth()) / 2;
      final int y = (c.getHeight() - icon.getIconHeight()) / 2;
      icon.paintIcon(c, g, x, y);
    }

    public static Image toImage(@NotNull Icon icon) {
      return IconLoader.toImage(icon);
    }

    @NotNull
    public static Icon brighter(@NotNull Icon source, int tones) {
      return filterIcon(null, source, new BrighterFilter(tones));
    }

    @NotNull
    public static Icon darker(@NotNull Icon source, int tones) {
      return filterIcon(null, source, new DarkerFilter(tones));
    }

    @NotNull
    private static Icon filterIcon(Graphics2D g, @NotNull Icon source, @NotNull Filter filter) {
      BufferedImage src = g != null ? UIUtil.createImage(g, source.getIconWidth(), source.getIconHeight(), Transparency.TRANSLUCENT) :
                                      UIUtil.createImage(source.getIconWidth(), source.getIconHeight(), Transparency.TRANSLUCENT);
      Graphics2D g2d = src.createGraphics();
      source.paintIcon(null, g2d, 0, 0);
      g2d.dispose();
      BufferedImage img = g != null ? UIUtil.createImage(g, source.getIconWidth(), source.getIconHeight(), Transparency.TRANSLUCENT) :
                                      UIUtil.createImage(source.getIconWidth(), source.getIconHeight(), Transparency.TRANSLUCENT);
      int[] rgba = new int[4];
      for (int y = 0; y < src.getRaster().getHeight(); y++) {
        for (int x = 0; x < src.getRaster().getWidth(); x++) {
          src.getRaster().getPixel(x, y, rgba);
          if (rgba[3] != 0) {
            img.getRaster().setPixel(x, y, filter.convert(rgba));
          }
        }
      }
      return createImageIcon((Image)img);
    }

    @NotNull
    public static JBImageIcon createImageIcon(@NotNull final Image img) {
      return new JBImageIcon(img) {
        @Override
        public int getIconWidth() {
          return ImageUtil.getUserWidth(getImage());
        }

        @Override
        public int getIconHeight() {
          return ImageUtil.getUserHeight(getImage());
        }
      };
    }

    private static abstract class Filter {
      @NotNull
      abstract int[] convert(@NotNull int[] rgba);
    }

    private static class ColorFilter extends Filter {
      private final float[] myBase;
      private final boolean myKeepGray;

      private ColorFilter(@NotNull Color color, boolean keepGray) {
        myKeepGray = keepGray;
        myBase = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      }

      @NotNull
      @Override
      int[] convert(@NotNull int[] rgba) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(rgba[0], rgba[1], rgba[2], hsb);
        int rgb = Color.HSBtoRGB(myBase[0], myBase[1] * (myKeepGray ? hsb[1] : 1f), myBase[2] * hsb[2]);
        return new int[]{rgb >> 16 & 0xff, rgb >> 8 & 0xff, rgb & 0xff, rgba[3]};
      }
    }

    private static class BrighterFilter extends Filter {
      private final int myTones;

      public BrighterFilter(int tones) {
        myTones = tones;
      }

      @NotNull
      @Override
      int[] convert(@NotNull int[] rgba) {
        final float[] hsb = Color.RGBtoHSB(rgba[0], rgba[1], rgba[2], null);
        float brightness = hsb[2];
        for (int i = 0; i < myTones; i++) {
          brightness = Math.min(1, brightness * 1.1F);
          if (brightness == 1) break;
        }
        Color color = Color.getHSBColor(hsb[0], hsb[1], brightness);
        return new int[]{color.getRed(), color.getGreen(), color.getBlue(), rgba[3]};
      }
    }

    private static class DarkerFilter extends Filter {
      private final int myTones;

      public DarkerFilter(int tones) {
        myTones = tones;
      }

      @NotNull
      @Override
      int[] convert(@NotNull int[] rgba) {
        final float[] hsb = Color.RGBtoHSB(rgba[0], rgba[1], rgba[2], null);
        float brightness = hsb[2];
        for (int i = 0; i < myTones; i++) {
          brightness = Math.max(0, brightness / 1.1F);
          if (brightness == 0) break;
        }
        Color color = Color.getHSBColor(hsb[0], hsb[1], brightness);
        return new int[]{color.getRed(), color.getGreen(), color.getBlue(), rgba[3]};
      }
    }

}
