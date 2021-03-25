package eu.eutampieri.catacombs.model;

/**
 * A potion that will increase a player's health by a fixed amount.
 */
public final class SimplePotion implements HealthModifier {
    private final int healthDelta;
    private final String name;

    public SimplePotion(final int healing, final String name) {
        this.healthDelta = healing;
        this.name = name;
    }

    @Override
    public int getHealthDelta() {
        return this.healthDelta;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
