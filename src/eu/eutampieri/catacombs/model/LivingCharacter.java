package eu.eutampieri.catacombs.model;

public interface LivingCharacter {
	public int getHealth();
	public int setHealth();
	public default boolean isAlive() {
		return this.getHealth() > 0;
	}
}
