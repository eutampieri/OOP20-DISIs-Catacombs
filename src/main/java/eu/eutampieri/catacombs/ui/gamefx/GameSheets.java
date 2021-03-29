package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.image.BufferedImage;
import eu.eutampieri.catacombs.ui.utils.ImageLoader;

public class GameSheets {
	
	private final BufferedImage sheet;
	
	public GameSheets(final String path) {
		this.sheet = ImageLoader.loadImage(path);
	}
	
	public BufferedImage getImage() {
		return this.sheet;
	}
	
	public BufferedImage cutImage(final int x, final int y, final int width, final int height) {
		return this.sheet.getSubimage(x, y, width, height);
	}

}
