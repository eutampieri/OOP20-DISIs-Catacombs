package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.input.KeyManager;

import java.awt.event.KeyEvent;

/**
 * This class is the implementation behind the menu
 */

public class LogicMenuImpl implements LogicMenu {
    /**
     * This enum indicates the two menu options
     */
    protected enum MenuOption {
        START_GAME, QUIT_GAME,
    }

    private MenuOption optionSelected = LogicMenuImpl.MenuOption.START_GAME;
    /**
     * the game manager used
     */
    public final DungeonGame game;

    /**
     * Contructor of the logic part of the menu
     *
     * @param game
     */
    public LogicMenuImpl(final DungeonGame game) {
        this.game = game;

    }

    /**
     * This method is used to move to the StartTransition state
     */
    @Override
    public void start() {
        this.game.startGame();
    }

    /**
     * This method controls the selection of the menu options
     */

    @Override
    public void selectOption() {
        if (KeyManager.getKeyManager().isKeyPressed(KeyEvent.VK_ENTER)) {
            switch(this.optionSelected) {
                case START_GAME:
                    start();
                    break;
                case QUIT_GAME:
                    this.game.stop();
                    break;
                default:
            }
        }
        if (KeyManager.getKeyManager().isKeyPressed(KeyEvent.VK_S)) {
            this.optionSelected = MenuOption.QUIT_GAME;
        }
        if (KeyManager.getKeyManager().isKeyPressed(KeyEvent.VK_W)) {
            this.optionSelected = MenuOption.START_GAME;
        }

    }

    /**
     * Is used to know which option has ben selected
     *
     * @return the option selected
     */

    @Override
    public MenuOption getOption() {
        return this.optionSelected;

    }

    /**
     * This method  used to know if the current selected option
     * is the one that starts the game
     *
     * @return true if the selected option is start game
     */

    @Override
    public boolean isOptionStart() {
        return getOption() == MenuOption.START_GAME;
    }

}