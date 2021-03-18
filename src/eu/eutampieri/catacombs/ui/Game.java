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
	private int frameThisSecond;
	private boolean running = true;
	protected Graphics2D graphics;
	protected int fps;
	
	public Game() {
		
	}
	
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
		
	// da continuare
	
	
}
