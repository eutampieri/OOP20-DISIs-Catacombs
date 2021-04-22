package eu.eutampieri.catacombs.ui;

public interface LogicMenu {

    /**
     * This method is used to move to the StartTransition state
     */

    void start();

    /**
     * This method controls the selection of the menu options
     */

    void selectOption();

    /**
     * Is used to know which option has ben selected
     *
     * @return the option selected
     */

    LogicMenuImpl.MenuOption getOption();

    /**
     * This method  used to know if the current selected option
     * is the one that starts the game
     *
     * @return true if the selected option is start game
     */

    boolean isOptionStart();
}
