package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;

public class GameSheets {
	
	private final BufferedImage sheet;
	
	public GameSheets(final Path path) {
		this.sheet = ImageLoader.loadImage(path).get();
	}
	
	public Optional<BufferedImage> getImage() {
		return Optional.of(this.sheet);
	}
	
	public BufferedImage cutImage(final int x, final int y, final int width, final int height) {
		return this.sheet.getSubimage(x, y, width, height);
	}

}
