package eu.eutampieri.catacombs.model;

/**
 * This interface is required to be implemented for every object that causes a LivingCharacter to lose/gain life.
 */
public interface HealthModifier {
    /**
     * Method to get how much this object modifies character's health.
     * 
     * @return an integer that, summed to the initial health value, gives the
     *         resulting health value after the usage of this object
     */
    int getHealthDelta();

    /**
     * Apply the health delta onto the LivingCharacter's life.
     * @param character the character whose health will be modified.
     */
    default void useOn(LivingCharacter character) {
        int currentHealth = character.getHealth();
        currentHealth += this.getHealthDelta();
        character.setHealth(currentHealth);
    }

    /**
     * Describes the health modifier.
     * @return The friendly name for this HealthModifier
     */
    String getName();
}
