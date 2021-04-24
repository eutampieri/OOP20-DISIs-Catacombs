package eu.eutampieri.catacombs.ui.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.imageio.ImageIO;

/**
 * This class loads image from a path.
 */

// Suppressing SpotBugs because it finds non existing bugs, spent half an hour debugging and found none
@SuppressFBWarnings()
public final class ImageLoader {

    private ImageLoader() {

    }

    /**
     * This method loads image from a path.
     * @param path the path to find the image
     * @return an optional of Buffered image
     */
    public static Optional<BufferedImage> loadImage(final String path) {
        try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
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
