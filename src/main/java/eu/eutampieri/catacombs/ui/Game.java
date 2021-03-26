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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.input.MouseManager;
import eu.eutampieri.catacombs.window.MainWindow;

public abstract class Game implements Runnable {

    protected static final double FPS_CONST = 1_000_000_000d;
    protected static final float DELTA_CONST = 1_000_000_000.0f;
    protected static final float DELTA_MIN = 0.016f;
    protected static final float THREAD_SLEEP_CONST = 1_000_000f;

    public static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
    public static final KeyManager KEY_MANAGER = new KeyManager();
    public static final MouseManager MOUSE_MANAGER = new MouseManager();

    private static MainWindow mainFrame;
    private static GameConfiguration gameConfiguration;

    private BufferStrategy bs;
    private GraphicsConfiguration gc;
    private VolatileImage vImage;
    private int framesThisSecond;
    private boolean running = true;
    private Graphics2D graphics;
    private int fps;
    private Thread gameThread;

    public abstract void create();

    /**
     *
     * @param delta gap time from previous render
     */

    public abstract void update(float delta);

    public abstract void render();

    /**
     *
     * @return width of the main frame
     */

    public static int getWidth() {
        return mainFrame.getCanvas().getWidth();
    }

    /**
     *
     * @return height of the main fraim
     */

    public static int getHeight() {
        return mainFrame.getCanvas().getHeight();
    }

    /**
     *
     * @return width of game configuration
     */

    public static int getGameWidth() {
        return gameConfiguration.getGameWidth();
    }

    /**
     *
     * @return height of game configuration
     */

    public static int getGameHeight() {
        return gameConfiguration.getGameHeight();
    }

    /**
     *
     * @param fps
     *           frames per second used
     */

    protected final void setFps(final int fps) {
        this.fps = fps;
    }

    /**
     *
     * @param config
     *              the style choose for the frame
     */

    // TODO Is this warning really necessary? Isn't it bad design?
    @SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
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

        addKeyAdapter(KEY_MANAGER);
        addMouseAdapter(MOUSE_MANAGER);
    }

    /**
     * rendering of basics aspects
     */

    private void preRender() {
        this.graphics = (Graphics2D) vImage.getGraphics();
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, getWidth(), getHeight());
        if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
            vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
                    gameConfiguration.getGameHeight());
        }
        /*if (vImage == null) {
            gc = mainFrame.getCanvas().getGraphicsConfiguration();
            vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
                    gameConfiguration.getGameHeight());
        }*/
    }

    /**
     * managing of scaling
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
     * start the main thread
     */

    public synchronized final void start() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * stop the main thread
     */

    public synchronized final void stop() {
        try {
            this.gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void run() {
        create();
        double tickPerTime;
        long lastTime;
        long lastUpdateTime;
        long now;
        long timer;
        int ticks;
        int updates;
        final int maxUpdates = 5;

        tickPerTime = FPS_CONST / fps;
        lastTime = System.nanoTime();
        lastUpdateTime = System.nanoTime();
        timer = 0;
        ticks = 0;
        while (running) {
            now = System.nanoTime();
            timer += now - lastTime;
            updates = 0;
            while ((now - lastUpdateTime) >= tickPerTime) {
                float delta;
                delta = (now - lastUpdateTime) / DELTA_CONST;
                KEY_MANAGER.update(delta);
                delta = delta <= DELTA_MIN ? delta : DELTA_MIN;
                update(delta);
                lastUpdateTime += tickPerTime;
                updates++;
                if (updates > maxUpdates) {
                    break;
                }
            }
            preRender();
            render();
            show();
            ticks++;
            lastTime = now;

            long timeTake;
            timeTake = System.nanoTime() - now;
            if (tickPerTime > timeTake) {
                try {
                    Thread.sleep((long) ((tickPerTime - timeTake) / THREAD_SLEEP_CONST));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (timer >= FPS_CONST) {
                this.framesThisSecond = ticks;
                mainFrame.getFrame().setTitle("Frame Per Seconds : " + framesThisSecond);
                ticks = 0;
                timer = 0;
            }
        }
    }
/*
    protected void renderFpsCount(final Color color) {
        graphics.setFont(DEFAULT_FONT);
        graphics.setColor(color);
        graphics.drawString("FRAME PER SECOND : " + framesThisSecond,
                gameConfiguration.isScaling() ? gameConfiguration.getGameWidth() - 160 : getWidth() - 160,
                10 + graphics.getFont().getSize());
    }

    protected final void renderFpsCount(final Color color, final int x, final int y) {
        graphics.setColor(color);
        graphics.drawString("FRAME PER SECOND : " + framesThisSecond, x, y);
    }

 */

    /**
     * add key listener to main frame
     *
     * @param e
     *          KeyAdapter
     */
    public final void addKeyAdapter(final KeyAdapter e) {
        mainFrame.getCanvas().addKeyListener(e);
        mainFrame.getFrame().addKeyListener(e);
    }

    /**
     * add mouse listener to main frame
     *
     * @param e
     *          MouseAdapter
     */

    public final void addMouseAdapter(final MouseAdapter e) {
        mainFrame.getCanvas().addMouseListener(e);
        mainFrame.getFrame().addMouseListener(e);
        mainFrame.getCanvas().addMouseMotionListener(e);
        mainFrame.getFrame().addMouseMotionListener(e);
    }

    /**
     * remove key listener from main frame
     *
     * @param e
     *          KeyAdapter
     */

    public final void removeKeyAdapter(final KeyAdapter e) {
        mainFrame.getCanvas().removeKeyListener(e);
        mainFrame.getFrame().removeKeyListener(e);
    }

    /**
     *
     * @return modified graphic
     */

    public final Graphics2D getGraphics() {
        return graphics;
    }

    public void stopGame() {
        this.running = false;
    }

}
