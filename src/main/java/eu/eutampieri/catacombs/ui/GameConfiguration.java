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
     *
     * @return the the width of the canvas
     */

    public int getGameWidth() {
        return this.gameWidth;
    }

    /**
     *
     * @return teh height of the canvas
     */

    public int getGameHeight() {
        return this.gameHeight;
    }

    /**
     *
     * @return the game title
     */

    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return the game fps
     */

    public int getFps() {
        return this.fps;
    }

    /**
     *
     * @return true if the game is fullScreen
     */

    public boolean isFullScreen() {
        return this.fullScreen;
    }

    /**
     *
     * @return true if the game is resizeable
     */

    public boolean isResizeable() {
        return this.resizeable;
    }

    /**
     *
     * @return true if the game can be scaled
     */

    public boolean isScaling() {
        return this.scaling;
    }

    /**
     *  This method set the game in order to be full screened.
     *
     * @param fullScreen indicates if the game can be made full screen or not
     */

    public void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     *
     * @param gameWidth the width of the game
     */

    public void setGameWidth(final int gameWidth) {
        this.gameWidth = gameWidth;
    }

    /**
     *
     * @param gameHeight the height of the game
     *
     */

    public void setGameHeight(final int gameHeight) {
        this.gameHeight = gameHeight;
    }

    /**
     *
     * @param title the title of the game
     */

    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     *
     * @param fps the fps on which the game is based
     */

    public void setFps(final int fps) {
        this.fps = fps;
    }

    /**
     *
     * @param s true if the game  can scale
     */

    public void setScaling(final boolean s) {
        this.scaling = s;
    }

    /**
     *
     * @param r true if the game can be resized
     */

    public void setResizeable(final boolean r) {
        this.resizeable = r;
    }

}
