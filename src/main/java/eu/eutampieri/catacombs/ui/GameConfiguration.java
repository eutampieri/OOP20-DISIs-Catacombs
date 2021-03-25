package eu.eutampieri.catacombs.ui;

public final class GameConfiguration {

    static final int DEFAULT_FPS = 60;
    private int gameHeight, gameWidth;
    private int fps = DEFAULT_FPS;
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

    public void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
    public void setGameWidth(final int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public void setGameHeight(final int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setFps(final int fps) {
        this.fps = fps;
    }

    public void setScaling(final boolean s) {
        this.isScaling = s;
    }

    public void setResizeable(final boolean r) {
        this.resizeable = r;
    }

}
