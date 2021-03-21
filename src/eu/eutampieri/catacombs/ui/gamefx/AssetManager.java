package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AssetManager {
	
	private  HashMap<String,BufferedImage[]> allAnimations = new HashMap<String,BufferedImage[]>();
	
	public BufferedImage[] getFrames(String key) {
		return this.allAnimations.get(key);
	}
	
	public void load() {
		loadSlimeAnimations();
		loadBatAnimations();
	}
	
	public BufferedImage horizontalFlip(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(image ,0 ,0 ,width ,height ,width ,0 ,0 ,height , null);
		g.dispose();
		return flippedImage;
	}
	
	public void loadSlimeAnimations() {
		// Slime Animations
		
		GameSheets slimeSheet = new GameSheets("/slimesheet.png");
		BufferedImage[] slimeUp, slimeDown, slimeRight, slimeLeft;
		int numFramesSlime = 4;
		
		slimeUp = new BufferedImage[numFramesSlime];
		slimeDown = new BufferedImage[numFramesSlime];
		slimeRight = new BufferedImage[numFramesSlime];
		slimeLeft = new BufferedImage[numFramesSlime];
		
		for (int i = 0; i < numFramesSlime; i++) {
			slimeUp[i] = slimeSheet.cutImage(32 * i, 64, 32, 32);
			slimeDown[i] = slimeSheet.cutImage(32 * i, 0, 32, 32);
			slimeRight[i] = slimeSheet.cutImage(32 * i, 32, 32, 32);
			slimeLeft[i] = slimeSheet.cutImage(32 * i, 96, 32, 32);
		}
		
		this.allAnimations.put("slime_up", slimeUp);
		this.allAnimations.put("slime_down", slimeDown);
		this.allAnimations.put("slime_right", slimeRight);
		this.allAnimations.put("slime_left", slimeLeft);
	}
	
	public void loadBatAnimations() {
		// Bat animation
		
		GameSheets batSheet = new GameSheets("/batsheet.png");
		BufferedImage[] batRight ,batLeft;
		int numFramesBat = 3;
		
		batRight = new BufferedImage[numFramesBat];
		batLeft = new BufferedImage[numFramesBat];
		
		for (int i = 0; i < numFramesBat; i++) {
			batRight[i] = batSheet.cutImage(16 * i, 0, 16, 16);
			batLeft[i] = horizontalFlip(batRight[i]);
		}
		
		this.allAnimations.put("bat_right", batRight);
		this.allAnimations.put("bat_Left", batLeft);
		
	
	}
	
}
