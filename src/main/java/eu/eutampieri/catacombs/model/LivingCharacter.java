package eu.eutampieri.catacombs.model;

public interface LivingCharacter {
    /**
     * Getter for LivingCharacter health.
     * @return LivingCharacter current health
     */
    int getHealth();
    /**
     * Please make sure that the resulting health is always >= 0 and <= 100.
     * @param health the new health value
     */
    void setHealth(int health);
    default boolean isAlive() {
        return this.getHealth() > 0;
    }
}
