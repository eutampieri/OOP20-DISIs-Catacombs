package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EndGameState extends State {

    private static final float BLINK_DELAY = 1f;

    private final Font font = new Font("Monospace", Font.PLAIN, 40);
    private float blinkDelayCount;
    private boolean blink = true;
    private final DungeonGame game;

    public EndGameState(final DungeonGame game) {
        super(game);
        this.game = game;
    }

    @Override
    public void update (final long delta) {
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

    @Override
    public void render (final Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
        final String level = "GAME COMPLETED!";
        g2.setFont(font);
        g2.setColor(Color.WHITE);

        float x = (game.getGameWidth() - FontUtils.getTextWidth(this.font, level)) / 2f;
        float y = (game.getGameHeight() - this.font.getSize()) / 2f;
        g2.drawString(level, x, y);

        if (this.blink){
            return;
        }
        final String msg = "Press space to continue";
        x = (game.getGameWidth() - FontUtils.getTextWidth(this.font, msg)) / 2f;
        y = y + font.getSize() * 3;
        g2.drawString(msg, x, y);
    }

}
