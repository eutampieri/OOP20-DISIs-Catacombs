package eu.eutampieri.catacombs.ui.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Concrete implementation of a simple {@link ImageTransformerFactory}.
 */
public final class ImageTransformerFactoryImpl implements ImageTransformerFactory {
    @Override
    public ImageTransformer rotate(final double degrees) {
        return input -> {
            final ImageRotator ir = new ImageRotator();
            return ir.rotate(input, degrees);
        };
    }

    @Override
    public ImageTransformer scale(final double scalingFactor) {
        return input -> {
            final int w = (int) (input.getWidth() * scalingFactor);
            final int h = (int) (input.getHeight() * scalingFactor);
            final BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            final AffineTransform at = new AffineTransform();
            at.scale(scalingFactor, scalingFactor);
            final AffineTransformOp scaling = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            return scaling.filter(input, after);
        };
    }

    @Override
    public ImageTransformer flip(final boolean flipX, final boolean flipY) {
        return image -> {
            final int width = image.getWidth();
            final int height = image.getHeight();
            final int sx1 = flipY ? width : 0;
            final int sy1 = flipX ? height : 0;
            final int sx2 = flipY ? 0 : width;
            final int sy2 = flipX ? 0 : height;
            final BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g = flippedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, sx1, sy1, sx2, sy2, null);
            g.dispose();
            return flippedImage;
        };
    }
}
