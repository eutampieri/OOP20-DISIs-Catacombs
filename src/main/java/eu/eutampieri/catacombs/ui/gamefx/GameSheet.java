package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.image.BufferedImage;
import java.util.Optional;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;

/**
 * this class manages the laoding and the cutting of the singular frames.
 */

public final class GameSheet {
    private final BufferedImage sheet;

    /**
     * GameSheet constructor.
     * @param path yhe path of the image file
     */

    public GameSheet(final String path) {
        this.sheet = ImageLoader.loadImage(path).get();
    }

    /**
     * This method returns the image required.
     * @return the image required
     */

    public Optional<BufferedImage> getImage() {
        return Optional.of(this.sheet);
    }

    /**
     * this method cut a sub image form an image
     * @param x         x offset
     * @param y         y offset
     * @param width     sub image width
     * @param height    sub image height
     * @return          the cat image
     */

    public BufferedImage cutImage(final int x, final int y, final int width, final int height) {
        return this.sheet.getSubimage(x, y, width, height);
    }

}
