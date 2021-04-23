package eu.eutampieri.catacombs.ui.utils;

/**
 * An abstract factory of image transformers
 */
public interface ImageTransformerFactory {
    /**
     * Rotate an image.
     * @param degrees the rotation degrees.
     * @return An ImageRotator that rotates an image.
     */
    ImageTransformer rotate(double degrees);

    /**
     * Scale an image.
     * @param scalingFactor The scaling factor, i.e. how many times will the image be enlarged
     * @return An ImageRotator that scales an image.
     */
    ImageTransformer scale(double scalingFactor);

    /**
     * Flip an image.
     * @param flipX true if you want to flip the image on the x axis.
     * @param flipY true if you want to flip the image on the y axis.
     * @return An ImageRotator that flips an image.
     */
    ImageTransformer flip(boolean flipX, boolean flipY);
}
