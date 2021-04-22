package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
/**
 * This class is used to end the game and let the user choose to start again.
 */
public class EndTransition extends State {

    /**
     * The game that will end after the transition.
     */
    private EndGameState levelState;
    /**
     * The font used to write the new game message during the transition.
     */
    private final Font font = new Font("Monospace", Font.PLAIN, 40);
    /**
     * The game manager used.
     */
    private final DungeonGame game;

    /**
     * EndTransition constructor.
     *
     * @param game the game manager used
     */

    public EndTransition(final DungeonGame game) {
        super(game);
        this.game = game;
    }

    /**
     * This method changes the state from GameState to EndGameState
     *
     * @param delta gap time from the previous render
     */

    @Override
    public void update(final long delta) {
        game.setState(levelState);
    }

    /**
     * Renders the dead message during transition.
     *
     * @param g2 use to generate graphics
     */

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
        final String newGame = "DEAD";
        g2.setFont(this.font);
        g2.setColor(Color.WHITE);
        g2.drawString(newGame, (this.game.getGameWidth() - FontUtils.getTextWidth(font, newGame)) / 2,
                (this.game.getGameHeight() - font.getSize()) / 2);
    }

    /**
     * Make the transition start.
     *
     * @param levelState the EndGameState to set
     */

    public void endTransition(final EndGameState levelState) {
        this.levelState = levelState;
    }

}
