package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.*;
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
    private static final double MAP_SCALING_FACTOR = 2.25;
    private final static double BULLET_SCALING_FACTOR = 0.25;
    private final static double BOSS_BULLET_SCALING_FACTOR = 1;
    private static final Map<Tile, BufferedImage> MAP_CACHE = new HashMap<>();
    private static final Map<Triple<Entity, Action, Direction>, Pair<Animation, Long>> ANIMATIONS_CACHE = new HashMap<>();
    private static final Map<StaticEntityKind, BufferedImage> STATIC_ASSETS_CACHE = new HashMap<>();

    private AssetManagerProxy() {
    }

    public static Animation getFrames(final Entity entity, final Action action, final Direction direction) {
        if (entity.canPerform(action)) {
            if (direction == null && action.getDirections().size() != 0) {
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
        final StaticEntityKind entityKind = StaticEntityKind.fromGameObject(entity);
        assert entityKind != null;
        final BufferedImage fromCache = STATIC_ASSETS_CACHE.get(entityKind);
        if (fromCache != null) {
            return fromCache;
        }
        final AssetManager am = AssetManager.getAssetManager();
        switch (entityKind) {
            case BULLET:
                final BufferedImage normal =  am.getFrames("Projectile_1")
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findAny()
                        .get();
                final BufferedImage resized = scale(normal, BULLET_SCALING_FACTOR);
                STATIC_ASSETS_CACHE.put(entityKind, resized);
                return resized;
            case BOSS_BULLET:
                final BufferedImage normal2 =  am.getFrames("Projectile_2")
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findAny()
                        .get();
                final BufferedImage resized2 = scale(normal2, BOSS_BULLET_SCALING_FACTOR);
                STATIC_ASSETS_CACHE.put(entityKind, resized2);
                return resized2;
            case POTION:
                return am.getImage("potion");
            default:
                return null;
        }
    }

    private static BufferedImage scale(final BufferedImage before, final double scale) {
        final int w = (int) (before.getWidth() * scale);
        final int h = (int) (before.getHeight() * scale);
        final BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        final AffineTransformOp scaling = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaling.filter(before, after);
    }

    public static Optional<BufferedImage> getTileSprite(final Tile tile) {
        if (MAP_CACHE.get(tile) != null) {
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

    private enum StaticEntityKind {
        BULLET,
        POTION,
        WEAPON,
        BOSS_BULLET;
        public static StaticEntityKind fromGameObject (final GameObject gameObject) {
            switch (gameObject.getKind()) {
                case BULLET:
                    return BULLET;
                case BOSS_BULLET:
                    return BOSS_BULLET;
                case PICKUP:
                    return gameObject instanceof Weapon ? WEAPON : POTION;
                default:
                    return null;
            }
        }
    }
}
