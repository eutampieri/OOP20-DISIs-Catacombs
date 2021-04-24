package eu.eutampieri.catacombs.ui;

import java.awt.Font;

/**
 * This interface manages the logic part of EndGameState.
 */

public interface EndGameLogic {
    /**
     * this method manages the generation of a new game proceeding to the menu state.
     * @param delta  the gap time between two frames
     */
    void continueGame(long delta);

    /**
     * This method returns the font utilized
     * @return the font utilized
     */

    Font getFont();

    /**
     * This method returns true if is blinking
     * @return true if is blinking
     */

    boolean isBlink();
}
