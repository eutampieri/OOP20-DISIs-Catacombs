package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;
import eu.eutampieri.catacombs.model.Direction;
import eu.eutampieri.catacombs.model.Entity;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AssetManagerProxy {
    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static List<BufferedImage> getFrames(Entity entity, Action action, Direction direction) {
        AssetManager am = AssetManager.getAssetManager();
        if(entity.canPerform(action)) {
            if(direction == null && action.getDirections().size() != 0) {
                throw new IllegalArgumentException();
            } else if (direction != null && action.getDirections().size() == 0) {
                throw new IllegalArgumentException();
            }
            switch (entity.getKind()) {
                case BOSS:
                    String frameKey = "Boss_" + AssetManagerProxy.capitalize(action.toString());
                    if (direction != null) {
                        frameKey += "_" + direction.toString();
                    }
                    return am.getFrames(frameKey).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                case ITEM:
                    break;
                case ENEMY:
                    frameKey = entity.getClass().getSimpleName() + "_" + direction.toString();
                    return am.getFrames(frameKey).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
                case PICKUP:
                    return null;
                    //TODO
                case PLAYER:
                    return null;
                    //TODO
                case WEAPON:
                    return null;
                    //TODO
            }
        } else {
            throw new IllegalArgumentException();
        }
        // We should never get here
        throw new IllegalStateException();
    }
}
