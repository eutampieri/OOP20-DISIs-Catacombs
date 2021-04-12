package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.*;

public class StartTransition extends State{

    private static final float WAIT_TIME = 2.5f;

    private GameState levelState;
    private float waitTimer;
    private final Font font = new Font("Monospace", Font.PLAIN, 40);
    private final DungeonGame game;

    public StartTransition(final DungeonGame game) {
        super(game);
        this.game = game;
    }

    @Override
    public void update(final float delta) {
        this.waitTimer += delta;

        if (this.waitTimer >= WAIT_TIME) {
            this.waitTimer = 0;
            game.setState(levelState);
        }
    }

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
        final String newGame = "New Game";
        g2.setFont(this.font);
        g2.setColor(Color.WHITE);
        g2.drawString(newGame, (this.game.getGameWidth() - FontUtils.getTextWidth(font, newGame)) / 2, (this.game.getGameHeight() - font.getSize()) / 2);
    }

    public void startTransition(final GameState levelState) {
        this.levelState = levelState;
    }


}
