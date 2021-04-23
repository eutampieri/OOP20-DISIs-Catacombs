package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class UIUtilsTest {

    @Test
    public void testLoadImage() {
        final Optional<BufferedImage> img = ImageLoader.loadImage("res/eagle.png");
        assertTrue(img.isEmpty());
        final Optional<BufferedImage> img1 = ImageLoader.loadImage("res/playersheet.png");
        assertTrue(img1.isPresent());
    }
}
