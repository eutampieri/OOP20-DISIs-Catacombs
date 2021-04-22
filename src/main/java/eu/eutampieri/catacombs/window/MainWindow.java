package eu.eutampieri.catacombs.window;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

/**
 * This class create the main frame in which the entire game
 * is going to run.
 */

public final class MainWindow {
    /**
     * The main JFrame of the game.
     */
    private JFrame frame;
    /**
     * The canvas added in the main JFrame.
     */
    private Canvas canvas;

    /**
     * main frame constructor.
     *
     * @param name          Title of the game
     * @param width         Width of the window
     * @param height        Height of the window
     * @param fullScreen    True if game is full screen
     * @param resizeable    True if the window is resizable
     */

    public MainWindow(final String name, final int width, final int height, final boolean fullScreen,
            final boolean resizeable) {

        this.frame = new JFrame(name);
        this.canvas = new Canvas();
        this.canvas.setSize(width, height);
        this.frame.setSize(width, height);
        this.frame.setResizable(resizeable);
        this.frame.add(canvas);
        if (fullScreen) {
            makeFullScreen();
        }

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setVisible(true);
    }

    /**
     *
     * @return the main Frame
     */

    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Set the main frame.
     *
     * @param frame  The Frame to set
     */

    public void setFrame(final JFrame frame) {
        this.frame = frame;
    }

    /**
     *
     * @return the canvas in the main frame.
     */

    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * Set the canvas in the main frame.
     *
     * @param canvas   the canvas to set
     */

    public void setCanvas(final Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * this method is used to resize the frame components to
     * make it fullscreen.
     *
     */

    private void makeFullScreen() {
        final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gd = env.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            this.frame.setUndecorated(true);
            this.frame.setResizable(false);
            this.frame.setIgnoreRepaint(true);
            gd.setFullScreenWindow(this.frame);
        }
    }

}
