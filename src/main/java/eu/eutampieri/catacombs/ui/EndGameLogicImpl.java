package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.input.KeyManager;

import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * This interface manages the logic part of EndGameState.
 */

public class EndGameLogicImpl implements EndGameLogic {

    private static final long BLINK_DELAY = 500;
    /**
     * Font used to write the end game message.
     */
    private final Font font = new Font("Monospace", Font.PLAIN, 40);
    private long blinkDelayCount;
    private boolean blink = true;
    private final DungeonGame game;

    public EndGameLogicImpl(final DungeonGame game) {
        this.game = game;
    }
    /**
     * this method manages the generation of a new game proceeding to the menu state.
     */
    @Override
    public void continueGame(final long delta) {
        this.blinkDelayCount += delta;
        if (this.blinkDelayCount >= BLINK_DELAY) {
            this.blinkDelayCount = 0;
            this.blink = !this.blink;
        }
        if (KeyManager.getKeyManager().isKeyPressed(KeyEvent.VK_SPACE)) {
            this.blinkDelayCount = 0;
            this.blink = false;
            this.game.setMenuState();
        }
    }
    /**
     * This method returns the font utilized.
     * @return the font utilized
     */
    @Override
    public Font getFont() {
        return font;
    }
    /**
     * The method returns true if is blinking.
     * @return true if is blinking
     */
    @Override
    public boolean isBlink() {
        return blink;
    }
}
