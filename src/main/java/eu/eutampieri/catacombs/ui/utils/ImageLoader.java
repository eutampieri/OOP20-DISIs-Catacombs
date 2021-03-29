package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageLoader {

	private ImageLoader() {

	}
	
	public static BufferedImage loadImage(final String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(TextLoader.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}

}
