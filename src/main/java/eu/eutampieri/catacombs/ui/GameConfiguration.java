package eu.eutampieri.catacombs.ui;

public class GameConfiguration {

    private int gameHeight, gameWidth;
    private int fps = 60;
    private String title;
    private boolean fullScreen;
    private boolean isScaling;
    private boolean resizeable;

    public int getGameWidth() {
        return this.gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

    public String getTitle() {
        return this.title;
    }

    public int getFps() {
        return this.fps;
    }

    public boolean fullScreen() {
        return this.fullScreen;
    }

    public boolean resizeable() {
        return this.resizeable;
    }

    public boolean isScaling() {
        return this.isScaling;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void setScaling(boolean s) {
        this.isScaling = s;
    }

    public void setResizeable(boolean r) {
        this.resizeable = r;
    }

}
