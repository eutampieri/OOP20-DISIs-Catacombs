package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.map.TileMapFactory;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * This class manages the graphical aspects of the game interface.
 */

public class GameState extends State {
    /**
     * x offset of health position.
     */
    private static final int HEALTH_POS = 5;
    /**
     * The world that has been generated.
     */
    private final World world;
    // private Player player;
    /**
     * This varaible is used to pause the game.
     */
    private boolean paused;

    /**
     * The font used to write player statistics.
     */
    private final Font font = new Font("Arial", Font.BOLD, 15);
    /**
     * The font used to write Pause.
     */
    private final Font pauseFont = new Font("Monospace", Font.BOLD, 30);
    /**
     * the game manager used.
     */
    private final DungeonGame game;

    /**
     * GameState constructor.
     *
     * @param game the game manager used
     */

    public GameState(final DungeonGame game) {
        super(game);
        this.game = game;
        final TileMapFactory tmf = new TileMapFactoryImpl();
        this.world = new World(tmf.def(), this.game);
        // TODO WorldLoader class
        // this.player = world.getPlayer();
    }

    /**
     * This method controls if the game should be paused by the pressure of the key Esc
     * and update the world tha has been generated.
     *
     * @param delta gap time from the previous render
     */

    @Override
    public void update(final long delta) {
        if (KeyManager.getKeyManager().isKeyPressed(KeyEvent.VK_ESCAPE)) {
            this.paused = !paused;
        }
        if (this.paused) {
            return;
        }
        // WorldLoader needed
        this.world.update(delta);
    }

    /**
     * This method renders the Pause message and the entire World.
     *
     * @param g2 use to generate graphics
     */

    @Override
    public void render(final Graphics2D g2) {
        // WorldLoader needed
        this.world.render(g2);
        g2.setColor(Color.orange);
        g2.setFont(font);
        g2.drawString("HEALTH : " + this.world.getPlayer().getHealth(), HEALTH_POS, font.getSize());
        // g2.drawString("COINS : " + player.getCoins(), 5, font.getSize() * 2 + 10);

        if (this.paused) {
            g2.setFont(this.pauseFont);
            final String msg = "Paused";
            final float x = (game.getGameWidth() - FontUtils.getTextWidth(pauseFont, msg)) / 2f;
            final float y = (game.getGameHeight() - this.pauseFont.getSize()) / 2f;
            g2.drawString("Paused", x, y);
        }
    }
}
