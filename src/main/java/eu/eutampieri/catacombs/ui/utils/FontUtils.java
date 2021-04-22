package eu.eutampieri.catacombs.ui.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public final class FontUtils {

    private FontUtils() { }

    public static int getTextWidth(final Font font, final String text) {
        final FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
        return (int) font.getStringBounds(text, frc).getWidth();
    }

}
