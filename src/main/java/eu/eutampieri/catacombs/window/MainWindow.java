package eu.eutampieri.catacombs.window;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame frame;
	private Canvas canvas;

	public MainWindow(String name, int width, int height,boolean fullScreen,boolean resizeable) {
		
		this.frame = new JFrame(name);
		this.canvas = new Canvas();
		this.canvas.setSize(width, height);
		this.frame.setSize(width,height);
		this.frame.setResizable(resizeable);
        this.frame.add(canvas);
        if(fullScreen)makeFullScreen();
		
		this.frame.pack();	
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}	

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	private void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
			this.frame.setUndecorated(true);
			this.frame.setResizable(false);
			this.frame.setIgnoreRepaint(true);
			gd.setFullScreenWindow(this.frame);
		}
	}

	

	
}
