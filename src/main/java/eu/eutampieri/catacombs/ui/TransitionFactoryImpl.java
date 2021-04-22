package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

public class TransitionFactoryImpl implements TransitionFactory {
    /**
     * This method will generate a State transition.
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @return            generate a transition
     */
    private State generalTrans(final String message, final DungeonGame game, final State state) {
        return new State(game) {
            private static final int WAIT_TIMER = 100_000;

            private final Font font = new Font("Monospace", Font.PLAIN, 40);

            private float waitTimer;
            private float waitTime = WAIT_TIMER;


            @Override
            public void update(final long delta) {
                waitTimer += delta;

                if (waitTimer >= waitTime) {
                    waitTimer = 0;
                    game.setState(state);
                }
            }

            @Override
            public void render(final Graphics2D g2) {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
                final String mes = message;
                g2.setFont(font);
                g2.setColor(Color.WHITE);
                g2.drawString(mes, (game.getGameWidth() - FontUtils.getTextWidth(font, mes)) / 2,
                        (game.getGameHeight() - font.getSize()) / 2);
            }

        };
    }
    /**
     * This method is used to return the state we have generated.
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @return            the transition generated
     */
    @Override
    public State transState(final String message, final  DungeonGame game, final  State state) {
        return this.generalTrans(message, game, state);
    }

}
