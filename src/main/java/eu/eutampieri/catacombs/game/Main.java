package eu.eutampieri.catacombs.game;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;

import java.awt.Toolkit;
import java.awt.Dimension;

public final class Main {
    private static final int FPS = 30;

    private Main() {

    }

    /**
     * Main entry point for game.
     * @param args main args
     */

    public static void main(final String[] args) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double width = screenSize.getWidth();
        final double height = screenSize.getHeight();

        final GameConfiguration gc = new GameConfiguration();
        gc.setFps(FPS);
        gc.setFullScreen(false);

        gc.setGameWidth((int) (width / 2));
        gc.setGameHeight((int) (height / 2));

        gc.setScaling(true);
        gc.setResizeable(false);

        gc.setTitle("CATACOMBS");

        final DungeonGame game = new DungeonGame();
        game.initialize(gc);
        game.run();
    }
}
