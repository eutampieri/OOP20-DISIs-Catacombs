package eu.eutampieri.catacombs.ui;

import java.awt.*;

public interface TransitionFactory {

    State generalTrans(String message, DungeonGame game, State state, Font font);

    State transState(String message, DungeonGame game, State state, Font font);

}
