package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;


import java.awt.Graphics2D;
import java.awt.Color;

/**
 * This class manages the end of the game interface.
 */

public class EndGameState extends State {

    private final DungeonGame game;

    private final EndGameLogic logic;

    /**
     * EndGame constructor.
     *
     * @param game the game manager used
     */

    public EndGameState(final DungeonGame game) {
        super(game);
        this.game = game;
        this.logic = new EndGameLogicImpl(this.game);
    }

    /**
     * This method controls if the gamer wants to restart the game by using the space key.
     *
     * @param delta gap time from the previous render
     */

    @Override
    public void update(final long delta) {
        this.logic.continueGame(delta);

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
        g2.setFont(this.logic.getFont());
        g2.setColor(Color.WHITE);
        if (this.logic.isBlink()) {
            return;
        }
        final String msg = "Press space to continue";
        final float x = (game.getGameWidth() - FontUtils.getTextWidth(this.logic.getFont(), msg)) / 2f;
        final float y = (game.getGameHeight() - this.logic.getFont().getSize()) / 2f;
        g2.drawString(msg, x, y);
    }

}
