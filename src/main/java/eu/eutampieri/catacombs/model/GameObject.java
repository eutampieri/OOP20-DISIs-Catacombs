package eu.eutampieri.catacombs.model;

/**
 *
 * Abstract class for every game object (ex: Player, enemies, items, ecc...).
 *
 */
public abstract class GameObject {

    /**
     * The current x coordinate of the object.
     */
    private int posX;
    /**
     * The current y coordinate of the object.
     */
    private int posY;
    /**
     * The kind of game object.
     */
    private final ID id;
    /**
     * The x component of the object's velocity.
     */
    private int velX;
    /**
     * The y component of the object's velocity.
     */
    private int velY;

    /**
     * GameObject constructor base.
     *
     * @param x object X position
     * @param y object Y position
     * @param id object ID @see eu.eutampieri.catacombs.model.ID
     */
    public GameObject(final int x, final int y, final ID id) {
        this.posX = x;
        this.posY = y;
        this.id = id;
    }

    public abstract void update();
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

    /**
     *
     * @return Object's X velocity
     */
    public int getVelX() {
        return velX;
    }

    /**
     * Set the object's current X velocity.
     * @param velX
     */
    public void setVelX(final int velX) {
        this.velX = velX;
    }

    /**
     *
     * @return Object's Y velocity
     */
    public int getVelY() {
        return velY;
    }

    /**
     * Set the object's current Y velocity.
     * @param velY
     */
    public void setVelY(final int velY) {
        this.velY = velY;
    }

    /**
     *
     * @return The object's kind
     */
    public ID getId() {
        return id;
    }

}
