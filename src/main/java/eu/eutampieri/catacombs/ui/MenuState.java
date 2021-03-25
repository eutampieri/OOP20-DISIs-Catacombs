package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

public final class MenuState extends State {

    private static final int START_GAME = 1;
    private static final int QUIT_GAME = 2;
    private static final int TITLE_FONT_SIZE = 50;
    private static final int DEFAULT_FONT_SIZE = 40;

    private final transient Font titleFont = new Font("Times New Roman", Font.PLAIN, TITLE_FONT_SIZE);
    private final transient Font font = new Font("Arial", Font.PLAIN, DEFAULT_FONT_SIZE);

    private int optionSelected = START_GAME;

    public MenuState(final DungeonGame game) {
        super(game);
    }

    @Override
    public void update(final float delta) {
        if (Game.KEY_MANAGER.isKeyJustPressed(KeyEvent.VK_ENTER)) {
            switch (this.optionSelected) {
                case START_GAME:
                    this.game.startGame();
                    break;
                case QUIT_GAME:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        if (Game.KEY_MANAGER.isKeyPressed(KeyEvent.VK_S)) {
            this.optionSelected = QUIT_GAME;
        }
        if (Game.KEY_MANAGER.isKeyPressed(KeyEvent.VK_W)) {
            this.optionSelected = START_GAME;
        }

    }

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());
        g2.setColor(Color.white);

        // title
        g2.setFont(this.titleFont);
        final String title = "CATACOMBS";
        final float x = (float)(Game.getWidth() - FontUtils.getTextWidth(titleFont, title)) / 2f;
        g2.drawString(title, x, titleFont.getSize() * 2);

        // options
        g2.setFont(this.font);
        final String start = "Start";
        final float x1 = (float)(Game.getWidth() - FontUtils.getTextWidth(font, start)) / 2f;
        g2.drawString(start, x1, titleFont.getSize() * 2);
        final String quit = "Quit";
        final float x2 = (float)(Game.getWidth() - FontUtils.getTextWidth(font, quit)) / 2f;
        g2.drawString(quit, x2, titleFont.getSize() + font.getSize() + 100);

        // selection
        final int x3 = (int) (this.optionSelected == START_GAME ? x1 - 30 : x2 - 30);
        final int y = this.optionSelected == START_GAME ? this.titleFont.getSize() + 55 : this.titleFont.getSize() + font.getSize() + 75;
        g2.fillOval(x3, y, 20, 20);



    }

}
