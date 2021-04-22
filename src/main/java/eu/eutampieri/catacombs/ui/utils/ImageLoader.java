package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
        try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.toString())) {
            if (file == null) {
                return Optional.empty();
            }
            try {
                final BufferedImage image = ImageIO.read(file);
                return Optional.of(image);
            } catch (IOException e) {
                return Optional.empty();
            }
        } catch (IOException inputStreamException) {
            inputStreamException.printStackTrace();
            return Optional.empty();
        }

    }

}
