package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

import java.awt.*;

public class StartTransition extends State{

    private static final float WAIT_TIME = 2.5f;

    private GameState levelState;
    private float waitTimer;
    private Font font = new Font("Monospace", Font.PLAIN, 40);
    private DungeonGame game = new DungeonGame();

    public StartTransition(DungeonGame game) {
        super(game);
        this.game = game;
    }

    @Override
    public void update(float delta) {
        this.waitTimer += delta;

        if (this.waitTimer >= WAIT_TIME) {
            this.waitTimer = 0;
            game.setState(levelState);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
        String newGame = "New Game";
        g2.setFont(this.font);
        g2.setColor(Color.WHITE);
        g2.drawString(newGame, (this.game.getGameWidth() - FontUtils.getTextWidth(font, newGame)) / 2, (this.game.getGameHeight() - font.getSize()) / 2);
    }

    public void startTransition(GameState levelState) {
        this.levelState = levelState;
    }


}
