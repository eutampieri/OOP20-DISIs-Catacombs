package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;
import eu.eutampieri.catacombs.model.Direction;
import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.ui.Animation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class AssetManagerProxy {
    private final static double MAP_SCALING_FACTOR = 2.25;
    //private final static double BULLET_SCALING_FACTOR = 0.5;
    private final static Map<Tile, BufferedImage> MAP_CACHE = new HashMap<>();
    private final static Map<Triple<Entity, Action, Direction>, Pair<Animation, Long>> ANIMATIONS_CACHE = new HashMap<>();

    private AssetManagerProxy(){}

    public static Animation getFrames(final Entity entity, final Action action, final Direction direction) {
        if(entity.canPerform(action)) {
            if(direction == null && action.getDirections().size() != 0) {
                throw new IllegalArgumentException();
            } else if (direction != null && action.getDirections().size() == 0) {
                throw new IllegalArgumentException();
            }
            final Triple<Entity, Action, Direction> cacheKey = Triple.of(entity, action, direction);
            if (ANIMATIONS_CACHE.get(cacheKey) != null) {
                final Pair<Animation, Long> item = ANIMATIONS_CACHE.get(cacheKey);
                final long lastUpdated = item.getRight();
                final long now = System.currentTimeMillis();
                final long delta = now - lastUpdated;
                final Animation animation = item.getLeft();
                animation.update(delta);
                item.setValue(now);
                return animation;
            }
            final Animation a;
            long now;
            switch (entity.getKind()) {
                case BOSS:
                    String frameKey = "Boss_" + StringUtils.capitalize(action.toString());
                    if (direction != null) {
                        frameKey += "_" + direction.toString();
                    }
                    a = new Animation(frameKey, 0.5f);
                    now = System.currentTimeMillis();
                    a.update(0);
                    ANIMATIONS_CACHE.put(cacheKey, MutablePair.of(a, now));
                    return a;
                case ITEM:
                    break;
                case ENEMY:
                    assert direction != null;
                    frameKey = entity.getClass().getSimpleName() + "_" + direction.toString();
                    a = new Animation(frameKey, 0.5f);
                    now = System.currentTimeMillis();
                    a.update(0);
                    ANIMATIONS_CACHE.put(cacheKey, MutablePair.of(a, now));
                    return a;
                case PLAYER:
                    assert direction != null;
                    frameKey = "Walk_" + direction.toString();
                    a = new Animation(frameKey, 0.5f);
                    now = System.currentTimeMillis();
                    a.update(0);
                    ANIMATIONS_CACHE.put(cacheKey, MutablePair.of(a, now));
                    return a;
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
                return am.getFrames("Projectile_1")
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst()
                        .get();
            default:
                return null;
        }
    }

    private static BufferedImage scale(final BufferedImage before, final double scale) {
        final int w = (int)(before.getWidth() * scale);
        final int h = (int)(before.getHeight() * scale);
        final BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        final AffineTransformOp scaling = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaling.filter(before, after);
    }

    public static Optional<BufferedImage> getTileSprite(final Tile tile) {
        if(MAP_CACHE.get(tile) != null) {
            return Optional.of(MAP_CACHE.get(tile));
        }
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
        MAP_CACHE.put(tile, scale(tileImg, MAP_SCALING_FACTOR));
        return Optional.of(MAP_CACHE.get(tile));
    }

    public static int getMapTileSize() {
        final Optional<BufferedImage> maybeTile = getTileSprite(Tile.FLOOR);
        assert maybeTile.isPresent();
        final BufferedImage tile = maybeTile.get();
        assert tile.getHeight() == tile.getWidth();
        return tile.getHeight();
    }
}
