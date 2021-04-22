package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.utils.FontUtils;


import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * This class manages the end of the game interface.
 */

public class EndGameState extends State {

    private static final float BLINK_DELAY = 500;
    /**
     * Font used to write the end game message.
     */
    private final Font font = new Font("Monospace", Font.PLAIN, 40);
    private float blinkDelayCount;
    private boolean blink = true;
    private final DungeonGame game;

    /**
     * EndGame constructor.
     *
     * @param game the game manager used
     */

    public EndGameState(final DungeonGame game) {
        super(game);
        this.game = game;
    }

    /**
     * This method controls if the gamer wants to restart the game by using the space key.
     *
     * @param delta gap time from the previous render
     */

    @Override
    public void update(final long delta) {
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
     * this method renders the end game message and
     * the instruction to start a new game.
     *
     * @param g2 use to generate graphics
     */

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        if (this.blink) {
            return;
        }
        final String msg = "Press space to continue";
        final float x = (game.getGameWidth() - FontUtils.getTextWidth(this.font, msg)) / 2f;
        final float y = (game.getGameHeight() - this.font.getSize()) / 2f;
        g2.drawString(msg, x, y);
    }

}
