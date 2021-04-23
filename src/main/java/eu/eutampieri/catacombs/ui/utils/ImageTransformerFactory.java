package eu.eutampieri.catacombs.ui.utils;

/**
 * An abstract factory of image transformers
 */
public interface ImageTransformerFactory {
    /**
     * Rotate an image.
     * @param degrees the rotation degrees.
     * @return The rotated image.
     */
    ImageTransformer rotate(double degrees);

    /**
     * Scale an image.
     * @param scalingFactor The scaling factor, i.e. how many times will the image be enlarged
     * @return The scaled image
     */
    ImageTransformer scale(double scalingFactor);

    /**
     * Flip an image.
     * @param flipX true if you want to flip the image on the x axis.
     * @param flipY true if you want to flip the image on the y axis.
     * @return The flipped image
     */
    ImageTransformer flip(boolean flipX, boolean flipY);
}
