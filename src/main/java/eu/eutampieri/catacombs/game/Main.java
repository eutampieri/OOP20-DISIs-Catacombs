package eu.eutampieri.catacombs.game;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public class Main {

    public static void main(String[] args) {
        GameConfiguration gc = new GameConfiguration();
        gc.setFps(60);
        gc.setFullScreen(false);

        gc.setGameWidth(672);
        gc.setGameHeight(480);

        gc.setScaling(true);
        gc.setResizeable(true);

        gc.setTitle("CATACOMBS");

        DungeonGame game = new DungeonGame();
        Launcher.launch(gc, game);
    }
}
