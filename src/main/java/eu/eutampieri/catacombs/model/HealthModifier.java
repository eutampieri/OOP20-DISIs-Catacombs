package eu.eutampieri.catacombs.model;

public interface HealthModifier {
    /**
     * Method to get how much this object modifies character's health.
     * @return an integer that, summed to the initial health value, gives
     * the resulting health value after the usage of this object
     */
    int getHealthDelta();
    default void useOn(LivingCharacter character) {
        int currentHealth = character.getHealth();
        currentHealth += this.getHealthDelta();
        character.setHealth(currentHealth);
    }

    String getName();
}
