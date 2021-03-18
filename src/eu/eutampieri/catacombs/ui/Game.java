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

import eu.eutampieri.catacombs.window.MainWindow;

public abstract class Game implements Runnable {
	
	public static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
	// implementare key manager
	// implementare mouse manager
	
	protected static MainWindow mainFrame;
	protected static GameConfiguration gameConfiguration;
	
	private BufferStrategy bs;
	private GraphicsConfiguration gc;
	private VolatileImage vImage;
	private int framesThisSecond;
	private boolean running = true;
	protected Graphics2D graphics;
	protected int fps;
	protected Thread gameThread;
	
	public Game() {
		
	}
	
	public abstract void create();
	
	public abstract void update(float delta);
	
	public abstract void render();
	
	public static int getWidth() {
		return mainFrame.getCanvas().getWidth();
	}

	public static int getHeight() {
		return mainFrame.getCanvas().getHeight();
	}
	
	public static int getGameWidth() {
		return gameConfiguration.getGameWidth();
	}
	
	public static int getGameHeight() {
		return gameConfiguration.getGameHeight();
	}
	
	protected void setFps(int fps) {
		this.fps = fps;
	}
	
	public final void initialize(GameConfiguration config) {
		this.gameConfiguration = config;
		this.mainFrame = new MainWindow(config.getTitle(),config.getGameWidth(),config.getGameHeight(),
				config.fullScreen(),config.resizeable());
		this.mainFrame.getCanvas().setBackground(Color.BLACK);
		this.fps = config.getFps();
		this.gc = this.mainFrame.getCanvas().getGraphicsConfiguration();
		this.mainFrame.getFrame().addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				if (!gameConfiguration.isScaling()) {
					vImage = gc.createCompatibleVolatileImage(getWidth(), getHeight());
				}
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				
			}
			
		});
		
		// aggiungere gli adapter
	}
	
	private void preRender() {
		this.graphics = (Graphics2D) vImage.getGraphics();
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, getWidth(), getHeight());
		if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
			vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
					gameConfiguration.getGameHeight());
		}
		if (vImage == null) {
			gc = mainFrame.getCanvas().getGraphicsConfiguration();
			vImage = gc.createCompatibleVolatileImage(gameConfiguration.getGameWidth(),
					gameConfiguration.getGameHeight());
		}
	}
	
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
	
	public synchronized void start() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}
	
	public synchronized void stop() {
		try {
			this.gameThread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		create();
		double tickPerTime;
		long lastTime;
		long lastUpdateTime;
		long now;
		long timer;
		int ticks;
		int updates = 0;
		int maxUpdates = 5;
		
		tickPerTime = 1000000000 / fps;
		lastTime = System.nanoTime();
		lastUpdateTime = System.nanoTime();
		now = 0;
		timer = 0;
		ticks = 0;
		while (running) {
			now = System.nanoTime();
			timer += (now -lastTime);
			updates = 0;
			while ((now -lastUpdateTime) >= tickPerTime) {
				float delta;
				delta = (now -lastUpdateTime) / 1000000000.0f;
				//  da implementare
				delta = delta <= 0.016f ? delta : 0.016f;
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
			if (tickPerTime > timeTake)
				try {
					Thread.sleep((long) ((tickPerTime - timeTake) / 1000000f));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			if (timer >= 1000000000) {
				// mainFrame.getFrame().setTitle("Frame Per Seconds : " + ticks);
				this.framesThisSecond = ticks;
				ticks = 0;
				timer = 0;
			}
		}
	}
	
	public void addKeyAdapter(KeyAdapter e) {
		mainFrame.getCanvas().addKeyListener(e);
		mainFrame.getFrame().addKeyListener(e);
	}
	
	public void addMouseAdapter(MouseAdapter e) {
		mainFrame.getCanvas().addMouseListener(e);
		mainFrame.getFrame().addMouseListener(e);		
		mainFrame.getCanvas().addMouseMotionListener(e);
		mainFrame.getFrame().addMouseMotionListener(e);
	}

	public void removeKeyAdapter(KeyAdapter e) {
		mainFrame.getCanvas().removeKeyListener(e);
		mainFrame.getFrame().removeKeyListener(e);
	}	
	
}
