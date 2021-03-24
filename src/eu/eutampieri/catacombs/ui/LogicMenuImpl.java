package eu.eutampieri.catacombs.ui;

import java.awt.event.KeyEvent;

public class LogicMenuImpl implements LogicMenu {
	
	private static final int START_GAME = 1;
	private static final int QUIT_GAME = 2;
	
	private int optionSelected = START_GAME;
	
	public DungeonGame game;
	
	public LogicMenuImpl(DungeonGame game) {
		this.game = game;
		
	}

	@Override
	public void start() {
		this.game.start();
	}

	@Override
	public void selectOption() {
		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_ENTER)) {
			switch(this.optionSelected) {
			case START_GAME:
				start();
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
	
	public int getCurrentOption() {
		return this.optionSelected;
		
	}
	
	public boolean isOptionStart() {
		return getCurrentOption() == START_GAME;
	}
	
}
