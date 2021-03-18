package eu.eutampieri.catacombs.model;

/**
 * A potion that will increase a player's health by a fixed amount
 */
public class SimplePotion implements HealthModifier {
	private final int healing;
	private final String name;
	
	public SimplePotion(int healing, String name) {
		this.healing = healing;
		this.name = name;
	}

	@Override
	public int getHealthDelta() {
		return this.healing;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
