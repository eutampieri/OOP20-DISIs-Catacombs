package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public final class Camera {

    private int xOffset;
    private int yOffset;
    private final GameConfiguration game;

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
    public void centerOnEntity(final GameObject e, final int gameWidth, final int gameHeight) {
        xOffset = e.getPosX() - (gameWidth / 2);
        yOffset = e.getPosY() - (gameHeight / 2);
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > (mapWidth - gameWidth)) {
            xOffset = mapWidth - gameWidth;
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > (mapHeight - gameHeight)) {
            yOffset = mapHeight - gameHeight;
        }
        //System.out.println(gameWidth + " x " + gameHeight + " and " + xOffset + " x " + yOffset);
    }

    /**
     *
     * @return Camera x offset.
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     *
     * @return Camera y offset.
     */
    public int getYOffset() {
        return yOffset;
    }
}
