package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javax.imageio.ImageIO;

public final class ImageLoader {

    private ImageLoader() {

    }

    /**
     * This methos loads image from a path.
     * @param path the path to find the image
     * @return an optiona of Buffered image
     */
    public static Optional<BufferedImage> loadImage(final Path path) {
        BufferedImage image;
        try {
            image = ImageIO.read(path.toFile());
        } catch (IOException e) {
            return Optional.empty();
        }

        return Optional.of(image);
    }

}
