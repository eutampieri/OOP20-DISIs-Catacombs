package eu.eutampieri.catacombs.ui;

import java.awt.Graphics2D;

/**
 * This class is a generic State of the game, starting from this abstract class
 * are generated all the others states of the game.
 */

public abstract class State {

    /**
     * Var used in the constructor.
     *
     */

    private final DungeonGame game;

    /**
     * General State constructor.
     *
     * @param game manages the states
     */

    public State(final DungeonGame game) {
        this.game = game;
    }

    /**
     * The implementation of this method depends on the state.
     *
     * @param delta gap time from the previous render
     */

    public abstract void update(long delta);

    /**
     * The implementation of this method depends on the state.
     *
     * @param g2 use to generate graphics
     */

    public abstract void render(Graphics2D g2);

    /**
     *
     * @return the game manager used
     */

    public final DungeonGame getGame() {
        return game;
    }

}
