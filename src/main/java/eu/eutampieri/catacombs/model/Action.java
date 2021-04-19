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
    DIE;

    public List<Direction> getDirections() {
        switch (this) {
            case ATTACK:
            case MOVE:
                return List.of(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT);
            case DIE:
            default:
                return List.of();
        }
    }

    public static Action fromString(String name) throws IllegalArgumentException {
        switch (name) {
            case "move":
                return MOVE;
            case "attack":
                return ATTACK;
            case "die":
                return DIE;
            default:
                throw new IllegalArgumentException();
        }
    }
}