package eu.eutampieri.catacombs.game;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public final class Main {

    private Main() {

    }
    public static void main(final String[] args) {
        final GameConfiguration gc = new GameConfiguration();
        gc.setFps(60);
        gc.setFullScreen(false);

        gc.setGameWidth(672);
        gc.setGameHeight(480);

        gc.setScaling(true);
        gc.setResizeable(true);

        gc.setTitle("CATACOMBS");

        final DungeonGame game = new DungeonGame();

        Launcher.launch(gc, game);
    }
}
