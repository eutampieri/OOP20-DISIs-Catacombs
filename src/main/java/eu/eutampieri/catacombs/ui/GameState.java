package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.ui.utils.FontUtils;
//import eu.eutampieri.catacombs.ui.world.World;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private World world;
    private Player player;
    private boolean paused;

    private Font font = new Font("Arial", Font.BOLD, 15);
    private Font pauseFont = new Font("Monospace", Font.BOLD, 30);
    private DungeonGame game = new DungeonGame();

    public GameState(DungeonGame game){
        super(game);
        this.game = game;
        player = world.getPlayer();
    }

    public void update(float delta){
        if(Game.KEY_MANAGER.isKeyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.paused = !paused;
        }
        if (this.paused){
            return;
        }
        this.world.update(delta);
    }

    public void render(Graphics2D g2){
        this.world.render(g2);
        g2.setColor(Color.orange);
        g2.setFont((font));
        g2.drawString(("HEALTH : ") + (int) this.player.getHealth(), 5, font.getSize());
        //g2.drawString("COINS : " + player.getCoins(), 5, font.getSize() * 2 + 10);

        if (this.paused) {
            g2.setFont(this.pauseFont);
            String msg = "Paused";
            float x = (game.getGameWidth() - FontUtils.getTextWidth(pauseFont, msg))/2;
            float y = (game.getGameHeight() - this.pauseFont.getSize())/2;
            g2.drawString("Paused", x, y);
        }
    }
}
