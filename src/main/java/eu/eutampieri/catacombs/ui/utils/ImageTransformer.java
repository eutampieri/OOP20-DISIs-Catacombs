package eu.eutampieri.catacombs.ui.utils;

import java.awt.image.BufferedImage;

/**
 * Describes an object that can transform images.
 */
public interface ImageTransformer {
    /**
     * Apply the transformation onto input, and return the transformed image.
     * @param input The input image.
     * @return The transformed image.
     */
    BufferedImage transform(BufferedImage input);
}
