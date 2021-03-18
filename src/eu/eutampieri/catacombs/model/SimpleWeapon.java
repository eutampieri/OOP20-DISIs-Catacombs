package eu.eutampieri.catacombs.model;

/**
 * 
 * A simple weapon that substracts a given value from a player's health
 *
 */
public class SimpleWeapon implements HealthModifier {
	private final int damage;
	private final String name; 
	
	/**
	 * 
	 * @param damage The amount of damage (i.e. the change in health) that a character
	 * will have after being hit with this weapon
	 * @param name The weapon's name
	 */
	SimpleWeapon(int damage, String name) {
		this.damage = damage;
		this.name = name;
	}

	@Override
	public int getHealthDelta() {
		return damage * -1;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
