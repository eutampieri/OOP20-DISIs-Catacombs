package eu.eutampieri.catacombs.model;

/**
 * The facing direction of an entity.
 */
public enum Direction {
    /**
     * Entity is facing right.
     */
    RIGHT,
    /**
     * Entity is facing left.
     */
    LEFT,
    /**
     * Entity is facing down.
     */
    DOWN,
    /**
     * Entity is facing up.
     */
    UP;

    @Override
    public final String toString() {
        switch (this) {
        case DOWN:
            return "down";
        case LEFT:
            return "left";
        case RIGHT:
            return "right";
        case UP:
            return "up";
        default:
            return "42";
        }
    }
}
