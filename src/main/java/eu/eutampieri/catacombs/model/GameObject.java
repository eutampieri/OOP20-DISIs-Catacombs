package eu.eutampieri.catacombs.model;

/**
 *
 * Abstract class for every game object (ex: Player, enemies, items, ecc...).
 */
public abstract class GameObject {

	protected int posX, posY;
	protected ID id;
	protected int speedX, speedY;
	
	/**
	 * GameObject constructor.
	 * 
	 * @param x object X position
	 * @param y	object Y position
	 * @param id object ID @see eu.eutampieri.catacombs.model.ID
	 */
	public GameObject(int x, int y, ID id) {
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

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int velX) {
		this.speedX = velX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(final int velY) {
		this.speedY = velY;
	}

	public void setSpeed(final int vel) {
		this.speedX = vel;
		this.speedY = vel;

	}

    public ID getId() {
        return id;
    }

    public void setId(final ID id) {
        this.id = id;
    }

}
