package eu.eutampieri.catacombs.game;

import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public final class Launcher {
    private Launcher(){

    }
    public static void launch(final GameConfiguration config, final Game game){
        game.initialize(config);
        game.start();
    }
}
