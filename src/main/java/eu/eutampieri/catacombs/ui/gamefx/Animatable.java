package eu.eutampieri.catacombs.ui.gamefx;

import eu.eutampieri.catacombs.model.Action;

/**
 * This interface is required for every entity for which you want to display an animation.
 */
public interface Animatable {
    /**
     * Whether this entity can perform a specific action.
     * @param action The action you want this entity ability to perform
     * @return true if the action can be performed.
     */
    boolean canPerform(Action action);
}
