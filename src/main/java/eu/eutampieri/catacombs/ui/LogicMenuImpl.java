package eu.eutampieri.catacombs.ui;

import java.awt.event.KeyEvent;

public class LogicMenuImpl implements LogicMenu {

    protected enum MenuOption {
        START_GAME, QUIT_GAME,
    }

    private static final int START_GAME = 1;
    private static final int QUIT_GAME = 2;

    private MenuOption optionSelected = LogicMenuImpl.MenuOption.START_GAME;
    public final DungeonGame game;

    public LogicMenuImpl(final DungeonGame game) {
        this.game = game;

    }

    @Override
    public void start() {
        this.game.startGame();
    }

    @Override
    public void selectOption() {
        if (Game.KEY_MANAGER.isKeyJustPressed(KeyEvent.VK_ENTER)) {
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
        if (Game.KEY_MANAGER.isKeyPressed(KeyEvent.VK_S)) {
            this.optionSelected = MenuOption.QUIT_GAME;
        }
        if (Game.KEY_MANAGER.isKeyPressed(KeyEvent.VK_W)) {
            this.optionSelected = MenuOption.START_GAME;
        }

    }

    @Override
    public MenuOption getOption() {
        return this.optionSelected;

    }

    @Override
    public boolean isOptionStart() {
        return getOption() == MenuOption.START_GAME;
    }

}