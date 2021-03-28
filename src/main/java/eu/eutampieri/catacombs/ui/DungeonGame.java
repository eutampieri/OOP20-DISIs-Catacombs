package eu.eutampieri.catacombs.ui;

public final class DungeonGame extends Game {

    private MenuState menuState;
    private State state;

    @Override
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
import eu.eutampieri.catacombs.ui.MenuState;
import eu.eutampieri.catacombs.ui.State;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

public final class DungeonGame extends Game {

	private MenuState menuState;
	private State state;
	public AssetManager assetManager;

	public void create() {
		assetManager.load();

		this.menuState = new MenuState(this);
		setState(this.menuState);

	}

	public void setState(State state) {
		this.state = state;
	}

	public void setMenuState() {
		setState(this.menuState);
	}

	public void update(float delta) {
		this.state.update(delta);
	}

	public void render() {
		this.state.render(graphics);
	}

	// da implementare nextLevel startGame e restartLevel

	public void startgame() {

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
