package eu.eutampieri.catacombs.model;

/**
 *
 * Enemy Slime class
 *
 */
public class Slime extends GameObject implements LivingCharacter{

	public Slime(int x, int y) {
		super(x, y, ID.Enemy);
		
	}

	@Override
	public void update(float delta) {
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
