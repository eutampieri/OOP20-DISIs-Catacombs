package eu.eutampieri.catacombs.model;

public interface LivingCharacter {
    int getHealth();
    /**
     * Please make sure that the resulting health is always >= 0 and <= 100.
     * @param health the new health value
     */
    void setHealth(final int health);
    default boolean isAlive() {
        return this.getHealth() > 0;
    }
}
