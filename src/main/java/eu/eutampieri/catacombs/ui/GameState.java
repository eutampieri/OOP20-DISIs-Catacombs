package eu.eutampieri.catacombs.ui;


import eu.eutampieri.catacombs.ui.utils.FontUtils;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameState extends State {

    /*private World world;
    private Player player;*/
    private boolean paused;

    private final Font font = new Font("Arial", Font.BOLD, 15);
    private final Font pauseFont = new Font("Monospace", Font.BOLD, 30);
    private final DungeonGame game;

    public GameState(final DungeonGame game){
        super(game);
        this.game = game;
        //TODO  WorldLoader class
        //this.player = world.getPlayer();
    }

    @Override
    public void update(final long delta){
        if(Game.KEY_MANAGER.isKeyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.paused = !paused;
        }
        if (this.paused){
            return;
        }
        // WorldLoader needed
        //this.world.update(delta);
    }

    @Override
    public void render(final Graphics2D g2){
        // WorldLoader needed
        //this.world.render(g2);
        g2.setColor(Color.orange);
        g2.setFont(font);
        g2.drawString("HEALTH : "/* + (int) this.player.getHealth()*/, 5, font.getSize());
        //g2.drawString("COINS : " + player.getCoins(), 5, font.getSize() * 2 + 10);

        if (this.paused) {
            g2.setFont(this.pauseFont);
            final String msg = "Paused";
            final float x = (game.getGameWidth() - FontUtils.getTextWidth(pauseFont, msg))/2f;
            final float y = (game.getGameHeight() - this.pauseFont.getSize())/2f;
            g2.drawString("Paused", x, y);
        }
    }
}
