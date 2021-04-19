package eu.eutampieri.catacombs.model;

import java.util.List;

public enum Action {
    /**
     * When a character changes its position, i.e. when it walks
     */
    MOVE,
    /**
     * When a character causes harm to another one, i.e. when it shoots
     */
    ATTACK,
    /**
     * When a character dies.
     */
    DIE,
    /**
     * When a character stands still
     */
    IDLE;

    public List<Direction> getDirections() {
        switch (this) {
            case ATTACK:
            case MOVE:
                return List.of(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT);
            case IDLE:
                return List.of(Direction.LEFT, Direction.RIGHT);
            case DIE:
            default:
                return List.of();
        }
    }

    public static Action fromString(final String name) {
        switch (name) {
            case "move":
                return MOVE;
            case "attack":
                return ATTACK;
            case "die":
                return DIE;
            case "idle":
                return IDLE;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case DIE:
                return "die";
            case IDLE:
                return "idle";
            case ATTACK:
                return "attack";
            case MOVE:
                return "move";
            default:
                throw new IllegalStateException();
        }
    }
}