package eu.eutampieri.catacombs.model;

public final class Camera {

    private int xOffset;
    private int yOffset;

    private final int mapWidth, mapHeight;

    public Camera(final int xOffset, final int yOffset, final int mapWidth, final int mapHeight) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    /**
     * Centers camera on selected entity.
     * 
     * @param e          Entity to center onto
     * @param gameWidth  game width
     * @param gameHeight game height
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
