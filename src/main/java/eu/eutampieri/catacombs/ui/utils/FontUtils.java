package eu.eutampieri.catacombs.ui.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public class FontUtils {
	
	public static int getTextWidth(Font font, String text) {
		final FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
		return (int) font.getStringBounds(text, frc).getWidth();
	}

}
