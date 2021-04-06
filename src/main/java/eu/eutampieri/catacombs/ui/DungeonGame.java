package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

public final class DungeonGame extends Game {

    private MenuState menuState;
    private State state;
    private AssetManager am = new AssetManager();

    @Override
    public void create() {
        am.load();

        this.menuState = new MenuState(this);
        setState(this.menuState);

    }

    public void setState(final State state) {
        this.state = state;
    }

    public void setMenuState() {
        setState(this.menuState);
    }

    @Override
    public void update(final float delta) {
        this.state.update(delta);
    }

    @Override
    public void render() {
        this.state.render(this.getGraphics());
    }

    // da implementare nextLevel startGame e restartLevel

    public void startGame() {

    }

    public void restartLevel() {

    }

    public void nextLevels() {

    }

    public MenuState getMenuState() {
        return menuState;
    }

    public State getState() {
        return state;
    }
}
