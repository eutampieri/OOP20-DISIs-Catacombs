package eu.eutampieri.catacombs.model;

import java.util.List;

/**
 * A potion that will increase a player's health by a fixed amount.
 */
public final class SimplePotion extends GameObject implements HealthModifier {
    private final int healthDelta;
    private final String name;
    private static final int SIZE = 12;
    private boolean used;

    public SimplePotion(final int healing, final String name, final int x, final int y) {
        super(x, y, GameObjectType.PICKUP, new CollisionBox(x, y, SIZE, SIZE), Team.FREIND);
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

    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        return List.of();
    }

    @Override
    public void useOn(final LivingCharacter character) {
        int currentHealth = character.getHealth();
        currentHealth += this.getHealthDelta();
        character.setHealth(currentHealth);
        this.used = true;
    }

    @Override
    public boolean isMarkedForDeletion() {
        return this.used;
    }
}
