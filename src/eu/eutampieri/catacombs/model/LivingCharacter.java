package eu.eutampieri.catacombs.model;

public interface LivingCharacter {
	public int getHealth();
	/**
	 * Please make sure that the resulting health is always >= 0 and <= 100
	 */
	public void setHealth(final int health);
	public default boolean isAlive() {
		return this.getHealth() > 0;
	}
}
