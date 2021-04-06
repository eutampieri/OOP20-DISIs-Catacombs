package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

public final class DungeonGame extends Game {

    private MenuState menuState;
    private StartTransition startGame;
    private State state;
    private AssetManager am;


    public MenuState getMenuState() {
        return menuState;
    }

    public State getState() {
        return state;
    }

    @Override
    public void create() {
        am.load();

        this.startGame = new StartTransition(this);
        // to use when game ends
        //final EndGameState endGame = new EndGameState(this);
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

    public void startGame() {
        final GameState newGame = new GameState(this);
        this.startGame.startTransition(newGame);
        setState(startGame);



    }

    public void restartLevel() {

    }

    public void nextLevels() {

    }
}
