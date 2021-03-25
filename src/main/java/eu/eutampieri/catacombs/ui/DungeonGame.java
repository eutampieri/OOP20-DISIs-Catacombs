package eu.eutampieri.catacombs.ui;

public final class DungeonGame extends Game {

    private MenuState menuState;
    private State state;

    public void create() {
        // caricare gli asset

        this.menuState = new MenuState(this);
        setState(this.menuState);

    }

    public void setState(final State state) {
        this.state = state;
    }

    public void setMenuState() {
        setState(this.menuState);
    }

    public void update(final float delta) {
        this.state.update(delta);
    }

    public void render() {
        this.state.render(this.getGraphics());
    }

    // da implementare nextLevel startGame e restartLevel

    public void startgame() {

    }

    public void restartLevel() {

    }

    public void nextLevels() {

    }
}
