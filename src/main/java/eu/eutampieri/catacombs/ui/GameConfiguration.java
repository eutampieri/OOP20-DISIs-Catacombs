package eu.eutampieri.catacombs.ui;

/**
 * This class keeps all the information about the game features.
 */

public final class GameConfiguration {

    private static final int DEFAULT_FPS = 60;
    /**
     * Indicates the dimension of the canvas.
     */
    private int gameHeight, gameWidth;
    private int fps = DEFAULT_FPS;
    /**
     * Indicates the title of the game.
     */
    private String title;
    /**
     * Indicates if the game can be fullScreen.
     */
    private boolean fullScreen;
    /**
     * Indicates if the game can be scaled.
     */
    private boolean scaling;
    /**
     * Indicates if the game is resizeable.
     */
    private boolean resizeable;

    /**
     * This method returns the the width of the canvas.
     * @return the width of the canvas
     */

    public int getGameWidth() {
        return this.gameWidth;
    }

    /**
     * This method returns the height of the canvas.
     * @return the height of the canvas
     */

    public int getGameHeight() {
        return this.gameHeight;
    }

    /**
     * This method returns the game title.
     * @return the game title
     */

    public String getTitle() {
        return this.title;
    }

    /**
     * This method returns the game fps.
     * @return the game fps
     */

    public int getFps() {
        return this.fps;
    }

    /**
     * This method returns true if the game is fullScreen.
     * @return true if the game is fullScreen
     */

    public boolean isFullScreen() {
        return this.fullScreen;
    }

    /**
     * This method returns true if the game is resizeable.
     * @return true if the game is resizeable
     */

    public boolean isResizeable() {
        return this.resizeable;
    }

    /**
     * This method returns true if the game can be scaled.
     * @return true if the game can be scaled
     */

    public boolean isScaling() {
        return this.scaling;
    }

    /**
     * This method set the game in order to be full screened.
     *
     * @param fullScreen indicates if the game can be made full screen or not
     */

    public void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * This method set the width of the game.
     * @param gameWidth the width of the game
     */

    public void setGameWidth(final int gameWidth) {
        this.gameWidth = gameWidth;
    }

    /**
     * This method set the height of the game.
     * @param gameHeight the height of the game
     */

    public void setGameHeight(final int gameHeight) {
        this.gameHeight = gameHeight;
    }

    /**
     * This method set the title of the game.
     * @param title the title of the game
     */

    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * This method set the fps on which the game is based.
     * @param fps the fps on which the game is based
     */

    public void setFps(final int fps) {
        this.fps = fps;
    }

    /**
     * This method set true if the game  can scale.
     * @param s true if the game  can scale
     */

    public void setScaling(final boolean s) {
        this.scaling = s;
    }

    /**
     * This method set true if the game can be resized.
     * @param r true if the game can be resized
     */

    public void setResizeable(final boolean r) {
        this.resizeable = r;
    }

}
