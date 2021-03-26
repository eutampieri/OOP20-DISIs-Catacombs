package eu.eutampieri.catacombs.ui;

import java.awt.Graphics2D;

public abstract class State {

    /**
     *
     * var used in the constructor
     *
     */

    private final DungeonGame game;

    /**
     *
     *
     * @param game
     *             manages the states
     */

    public State(final DungeonGame game) {
        this.game = game;
    }

    /**
     *
     * @param delta
     *              gap time from the previous render
     */

    public abstract void update(float delta);

    /**
     *
     * @param g2
     *              use to generate graphics
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
