package eu.eutampieri.catacombs.ui;

public class DungeonGame {
	
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
		setState(this.menuState)
	}
	
	public void update(float delta) {
		this.state.update(delta);
	}
	
	public void render() {
		// da implementare
	}
	
	// da implementare nextLevel startGame e restartLevel
	
	

}
