package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class UIUtilsTest {

    @Test
    public void testLoadImage() {
        final Optional<BufferedImage> img = ImageLoader.loadImage(Path.of("res/eagle.png"));
        assertTrue(img.isEmpty());
        final Optional<BufferedImage> img1 = ImageLoader.loadImage(Path.of("res/playersheet.png"));
        assertTrue(img1.isPresent());
    }
}
