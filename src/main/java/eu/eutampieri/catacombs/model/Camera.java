package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public final class Camera {

    private int xOffset;
    private int yOffset;
    private GameConfiguration game;

    private final int mapWidth, mapHeight;

    public Camera(final int xOffset, final int yOffset, final int mapWidth, final int mapHeight) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.mapWidth = mapWidth; //should be multiplied by tile dimensions
        this.mapHeight = mapHeight;
        this.game = new GameConfiguration();
    }

    /**
     * Centers the camera onto a specified Entity.
     * @param e Entity to center the camera upon
     */
    public void centerOnEntity(final GameObject e) {
        xOffset = e.getPosX() - (game.getGameWidth() / 2);
        yOffset = e.getPosY() - (game.getGameHeight() / 2);
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > (mapWidth - game.getGameWidth())) {
            xOffset = mapWidth - game.getGameWidth();
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > mapHeight - game.getGameWidth()) {
            yOffset = mapHeight - game.getGameHeight();
        }
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
