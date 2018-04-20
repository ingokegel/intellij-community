package com.intellij.ui;

import com.intellij.util.ui.JBInsets;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ScreenUtil {
    public static Insets getScreenInsets(GraphicsConfiguration gc) {
        return Toolkit.getDefaultToolkit().getScreenInsets(gc);
    }

    public static void moveRectangleToFitTheScreen(Rectangle aRectangle) {
        int screenX = aRectangle.x + aRectangle.width / 2;
        int screenY = aRectangle.y + aRectangle.height / 2;
        Rectangle screen = getScreenRectangle(screenX, screenY);

        moveToFit(aRectangle, screen, null);
    }

    public static void moveToFit(final Rectangle rectangle, final Rectangle container, @Nullable Insets padding) {
        Rectangle move = new Rectangle(rectangle);
        JBInsets.addTo(move, padding);

        if (move.getMaxX() > container.getMaxX()) {
            move.x = (int)container.getMaxX() - move.width;
        }


        if (move.getMinX() < container.getMinX()) {
            move.x = (int)container.getMinX();
        }

        if (move.getMaxY() > container.getMaxY()) {
            move.y = (int)container.getMaxY() - move.height;
        }

        if (move.getMinY() < container.getMinY()) {
            move.y = (int)container.getMinY();
        }

        JBInsets.removeFrom(move, padding);
        rectangle.setBounds(move);
    }

    public static Rectangle getScreenRectangle(int x, int y) {
        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        if (devices.length == 0) {
            return new Rectangle(x, y, 0, 0);
        }
        if (devices.length == 1) {
            return getScreenRectangle(devices[0]);
        }
        Rectangle[] rectangles = new Rectangle[devices.length];
        for (int i = 0; i < devices.length; i++) {
            GraphicsConfiguration configuration = devices[i].getDefaultConfiguration();
            Rectangle bounds = configuration.getBounds();
            rectangles[i] = applyInsets(bounds, getScreenInsets(configuration));
            if (bounds.contains(x, y)) {
                return rectangles[i];
            }
        }
        Rectangle bounds = rectangles[0];
        int minimum = distance(bounds, x, y);
        if (bounds.width == 0 || bounds.height == 0) {
            //Screen is invalid, give maximum score
            minimum = Integer.MAX_VALUE;
        }
        for (int i = 1; i < rectangles.length; i++) {
            if (rectangles[i].width == 0 || rectangles[i].height == 0) {
                //Screen is invalid
                continue;
            }
            int distance = distance(rectangles[i], x, y);
            if (minimum > distance) {
                minimum = distance;
                bounds = rectangles[i];
            }
        }
        if (bounds.width == 0 || bounds.height == 0) {
            //All screens were invalid, return sensible default
            return new Rectangle(x, y, 0, 0);
        }
        return bounds;
    }

    private static Rectangle applyInsets(Rectangle rect, Insets i) {
        rect = new Rectangle(rect);
        JBInsets.removeFrom(rect, i);
        return rect;
    }

    private static Rectangle getScreenRectangle(GraphicsDevice device) {
        return getScreenRectangle(device.getDefaultConfiguration());
    }

    /**
     * Returns a visible area for the specified graphics configuration.
     *
     * @param configuration one of available configurations
     * @return a visible area rectangle
     */
    public static Rectangle getScreenRectangle(GraphicsConfiguration configuration) {
        return applyInsets(configuration.getBounds(), getScreenInsets(configuration));
    }

    private static int distance(Rectangle bounds, int x, int y) {
        x -= normalize(x, bounds.x, bounds.x + bounds.width);
        y -= normalize(y, bounds.y, bounds.y + bounds.height);
        return x * x + y * y;
    }

    private static int normalize(int value, int min, int max) {
        return value < min ? min : value > max ? max : value;
    }
}
