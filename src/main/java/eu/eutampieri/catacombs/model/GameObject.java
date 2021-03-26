package eu.eutampieri.catacombs.model;

/**
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
	public GameObject(final int x, final int y, final ID id) {
		this.posX = x;
		this.posY = y;
		this.id = id;
	}

	/**
	 * Method used in the game while that updates all elements of a game obj.
	 * @param delta time between updates
	 */
	public abstract void update(int delta);
	public abstract void render();

	/**
	 * Getter for object X position.
	 * @return Object X position
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Setter for object X position.
	 * @param posX Object X position to be set
	 */
	public void setPosX(final int posX) {
		this.posX = posX;
	}

	/**
	 * Getter for object Y position.
	 * @return Object Y position
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Setter for object Y position.
	 * @param posY Object Y position to be set
	 */
	public void setPosY(final int posY) {
		this.posY = posY;
	}

	/**
	 * Getter for object speed on the X axis.
	 * @return Object X speed
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * Setter for object speed on the X axis.
	 * @param speedX Speed to be set
	 */
	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}

	/**
	 * Getter for object speed on the Y axis.
	 * @return Object Y speed
	 */
	public int getSpeedY() {
		return speedY;
	}

	/**
	 * Setter for object speed on the Y axis.
	 * @param speedY Speed to be set
	 */
	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}

	/**
	 * Set both speeds to a specified value (both X and Y).
	 * @param speed Speed to be set
	 */
	public void setSpeed(final int speed) {
		this.speedX = speed;
		this.speedY = speed;

	}

	/**
	 * Getter for object ID.
	 * @return Object id
	 */
	public ID getId() {
		return id;
	}

}
