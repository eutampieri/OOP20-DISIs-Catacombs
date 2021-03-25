package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.image.BufferedImage;
import eu.eutampieri.catacombs.ui.utils.ImageLoader;

public class GameSheets {
	
	private BufferedImage sheet;
	
	public GameSheets(String path) {
		this.sheet = ImageLoader.loadImage(path);
	}
	
	public BufferedImage getImage() {
		return this.sheet;
	}
	
	public BufferedImage cutImage(int x, int y, int width, int height) {
		return this.sheet.getSubimage(x, y, width, height);
	}

}
