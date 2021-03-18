package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MenuState extends State {
	
	private static final int START_GAME = 1;
	private static final int END_GAME = 2;
	
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private Font font = new Font("Arial", Font.PLAIN, 40);
	
	private int optionSelected = START_GAME;
	
	public MenuState(DungeonGame game) {
		super(game);
	}
	
	public void update() {
		// da completare
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(Color.black);
		// g2.fillRect(x, y, width, height);
		g2.setColor(Color.white);
		
		// title
		g2.setFont(this.titleFont);
		String title = "CATACOMBS";
		// da implementare position x
		
		// options
		g2.setFont(this.font);
		String start = "Start";
		// da implementare position x1
		String quit = "Quit";
		// da implementare position x2
		
		// selection
		int x3 = (int) (this.optionSelected == START_GAME ? x1 - 30 : x2 - 30 );
		int y = this.optionSelected == START_GAME ? this.titleFont.getSize() + 55 : this.titleFont.getSize() + font.getSize() + 75;
		g2.fillOval(x3, y, 20, 20);
		
		
		
	}
	
}
