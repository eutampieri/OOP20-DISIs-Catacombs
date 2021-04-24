package eu.eutampieri.catacombs.model;

/**
 * This interface is required to handle health and to allow HealthModifiers to increase or decrease it.
 */
public interface LivingCharacter {
    /**
     * Getter for LivingCharacter health.
     * 
     * @return LivingCharacter current health
     */
    int getHealth();

    /**
     * Please make sure that the resulting health is always &gt;= 0 and &lt;= 100.
     * 
     * @param health the new health value
     */
    void setHealth(int health);

    /**
     * Check if the character is alive.
     * @return true if the entity is alive.
     */
    default boolean isAlive() {
        return this.getHealth() > 0;
    }
}
