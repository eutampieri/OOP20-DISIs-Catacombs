package eu.eutampieri.catacombs.model;

/**
 *
 * Abstract class for every game object (ex: Player, enemies, items, ecc...)
 *
 */
public abstract class GameObject {

    private int posX, posY;
    protected ID id;
    protected int velX, velY;

    /**
     * GameObject constructor base
     *
     * @param x  object X position
     * @param y  object Y position
     * @param id object ID @see eu.eutampieri.catacombs.model.ID
     */
    public GameObject(final int x, final int y, final ID id) {
        this.posX = x;
        this.posY = y;
        this.id = id;
    }

    /**
     *
     * @param delta time between updates
     */
    public abstract void update(int delta);

    public abstract void render();

    /**
     *
     * @return Object X position
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @param posX set object X position
     */
    public void setPosX(final int posX) {
        this.posX = posX;
    }

    /**
     *
     * @return Object Y position
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @param posY set object Y position
     */
    public void setPosY(final int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(final int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(final int velY) {
        this.velY = velY;
    }

    public void setBothVelAsEquals(final int vel) {
        this.velX = vel;
        this.velY = vel;
    }

    public ID getId() {
        return id;
    }

    public void setId(final ID id) {
        this.id = id;
    }

}
