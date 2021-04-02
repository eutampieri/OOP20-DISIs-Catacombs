package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			// TODO: image = ImageIO.read(TextLoader.class.getResourceAsStream(path));
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}

}
