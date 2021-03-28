package eu.eutampieri.catacombs.ui;

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
