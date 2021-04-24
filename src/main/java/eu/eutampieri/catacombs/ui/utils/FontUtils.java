package eu.eutampieri.catacombs.ui.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;

/**
 * This class return the width of a font
 */

public final class FontUtils {

    private FontUtils() {
    }

    /**
     * This method is used to know the dimension of the text used in the game.
     * @param font the font width to be known
     * @param text the text width to be known
     * @return the text width
     */

    public static int getTextWidth(final Font font, final String text) {
        final FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
        return (int) font.getStringBounds(text, frc).getWidth();
    }

}
