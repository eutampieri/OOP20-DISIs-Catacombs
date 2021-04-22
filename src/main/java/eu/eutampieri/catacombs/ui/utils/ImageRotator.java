package eu.eutampieri.catacombs.ui.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageRotator {
    public BufferedImage rotate(final BufferedImage image, final double angle) {
        final BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
        final AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        at.rotate(Math.toRadians(angle), image.getWidth() / 2f, image.getHeight() / 2f);
        g.drawImage(image, at, null);

        return rotatedImage;
    }
}
