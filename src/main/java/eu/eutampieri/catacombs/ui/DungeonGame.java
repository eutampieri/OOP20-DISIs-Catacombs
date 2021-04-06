package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

public final class DungeonGame extends Game {

    private MenuState menuState;
    private GameState newGame;
    private EndGameState endGame;
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
        this.endGame = new EndGameState(this);
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
        this.newGame = new GameState(this);
        this.startGame.startTransition(this.newGame);
        setState(startGame);



    }

    public void restartLevel() {

    }

    public void nextLevels() {

    }
}
