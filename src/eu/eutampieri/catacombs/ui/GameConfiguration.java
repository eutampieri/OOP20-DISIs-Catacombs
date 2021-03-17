package eu.eutampieri.catacombs.ui;

public class GameConfiguration {
	
	private int gameHeight, gameWidth;
	private int fps = 60;
	private String title;
	private boolean fullScreen;
	private boolean isScaling;
	private boolean resizeable;
	
	private int getGameWidth() {
		return this.gameWidth;
	}
	
	private int getGameHeight() {
		return this.gameHeight;
	}
	
	private int getTitle() {
		return this.getTitle();
	}
	
	private int getFps() {
		return this.fps;
	}
	
	private boolean fullScreen() {
		return this.fullScreen;
	}
	
	private boolean resizeable() {
		return this.resizeable;
	}

	private boolean isScaling() {
		return this.isScaling;
	}
	
	private void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	private void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}
	
	private void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setFps(int fps) {
		this.fps = fps;
	}
	
	private void setScaling(boolean s) {
		this.isScaling = s;
	}
	
	private void setResizeable(boolean r) {
		this.resizeable = r;
	}

}
