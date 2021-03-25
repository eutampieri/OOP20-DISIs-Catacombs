package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

/**
 * Enemy Bat class.
 *
 */
public final class Bat extends Entity {

	private static final int HEIGHT = 2;
	private static final int WIDTH = 2;
	private static final int MOVEMENT_SPEED = 1;
	private static final int HEALTH = 8;
	private static final String NAME = "Bat";

	private SimpleWeapon weapon;
	private boolean isMoving;
	private int moveDelay, delayCounter;
	private int pauseDelay, pauseCounter;
	private CollisionBox radarBox;

	/**
	 * Bat constructor.
	 * @param x X spawn position
	 * @param y Y spawn position
	 * @param tileMap Tile map in which Bat is spawned
	 */
	public Bat(final int x, final int y, final TileMap tileMap) {
		super(x, y, tileMap);
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setSpeed(MOVEMENT_SPEED);
		setHealth(HEALTH);
		setAlive(true);
		face = FACE_RIGHT;
		hitBox = new CollisionBox(posX, posY, width, height);
		radarBox = new CollisionBox(posX - width * 4, posY - width * 4, width * 9, height * 9);
        weapon = new SimpleWeapon(4, "bat_wpn", 4, 9, -1){};

		// TODO Animations

	}

	@Override
	public void update(int delta) {
		if (isMoving) {
			delayCounter += delta;
			if (delayCounter >= moveDelay) {
				moveDelay = 0;
				isMoving = false;
				resetMovement();
			}
		} else {
			pauseCounter += delta;
			if (pauseCounter >= pauseDelay) {
				pauseCounter = 0;
				isMoving = true;
				changeDirection();
			}
		}
		super.update(delta);
		updateRadarBoxLocation();
		weapon.update(delta);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHealth() {
		return this.hp;
	}

	@Override
	public void setHealth(int health) {
		this.hp = health;
	}

	/**
	 * Utility class that makes the bat change movement direction. As of now bats can only go left or right.
	 */
	private void changeDirection() {
		if (face == FACE_RIGHT) {
			left = true;
			right = false;
			face = FACE_LEFT;
		} else {
			right = true;
			left = false;
			face = FACE_RIGHT;
		}
	}

	/**
	 * Updates the aggro radar's Bat box.
	 */
	private void updateRadarBoxLocation() {
		radarBox.setLocation(posX - width * 4, posY - height * 4);
	}

}
