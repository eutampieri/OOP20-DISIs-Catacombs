package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

public class MenuState extends State {
	
	private static final int START_GAME = 1;
	private static final int QUIT_GAME = 2;
	
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private Font font = new Font("Arial", Font.PLAIN, 40);
	
	private int optionSelected = START_GAME;
	
	public MenuState(DungeonGame game) {
		super(game);
	}
	
	@Override
	public void update(float delta) {
		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_ENTER)) {
			switch(this.optionSelected) {
			case START_GAME:
				this.game.startgame();
				break;
			case QUIT_GAME:
				System.exit(0);
				break;
			}
		}
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_S)) {
			this.optionSelected = QUIT_GAME;
		}
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_W)) {
			this.optionSelected = START_GAME;
		}
		
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());
		g2.setColor(Color.white);
		
		// title
		g2.setFont(this.titleFont);
		String title = "CATACOMBS";
		float x = (Game.getWidth() - FontUtils.getTextWidth(titleFont, title)) / 2;
		g2.drawString(title, x, titleFont.getSize() + 20);
		
		// options
		g2.setFont(this.font);
		String start = "Start";
		float x1 = (Game.getWidth() - FontUtils.getTextWidth(font, start)) / 2;
		g2.drawString(start, x1, titleFont.getSize() + 80);
		String quit = "Quit";
		float x2 = (Game.getWidth() - FontUtils.getTextWidth(font, quit)) / 2;
		g2.drawString(quit, x2, titleFont.getSize() + font.getSize() + 100);
		
		// selection
		int x3 = (int) (this.optionSelected == START_GAME ? x1 - 30 : x2 - 30 );
		int y = this.optionSelected == START_GAME ? this.titleFont.getSize() + 55 : this.titleFont.getSize() + font.getSize() + 75;
		g2.fillOval(x3, y, 20, 20);
		
		
		
	}
	
}
