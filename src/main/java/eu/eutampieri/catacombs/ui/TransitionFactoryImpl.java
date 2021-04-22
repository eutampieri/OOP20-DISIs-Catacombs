package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.*;

public class TransitionFactoryImpl implements TransitionFactory{
    /**
     * This method will generate a State transition.
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @param font        the font for the message
     * @return            generate a transition
     */
    @Override
    public State generalTrans(final String message, final DungeonGame game, final State state, final Font font) {
        return new State(game) {

            private float waitTimer;
            private float waitTime = 100_000;


            @Override
            public void update(final long delta) {
                waitTimer += delta;

                if(waitTimer >= waitTime) {
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
     * This method is used to return the state we have generated
     * @param message     the message to show
     * @param game        the game manager used
     * @param state       the state that will be introduced
     * @param font        the font for the message
     * @return            the transition generated
     */
    @Override
    public State transState(final String message, final  DungeonGame game, final  State state, final  Font font) {
        return this.generalTrans(message, game, state, font);
    }

}
