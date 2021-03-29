package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;


public final class ImageLoader {

	private ImageLoader() {

	}
	
	public static Optional<BufferedImage> loadImage(final String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(TextLoader.class.getResourceAsStream(path));
		} catch (IOException e) {
			return Optional.empty();
		}
		
		return Optional.of(image);
		
	}

}
