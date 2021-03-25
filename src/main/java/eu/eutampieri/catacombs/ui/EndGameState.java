package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EndGameState extends State {

    private Font font = new Font("Monospace", Font.PLAIN, 40);
    private float blinkDelayCount;
    private float blinkDelay = 1f;
    private boolean blink = true;

    public EndGameState(DungeonGame game) {
        super(game);
    }

    public void update (float delta) {
        this.blinkDelayCount *= delta;
        if (this.blinkDelayCount >= this.blinkDelay) {
            this.blinkDelayCount = 0;
            this.blink = !this.blink;
        }
        if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_SPACE)) {
            this.blinkDelayCount = 0;
            this.blink = false;
            this.game.setMenuState();
        }


    }

    public void render (Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());
        String level = "GAME COMPLETED!";
        g2.setFont(font);
        g2.setColor(Color.WHITE);

        float x = (Game.getGameWidth() - FontUtils.getTextWidth(this.font, level))/2;
        float y = (Game.getGameHeight() - this.font.getSize())/2;
        g2.drawString(level, x, y);

        if (this.blink){
            return;
        }
        String msg = "Press space to continue";
        x = (Game.getGameWidth() - FontUtils.getTextWidth(this.font, msg)) / 2;
        y = y + font.getSize() * 3;
        g2.drawString(msg, x, y);
    }

}
