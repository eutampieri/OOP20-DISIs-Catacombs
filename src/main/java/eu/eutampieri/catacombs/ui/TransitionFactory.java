package eu.eutampieri.catacombs.ui;


/**
 * this class manages the transitions between states.
 */

public interface TransitionFactory {
    /**
     * This method is used to return the state we have generated.
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @return            the transition generated
     */

    State transState(String message, DungeonGame game, State state);

}
