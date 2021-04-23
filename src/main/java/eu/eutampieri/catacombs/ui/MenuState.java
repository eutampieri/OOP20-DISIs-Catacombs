package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Optional;

import eu.eutampieri.catacombs.ui.utils.*;

/**
 * This class manage the graphical elements of the game menu.
 */

public final class MenuState extends State {

    private static final int TITLE_FONT_SIZE = 50;
    private static final int DEFAULT_FONT_SIZE = 40;
    private static final int OVAL_SIZE = 20;
    private static final int MIDDLE_OF_OPTION = 30;
    private static final int START_OFFSET = 55;
    private static final int QUIT_OFFSET = 75;
    private static final int TITLE_PADDING = 20;
    private static final int OPTION_HEIGHT = 20;
    private static final int TITLE_HEIGHT = 60;

    /**
     * The font used to write the name of the game.
     */
    private final transient Font titleFont = new Font("Times New Roman", Font.PLAIN, TITLE_FONT_SIZE);
    /**
     * The font used to write the name of the game.
     */
    private final transient Font font = new Font("Arial", Font.PLAIN, DEFAULT_FONT_SIZE);
    /**
     * The game manager.
     */
    private final DungeonGame game;
    /**
     * the logic interface behind the menu.
     */
    private final LogicMenu logic;

    /**
     * The menu constructor.
     *
     * @param game the game manger used
     */
    public MenuState(final DungeonGame game) {
        super(game);
        this.game = game;
        this.logic = new LogicMenuImpl(this.game);
    }

    /**
     * This updates the selection of the two options.
     *
     * @param delta gap time from the previous render
     */

    @Override
    public void update(final long delta) {
        this.logic.selectOption();
    }

    /**
     * Renders the menu interface.
     *
     * @param g2 use to generate graphics
     */

    @Override
    public void render(final Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
        g2.setColor(Color.white);

        // title
        g2.setFont(this.titleFont);
        final String title = "D.I.S.I.'S CATACOMBS";
        final float x = (float) (game.getGameWidth() - FontUtils.getTextWidth(titleFont, title)) / 2f;
        g2.drawString(title, x, titleFont.getSize() + TITLE_PADDING);

        // options
        g2.setFont(this.font);
        final String start = "Start";
        final float x1 = (float) (game.getGameWidth() - FontUtils.getTextWidth(font, start)) / 2f;
        g2.drawString(start, x1, titleFont.getSize() + TITLE_HEIGHT + TITLE_PADDING);
        final String quit = "Quit";
        final float x2 = (float) (game.getGameWidth() - FontUtils.getTextWidth(font, quit)) / 2f;
        g2.drawString(quit, x2, titleFont.getSize() + font.getSize() + TITLE_HEIGHT + TITLE_PADDING + OPTION_HEIGHT);

        // selection
        final int x3 = (int) (this.logic.isOptionStart() ? x1 - MIDDLE_OF_OPTION : x2 - MIDDLE_OF_OPTION);
        final int y = this.logic.isOptionStart() ? this.titleFont.getSize() + START_OFFSET
                : this.titleFont.getSize() + font.getSize() + QUIT_OFFSET;
        g2.fillOval(x3, y, OVAL_SIZE, OVAL_SIZE);

        // image
        final ImageTransformerFactory itf = new ImageTransformerFactoryImpl();
        final Optional<BufferedImage> image = ImageLoader.loadImage("res/menucommands.png");

        g2.drawImage(itf.scale(0.4).transform(image.get()), null,
                game.getGameWidth() / 2 - image.get().getWidth() / 5,
                game.getGameHeight() / 2);

    }

}
