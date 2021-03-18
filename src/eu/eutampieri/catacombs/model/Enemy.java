package eu.eutampieri.catacombs.model;

public class Enemy extends GameObject implements LivingCharacter{ //general enemy for example purpose
	private int health;
	
	public Enemy(int x, int y, ID id) {
		super(x, y, id);
		health = 100;
	}

	@Override
	public int getHealth() {
		return this.health;
	}
	
	@Override
	public void setHealth(int health) {
		this.health = health;
	}

}
