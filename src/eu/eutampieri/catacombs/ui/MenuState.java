package eu.eutampieri.catacombs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import eu.eutampieri.catacombs.ui.utils.FontUtils;

public class MenuState extends State {
	
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private Font font = new Font("Arial", Font.PLAIN, 40);

	public LogicMenu logic;
	
	public MenuState(DungeonGame game) {
		super(game);
		this.logic = new LogicMenuImpl(game);
	}
	
	@Override
	public void update(float delta) {
		this.logic.selectOption();		
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
		int x3 = (int) (this.logic.isOptionStart() ? x1 - 30 : x2 - 30 );
		int y = this.logic.isOptionStart() ? this.titleFont.getSize() + 55 : this.titleFont.getSize() + font.getSize() + 75;
		g2.fillOval(x3, y, 20, 20);
		
		
		
	}
	
}
