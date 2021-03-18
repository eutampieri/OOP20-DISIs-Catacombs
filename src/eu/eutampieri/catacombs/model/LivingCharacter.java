package eu.eutampieri.catacombs.model;

public interface LivingCharacter {
	public int getHealth();
	public void setHealth(final int health);
	public default boolean isAlive() {
		return this.getHealth() > 0;
	}
}