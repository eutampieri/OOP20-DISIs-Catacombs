package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;
import eu.eutampieri.catacombs.model.Direction;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.Tile;
import org.apache.commons.lang3.StringUtils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AssetManagerProxy {

    private AssetManagerProxy(){}

    public static <T extends GameObject & Animatable> List<BufferedImage> getFrames(final T entity, final Action action, final Direction direction) {
        final AssetManager am = AssetManager.getAssetManager();
        if(entity.canPerform(action)) {
            if(direction == null && action.getDirections().size() != 0) {
                throw new IllegalArgumentException();
            } else if (direction != null && action.getDirections().size() == 0) {
                throw new IllegalArgumentException();
            }
            switch (entity.getKind()) {
                case BOSS:
                    String frameKey = "Boss_" + StringUtils.capitalize(action.toString());
                    if (direction != null) {
                        frameKey += "_" + direction.toString();
                    }
                    return am.getFrames(frameKey).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                case ITEM:
                    break;
                case ENEMY:
                    assert direction != null;
                    frameKey = entity.getClass().getSimpleName() + "_" + direction.toString();
                    return am.getFrames(frameKey).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                case PLAYER:
                    assert direction != null;
                    return am.getFrames("Walk_" + direction.toString()).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                default:
                    throw new IllegalArgumentException();
            }
        }
        throw new IllegalArgumentException();
    }

    public static BufferedImage getSprite(final GameObject entity) {
        final AssetManager am = AssetManager.getAssetManager();
        switch (entity.getKind()) {
            case BULLET:
                return am.getImage("Projectile_1");
            default:
                return null;
        }
    }

    private static BufferedImage scale(final BufferedImage before, final double scale) {
        final int w = before.getWidth();
        final int h = before.getHeight();
        final BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaling = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaling.filter(before, after);
    }

    public static Optional<BufferedImage> getTileSprite(final Tile tile) {
        final BufferedImage tileImg;
        switch (tile) {
            case FLOOR:
                tileImg = AssetManager.getAssetManager().getImage("41");
                break;
            case WALL:
                tileImg = AssetManager.getAssetManager().getImage("25");
                break;
            default:
                return Optional.empty();
        }
        return Optional.of(scale(tileImg, 1.5));
    }
}
