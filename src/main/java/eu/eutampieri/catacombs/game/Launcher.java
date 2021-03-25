package eu.eutampieri.catacombs.game;

import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.ui.GameConfiguration;

public final class Launcher {
    public static final void launch(GameConfiguration config, Game game){
        game.initialize(config);
        game.start();
    }
}
