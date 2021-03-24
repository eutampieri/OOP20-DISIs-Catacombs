package eu.eutampieri.catacombs.model;

/**
 * Enemy Bat class
 *
 */
public class Bat extends GameObject implements LivingCharacter{ 
	public Bat(int x, int y) {
		super(x, y, ID.Enemy);
		
	}

	@Override
	public void update() {
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
