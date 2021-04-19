package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;

public interface Animatable {
    boolean canPerform(Action action);
}
