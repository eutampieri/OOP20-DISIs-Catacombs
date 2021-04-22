package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import eu.eutampieri.catacombs.ui.utils.TextLoader;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UIutilsTest {

    @Test
    public void testLoadText() {
        final Optional<String> text = TextLoader.loadText(Path.of("res/eagle.txt"));
        assertTrue(text.isEmpty());
        final Optional<String> text1 = TextLoader.loadText(Path.of("build.gradle.kts"));
        assertTrue(text1.isPresent());
    }

    @Test
    public void testLoadImage() {
        final Optional<BufferedImage> img = ImageLoader.loadImage(Path.of("res/eagle.png"));
        assertTrue(img.isEmpty());
        final Optional<BufferedImage> img1 = ImageLoader.loadImage(Path.of("res/playersheet.png"));
        assertTrue(img1.isPresent());
    }
}
