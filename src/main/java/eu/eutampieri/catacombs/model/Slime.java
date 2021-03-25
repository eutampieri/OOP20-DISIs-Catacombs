package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

/**
 *
 * Enemy Slime class.
 *
 */
public final class Slime extends Entity{

	private static final int HEIGHT = 3;
	private static final int WIDTH = 3;
	private static final int MOVEMENT_SPEED = 1;
	private static final int HEALTH = 10;
	private static final String NAME = "Slime";

	private Entity characterToFollow;
	private CollisionBox radarBox;

<<<<<<< HEAD
	/**
	 * Slime constructor.
	 * @param x X spawn position
	 * @param y Y spawn position
	 * @param tileMap Tile map in which Slime is spawned
	 */
=======
>>>>>>> 9ecb83a... Updated Slime.java and fixed bugs
	public Slime(int x, int y, TileMap tileMap) {
		super(x, y, tileMap);
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setSpeed(MOVEMENT_SPEED);
		setHealth(HEALTH);
		setAlive(true);
		face = FACE_RIGHT;
		hitBox = new CollisionBox(posX, posY, width, height);
		radarBox = new CollisionBox(posX - (width * 2), posY - (height * 2), width * 5, height * 5);

		// TODO Animations
	}

	@Override
	public void update(int delta) {
		resetMovement();
		follow();
		super.update(delta);
		updateRadarBoxLocation();
		
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

<<<<<<< HEAD
	/**
	 * Utility method useful and used in GameState to make the Slime follow an Entity (most likely the Player).
	 * 		With this method Slimes can follow every entity.
	 * @param e Entity to follow
	 */
=======
>>>>>>> 9ecb83a... Updated Slime.java and fixed bugs
	public void follow(Entity e) {
		if (radarBox.overlaps(e.getHitBox()))
			characterToFollow = e;
	}

<<<<<<< HEAD
	/**
	 * Makes Slime path towards the Character(Entity) to follow.
	 */
=======
>>>>>>> 9ecb83a... Updated Slime.java and fixed bugs
	private void follow() {
		if (characterToFollow == null){
			return;
		}
		if (characterToFollow.getPosX() < posX) {
			left = true;
		} else if (characterToFollow.getPosX() > posX) {
			right = true;
		}
		if (characterToFollow.getPosX() < posY) {
			up = true;
		} else if (characterToFollow.getPosX() > posY){
			down = true;
		}
	}

<<<<<<< HEAD
	/**
	 * Updates the aggro radar's Slime box.
	 */
=======
>>>>>>> 9ecb83a... Updated Slime.java and fixed bugs
	public void updateRadarBoxLocation() {
		radarBox.setLocation(posX - width * 2, posY - height * 2);
	}
	
}
