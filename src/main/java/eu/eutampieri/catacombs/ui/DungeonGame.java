package eu.eutampieri.catacombs.ui;

public class DungeonGame extends Game {
	
	private MenuState menuState;
	private State state;
	
	public void create() {
		// caricare gli asset
		
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
	
	// TODO nextLevel startGame e restartLevel
	
	public void startgame() {
		
	}

	public void restartLevel() {
		
	}
	
	public void nextLevels() {
		
	}
}
