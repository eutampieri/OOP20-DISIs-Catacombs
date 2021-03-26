package eu.eutampieri.catacombs.model;

/**
 * A general class for GameObjects boxes which define an imaginary outline used for
 * collision detection and aggro detection.
 */
public class CollisionBox {
    /**
     * The (X,Y) positions of CollisionBox top-left corner.
     */
    private int posX, posY;
    /**
     * Dimensions of the CollisionBox.
     */
    private int width, height;

    /**
     * General collision box constructor.
     * @param posX Box X position
     * @param posY Box Y position
     * @param width Box width dimension
     * @param height Box height dimension
     */
    public CollisionBox(final int posX, final int posY, final int width, final int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    /**
     * Collision box constructor that copies values from another CollisionBox.
     * @param box The collision box to duplicate
     */
    public CollisionBox(final CollisionBox box) {
        this.posX = box.posX;
        this.posY = box.posY;
        this.width = box.width;
        this.height = box.height;
    }

    /**
     * Method that moves the collision box by specified values.
     * @param dx Movement made in the X axis
     * @param dy Movement made in the Y axis
     */
    public void move(final int dx, final int dy) {
        this.posX += dx;
        this.posY += dy;
    }

    /**
     * Looks if two boxes overlaps.
     * @param r CollisionBox that may overlap
     * @return true if the two boxes overlaps; false otherwise
     */
    public boolean overlaps(final CollisionBox r) {
        return posX < r.posX + r.width && posX + width > r.posX && posY < r.posY + r.height && posY + height > r.posY;
    }

    /**
     * Getter for box X position.
     * @return Box X position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Setter for box X position.
     * @param posX X position to set
     */
    public void setPosX(final int posX) {
        this.posX = posX;
    }

    /**
     * Getter for box Y position.
     * @return Box Y position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Setter for box Y position.
     * @param posY Y position to set
     */
    public void setPosY(final int posY) {
        this.posY = posY;
    }

    /**
     * Getter for box width.
     * @return Box width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter for box width.
     * @param width Width dimension to set
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Getter for box height.
     * @return Box height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for box width.
     * @param height Width dimension to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Method that set box location to a specified (x,y).
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        this.setPosX(x);
        this.setPosY(y);
    }

    /**
     * Method that set box dimensions ot a specified width and height.
     * @param width
     * @param height
     */
    public void setDimensions(final int width, final int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * DEBUG method that shows the box in-game.
     */
    public void debugShowBox() {
        // TODO
    }

}
