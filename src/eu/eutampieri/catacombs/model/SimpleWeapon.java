package eu.eutampieri.catacombs.model;

/**
 * 
 * A simple weapon that substracts a given value from a player's health
 *
 */
public class SimpleWeapon implements HealthModifier {
	private final int damage;
	
	/**
	 * 
	 * @param damage The amount of damage (i.e. the change in health) that a character
	 * will have after being hit with this weapon
	 */
	SimpleWeapon(int damage) {
		this.damage = 0;
	}

	@Override
	public int getHealthDelta() {
		return damage * -1;
	}

}
