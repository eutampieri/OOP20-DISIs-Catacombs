package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;
import eu.eutampieri.catacombs.model.Direction;
import eu.eutampieri.catacombs.model.GameObject;
import org.apache.commons.lang3.StringUtils;

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
                    frameKey = entity.getClass().getSimpleName() + "_" + direction.toString();
                    return am.getFrames(frameKey).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                case PLAYER:
                    return null;
                    //TODO
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
}
