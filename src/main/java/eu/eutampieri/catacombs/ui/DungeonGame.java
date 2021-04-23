package eu.eutampieri.catacombs.ui;

/**
 * this class implements the unimplemented methods of game and
 * controls the passage from one state to another.
 */
public final class DungeonGame extends Game {

    private MenuState menuState;
    private TransitionFactory transition;
    private State state;

    /**
     *
     * @return the menu state
     */
    public MenuState getMenuState() {
        return menuState;
    }

    /**
     *
     * @return the current state
     */

    public State getState() {
        return state;
    }

    /**
     *  This method initialise the states of the game and
     *  set the menu state as starting state.
     */
    @Override
    public void create() {

        this.transition = new TransitionFactoryImpl();
        this.menuState = new MenuState(this);
        setState(this.menuState);

    }

    /**
     *
     * @param state the state to set as current state
     */

    public void setState(final State state) {
        this.state = state;
    }

    /**
     * Set the meu state as current state.
     */

    public void setMenuState() {
        setState(this.menuState);
    }

    /**
     * This method update the current state.
     *
     * @param delta gap time from previous render
     */

    @Override
    public void update(final long delta) {
        this.state.update(delta);
    }

    /**
     * This method renders the current state.
     */

    @Override
    public void render() {
        this.state.render(this.getGraphics());
    }

    /**
     * This method start the transition state from the menu to the game it self.
     */

    public void startGame() {
        final GameState newGame = new GameState(this);
        setState(this.transition.transState("New Game", this, newGame));
    }

    /**
     * This method start the transition state from the game to the end game.
     */

    public void endGame() {
        final EndGameState endGame = new EndGameState(this);
        setState(this.transition.transState("Dead", this, endGame));
    }

    /**
     * This method start the winning transition transition.
     */

    public void winGame() {
        final EndGameState endGame = new EndGameState(this);
        setState(this.transition.transState("WIN!!", this, endGame));
    }

}
