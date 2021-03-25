package eu.eutampieri.catacombs.model;

/**
 *
 * Abstract class for every game object (ex: Player, enemies, items, ecc...)
 *
 */
public abstract class GameObject {

    protected int posX;
    protected int posY;
    protected ID id;
    protected int velX;
    protected int velY;

    /**
     * GameObject constructor base
     *
     * @param x object X position
     * @param y	object Y position
     * @param id object ID @see eu.tampieri.catacombs.model.ID
     */
    public GameObject(int x, int y, ID id) {
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
    public void setPosX(int posX) {
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
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
