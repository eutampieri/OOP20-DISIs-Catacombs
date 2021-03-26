package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

/**
 * Enemy Bat class
 *
 */
public class Bat extends Entity {

	private SimpleWeapon weapon;
	private boolean isMoving;
	private float moveDelay;

	public Bat(int x, int y, TileMap tileMap) {
		super(x, y, tileMap);
		setHeight(16);
		setWidth(16);
		setBothVelAsEquals(1);
		setHealth(8);

		// TODO Animations

		// box = new CollisionBox();

		// TODO Enemy targeting box
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHealth(int health) {
		// TODO Auto-generated method stub

	}

}
