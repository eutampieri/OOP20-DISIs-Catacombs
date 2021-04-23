package eu.eutampieri.catacombs.ui;

import java.awt.Font;

public interface EndGameLogic {
    /**
     * this method manages the generation of a new game proceeding to the menu state.
     * @param delta  the gap time between two frames
     */
    void continueGame(long delta);

    /**
     *
     * @return the font utilized
     */

    Font getFont();

    /**
     *
     * @return true if is blinking
     */

    boolean isBlink();
}
