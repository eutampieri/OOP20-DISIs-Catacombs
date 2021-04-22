package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;

public class GameSheets {
	private final BufferedImage sheet;

	/**
	 * GameSheet constructor
	 * @param path the path of the sheet file
	 */
	
	public GameSheets(final Path path) {
		this.sheet = ImageLoader.loadImage(path).get();
	}

	/**
	 *
	 * @return the image specified in the constructor parameter
	 */
	
	public Optional<BufferedImage> getImage() {
		return Optional.of(this.sheet);
	}

	/**
	 * This method cut an image from the sheet
	 * @param x the x offset
	 * @param y the y offset
	 * @param width the width of the subimage to cut
	 * @param height the height of the subimage to cut
	 * @return the cutted image
	 */
	
	public BufferedImage cutImage(final int x, final int y, final int width, final int height) {
		return this.sheet.getSubimage(x, y, width, height);
	}

}
