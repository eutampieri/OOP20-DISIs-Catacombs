package eu.eutampieri.catacombs.ui;

import java.awt.Font;

/**
 * this class manages the transitions between states.
 */

public interface TransitionFactory {
    /**
     * This method will generate a State transition.
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @param font        the font for the message
     * @return            generate a transition
     */

    State generalTrans(String message, DungeonGame game, State state, Font font);
    /**
     * This method is used to return the state we have generated
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @param font        the font for the message
     * @return            the transition generated
     */

    State transState(String message, DungeonGame game, State state, Font font);

}
