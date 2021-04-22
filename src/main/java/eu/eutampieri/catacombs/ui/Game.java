package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;
import java.awt.event.WindowEvent;

import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.input.MouseManager;
import eu.eutampieri.catacombs.window.MainWindow;

import javax.swing.JFrame;

/**
 * this is the main class in which the game loop is implemented
 * it controls the thread the render and the update of the entire game.
 */

public abstract class Game implements Runnable {
    /**
     * minimum delta.
     */
    protected static final long DELTA_MIN = 16_000_000;
    /**
     * this constant indicates the thread sleep time.
     */
    protected static final float THREAD_SLEEP_CONST = 1_000_000f;
    /**
     *  Font used to render fps counter.
     */
    public static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
    /**
     * this is due to singleton in mouseManager.
     */
    public static final MouseManager MOUSE_MANAGER = new MouseManager();
    /**
     * Main windows used for the game.
     */
    private MainWindow mainFrame;
    /**
     * Configuration of the game.
     */
    private GameConfiguration gameConfiguration;

    private BufferStrategy bs;
    private GraphicsConfiguration gc;
    private VolatileImage vImage;
    /**
     * boolean value for the loop of the game.
     */
    private boolean running;
    /**
     * the graphical element used in the game.
     */
    private Graphics2D graphics;
    /**
     * fps of the game.
     */
    private int fps;

    /**
     *
     * @return the graphical element used
     */

    public Graphics2D getGraphics() {
        return this.graphics;
    }

    public abstract void create();

    /**
     *
     * @param delta gap time from previous render
     */

    public abstract void update(long delta);

    public abstract void render();

    /**
     *
     * @return width of the main frame
     */
    public int getWidth() {
        return this.mainFrame.getCanvas().getWidth();
    }

    /**
     *
     * @return height of the main frame
     */

    public int getHeight() {
        return this.mainFrame.getCanvas().getHeight();
    }

    /**
     *
     * @return width of game configuration
     */
    public int getGameWidth() {
        return this.gameConfiguration.getGameWidth();
    }

    /**
     *
     * @return height of game configuration
     */

    public int getGameHeight() {
        return this.gameConfiguration.getGameHeight();
    }

    /**
     *
     * @param fps frames per second used
     */

    protected final void setFps(final int fps) {
        this.fps = fps;
    }

    /**
     *
     * @param config the style choose for the frame
     */
    public final void initialize(final GameConfiguration config) {
        gameConfiguration = config;
        mainFrame = new MainWindow(config.getTitle(), config.getGameWidth(), config.getGameHeight(),
                config.isFullScreen(), config.isResizeable());
        mainFrame.getCanvas().setBackground(Color.BLACK);
        this.fps = config.getFps();
        gc = mainFrame.getCanvas().getGraphicsConfiguration();
        mainFrame.getFrame().addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(final ComponentEvent e) {

            }

            @Override
            public void componentMoved(final ComponentEvent e) {

            }

            @Override
            public void componentShown(final ComponentEvent e) {
                if (!gameConfiguration.isScaling()) {
                    vImage = gc.createCompatibleVolatileImage(getWidth(), getHeight());
                }

            }

            @Override
            public void componentHidden(final ComponentEvent e) {

            }

        });

        addKeyAdapter(KeyManager.getKeyManager());
        addMouseAdapter(MOUSE_MANAGER);
    }

    /**
     * rendering of basics aspects.
     */
    private void preRender() {
        if (vImage == null) {
            gc = mainFrame.getCanvas().getGraphicsConfiguration();
            vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
                    gameConfiguration.getGameHeight());
        }
        this.graphics = (Graphics2D) vImage.getGraphics();
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, getWidth(), getHeight());
        if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
            vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
                    gameConfiguration.getGameHeight());
        }
    }

    /**
     * managing of scaling.
     */

    private void show() {
        this.graphics.dispose();
        if (this.bs == null) {
            mainFrame.getCanvas().createBufferStrategy(3);
            this.bs = mainFrame.getCanvas().getBufferStrategy();
        }
        this.graphics = (Graphics2D) bs.getDrawGraphics();
        if (gameConfiguration.isScaling()) {
            int width, height;
            double thumbRatio;
            int imageWidth;
            int imageHeight;
            double aspectRatio;

            width = getWidth();
            height = getHeight();
            thumbRatio = (double) width / (double) height;
            imageWidth = vImage.getWidth();
            imageHeight = vImage.getHeight();
            aspectRatio = (double) imageWidth / (double) imageHeight;

            if (thumbRatio < aspectRatio) {
                height = (int) (width / aspectRatio);
            } else {
                width = (int) (height * aspectRatio);
            }
            this.graphics.drawImage(vImage, (getWidth() - width) / 2, (getHeight() - height) / 2, width, height, null);
        } else {
            this.graphics.drawImage(vImage, 0, 0, null);
        }
        this.graphics.dispose();
        this.bs.show();
    }

    /**
     * start the game.
     */
    public final void start() {
        this.running = true;
    }

    /**
     * stop the game.
     */
    public final void stop() {
        this.running = false;
    }

    /**
     *
     * @return the main frame of the game.
     */

    public JFrame getMainFrame() {
        return mainFrame.getFrame();
    }

    /**
     * this is the loop of the game that manage the update and render of the game.
     */
    @Override
    public final void run() {
        create();
        long lastUpdateTime;
        long now;
        this.start();

        final double tickPerTime = 1f / fps;
        lastUpdateTime = System.nanoTime();
        while (this.running) {
            now = System.currentTimeMillis();
            long delta;
            delta = now - lastUpdateTime;
            delta = Math.min(delta, DELTA_MIN);
            update(delta);
            lastUpdateTime += tickPerTime;
            preRender();
            render();
            show();
            final long timeTake = System.currentTimeMillis() - now;
            final long timeToFrame = 1_000 / this.fps - timeTake;
            if (timeToFrame > 0) {
                try {
                    Thread.sleep(timeToFrame);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.mainFrame.getFrame().dispatchEvent(new WindowEvent(mainFrame.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Add the Key listener.
     *
     * @param e the KeyAdapter to add
     */
    public void addKeyAdapter(final KeyAdapter e) {
        mainFrame.getCanvas().addKeyListener(e);
        mainFrame.getFrame().addKeyListener(e);
    }

    /**
     * Add the mouse listener.
     * @param e the MouseAdapter to add
     */
    public void addMouseAdapter(final MouseAdapter e) {
        mainFrame.getCanvas().addMouseListener(e);
        mainFrame.getFrame().addMouseListener(e);
        mainFrame.getCanvas().addMouseMotionListener(e);
        mainFrame.getFrame().addMouseMotionListener(e);
    }

    /**
     * method used to eventually remove a KeyAdapter.
     * @param e the Key adapter to remove
     */
    public void removeKeyAdapter(final KeyAdapter e) {
        mainFrame.getCanvas().removeKeyListener(e);
        mainFrame.getFrame().removeKeyListener(e);
    }

}
