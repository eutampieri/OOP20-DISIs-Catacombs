package eu.eutampieri.catacombs.ui;

public interface LogicMenu {

    void start();

    void selectOption();

    LogicMenuImpl.MenuOption getOption();

    boolean isOptionStart();
}
