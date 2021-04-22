package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

public final class MenuState extends State {

    private static final int TITLE_FONT_SIZE = 50;
    private static final int DEFAULT_FONT_SIZE = 40;
    private static final int OVAL_SIZE = 20;
    private static final int MIDDLE_OF_OPTION = 30;
    private static final int START_OFFSET = 55;
    private static final int QUIT_OFFSET = 75;

    private final transient Font titleFont = new Font("Times New Roman", Font.PLAIN, TITLE_FONT_SIZE);
    private final transient Font font = new Font("Arial", Font.PLAIN, DEFAULT_FONT_SIZE);

    private final DungeonGame game;
    private final LogicMenu logic;

    public MenuState(final DungeonGame game) {
        super(game);
        this.game = game;
        this.logic = new LogicMenuImpl(this.game);

    }

    @Override
    public void update(final long delta) {
        this.logic.selectOption();
    }

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
        g2.setColor(Color.white);

        // title
        g2.setFont(this.titleFont);
        final String title = "CATACOMBS";
        final float x = (float) (game.getGameWidth() - FontUtils.getTextWidth(titleFont, title)) / 2f;
        g2.drawString(title, x, titleFont.getSize() + 20);

        // options
        g2.setFont(this.font);
        final String start = "Start";
        final float x1 = (float) (game.getGameWidth() - FontUtils.getTextWidth(font, start)) / 2f;
        g2.drawString(start, x1, titleFont.getSize() + 80);
        final String quit = "Quit";
        final float x2 = (float) (game.getGameWidth() - FontUtils.getTextWidth(font, quit)) / 2f;
        g2.drawString(quit, x2, titleFont.getSize() + font.getSize() + 100);

        // selection
        final int x3 = (int) (this.logic.isOptionStart() ? x1 - MIDDLE_OF_OPTION : x2 - MIDDLE_OF_OPTION);
        final int y = this.logic.isOptionStart() ? this.titleFont.getSize() + START_OFFSET
                : this.titleFont.getSize() + font.getSize() + QUIT_OFFSET;
        g2.fillOval(x3, y, OVAL_SIZE, OVAL_SIZE);

    }

}
