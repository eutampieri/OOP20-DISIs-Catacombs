package eu.eutampieri.catacombs.model;

public class Player extends GameObject implements LivingCharacter{
	private int health = 100;

	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public void setHealth(final int health) {
		this.health = health;
	}
	
}
