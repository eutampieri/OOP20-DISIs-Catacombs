package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;

import eu.eutampieri.catacombs.ui.input.KeyManager;
import eu.eutampieri.catacombs.ui.input.MouseManager;
import eu.eutampieri.catacombs.window.MainWindow;

import javax.swing.*;

public abstract class Game implements Runnable {

    protected static final long DELTA_MIN = 16_000_000;
    protected static final float THREAD_SLEEP_CONST = 1_000_000f;

    public static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
    public static final MouseManager MOUSE_MANAGER = new MouseManager();

    private MainWindow mainFrame;
    private GameConfiguration gameConfiguration;

    private BufferStrategy bs;
    private GraphicsConfiguration gc;
    private VolatileImage vImage;
    private int framesThisSecond;
    private boolean running;
    private Graphics2D graphics;
    private int fps;
    private Thread gameThread;
    private boolean isTest;

    public void setTest() {
        this.isTest = true;
    }

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
     * start the main thread.
     */

    public final void start() {
        this.running = true;
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * stop the main thread.
     */

    public final void stop() {
       try {
            running = false;
            this.gameThread.join();
            mainFrame.getFrame().dispatchEvent(new WindowEvent(mainFrame.getFrame(), WindowEvent.WINDOW_CLOSING));
       } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public JFrame getMainFrame() {
        return mainFrame.getFrame();
    }

    @Override
    public final void run() {
        create();
        long lastTime;
        long lastUpdateTime;
        long now;
        long timer;
        int ticks;
        int updates;
        final int maxUpdates = 5;

        final double tickPerTime = 1f / fps;
        lastTime = System.nanoTime();
        lastUpdateTime = System.nanoTime();
        timer = 0;
        ticks = 0;
        while (running) {
            now = System.nanoTime();
            timer += now - lastTime;
            updates = 0;
            while ((now - lastUpdateTime) >= tickPerTime) {
                long delta;
                delta = now - lastUpdateTime;
                delta = Math.min(delta, DELTA_MIN);
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

			if (timer >= 1_000_000_000) {
				// mainFrame.getFrame().setTitle("Frame Per Seconds : " + ticks);
				this.framesThisSecond = ticks;
				ticks = 0;
				timer = 0;
			}
			if (updates > 0 && isTest) {
			    break;
            }
		}
	}

	protected void renderfpsCount(final Color color) {
		graphics.setFont(DEFAULT_FONT);
		graphics.setColor(color);
		graphics.drawString("FRAME PER SECOND : " + framesThisSecond,
				gameConfiguration.isScaling() ? gameConfiguration.getGameWidth() - 160 : getWidth() - 160,
				10 + graphics.getFont().getSize());
	}

	protected void renderfpsCount(final Color color, final int x, final int y) {
		graphics.setColor(color);
		graphics.drawString("FRAME PER SECOND : " + framesThisSecond, x, y);
	}

	
	public void addKeyAdapter(final KeyAdapter e) {
		mainFrame.getCanvas().addKeyListener(e);
		mainFrame.getFrame().addKeyListener(e);
	}
	
	public void addMouseAdapter(final MouseAdapter e) {
		mainFrame.getCanvas().addMouseListener(e);
		mainFrame.getFrame().addMouseListener(e);		
		mainFrame.getCanvas().addMouseMotionListener(e);
		mainFrame.getFrame().addMouseMotionListener(e);
	}

	public void removeKeyAdapter(final KeyAdapter e) {
		mainFrame.getCanvas().removeKeyListener(e);
		mainFrame.getFrame().removeKeyListener(e);
	}	
	
}
