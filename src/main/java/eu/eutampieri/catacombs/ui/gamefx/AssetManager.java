package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import eu.eutampieri.catacombs.ui.utils.ImageRotator;

public class AssetManager {
	
	private  static final HashMap<String,BufferedImage[]> allAnimations = new HashMap<String,BufferedImage[]>();
	private final HashMap<String,BufferedImage> allImages = new HashMap<String,BufferedImage>();
	
	private ImageRotator imageRot;
	
	public static BufferedImage[] getFrames(String key) {
		return allAnimations.get(key);
	}
	
	public BufferedImage getImage(String key) {
		return this.allImages.get(key);
	}
	
	public String getImageKey(BufferedImage image) {
		for (Entry<String,BufferedImage> entry : this.allImages.entrySet()) {
			if (entry.getValue() == image) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public void load() {
		loadSlimeAnimations();
		loadBatAnimations();
		loadPlayerAnimations();
		loadImages();
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
	
	public void loadImages() {
		// Tiles
		GameSheets tileSheet = new GameSheets("/tileSheet.png");
		int count = 1;
		BufferedImage image = tileSheet.cutImage(112, 0, 16, 16);
		
		this.allImages.put("" + count++, image);
		allImages.put("" + count++, imageRot.rotate(image, 90));
		allImages.put("" + count++, imageRot.rotate(image, 180));
		allImages.put("" + count++, tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put("" + count++, tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put("" + count++, tileSheet.cutImage(112, 16, 16, 16));
		allImages.put("" + count++, tileSheet.cutImage(112 + 16, 16, 16, 16));
		allImages.put("" + count++, tileSheet.cutImage(112 + 16, 16, 16, 16));
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; i < 7; j++) {
				if (i == 0 && j < 3) {
					BufferedImage img;
					img = tileSheet.cutImage(j * 16, i * 16, 16, 16);
					this.allImages.put("" + count++, img);
					this.allImages.put("" + count++, imageRot.rotate(image, 90));
					this.allImages.put("" + count++, imageRot.rotate(image, 180));
					this.allImages.put("" + count++, imageRot.rotate(image, 270));
				} else {
					this.allImages.put("" + count++, tileSheet.cutImage(j * 16, i * 16, 16, 16));
				}
			}
		}
		
		// background
		this.allImages.put("background", tileSheet.cutImage(64, 96, 16, 16));
		
		// world objects
		allImages.put("coin", tileSheet.cutImage(0, 144, 16,16));
		allImages.put("skull", tileSheet.cutImage(16, 144, 16,16));
		allImages.put("fire", tileSheet.cutImage(0, 160, 8, 21));
		allImages.put("potion", tileSheet.cutImage(0,181,9,11));
		allImages.put("gun", tileSheet.cutImage(0, 193, 21, 17));
	}
	
	public void loadPlayerAnimations() {
		// Player Animations
		GameSheets playerSheet = new GameSheets("/playersheet.png");

		// walk animations
		BufferedImage[] playerUp, playerDown, playerRight ,playerLeft;
		int numFramesPlayer = 8;

		playerUp = new BufferedImage[numFramesPlayer];
		playerDown = new BufferedImage[numFramesPlayer];
		playerRight = new BufferedImage[numFramesPlayer];
		playerLeft = new BufferedImage[numFramesPlayer];
		
		for (int i = 0; i < numFramesPlayer; i++) {
			playerUp[i] = playerSheet.cutImage(32 * i, 96, 32, 32);
			playerDown[i] = playerSheet.cutImage(32 * i, 64, 32, 32);
			playerRight[i] = playerSheet.cutImage(32 * i, 0 , 32, 32);
			playerLeft[i] = playerSheet.cutImage(32 * i, 32, 32, 32);
		}
		
		allAnimations.put("walk_up", playerUp);
		allAnimations.put("walk_down", playerDown);
		allAnimations.put("walk_right", playerRight);
		allAnimations.put("walk_left", playerLeft);

		// die animations
		BufferedImage[] die;

		die = new BufferedImage[numFramesPlayer];
		
		for (int i = 0; i < numFramesPlayer; i++) {
			die[i] = playerSheet.cutImage(32 * i, 128 , 32, 32);
		}
		
		allAnimations.put("die", die);

		// attack animations
		BufferedImage[] attackUp, attackDown, attackLeft, attackRight;
		attackUp = new BufferedImage[numFramesPlayer];
		attackDown = new BufferedImage[numFramesPlayer];
		attackRight = new BufferedImage[numFramesPlayer];
		attackLeft = new BufferedImage[numFramesPlayer];
		
		for (int i = 0; i < numFramesPlayer; i++) {
			attackUp[i] = playerSheet.cutImage(32 * i, 160, 32, 32);
			attackDown[i] = playerSheet.cutImage(32 * i, 192, 32, 32);
			attackRight[i] = playerSheet.cutImage(32 * i, 224, 32, 32);
			attackLeft[i] = playerSheet.cutImage(32 * i, 256, 32, 32);
		}
		
		allAnimations.put("attack_up", attackUp);
		allAnimations.put("attack_down", attackDown);
		allAnimations.put("attack_Right", attackRight);
		allAnimations.put("attack_Left", attackLeft);

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

		allAnimations.put("slime_up", slimeUp);
		allAnimations.put("slime_down", slimeDown);
		allAnimations.put("slime_right", slimeRight);
		allAnimations.put("slime_left", slimeLeft);
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

		allAnimations.put("bat_right", batRight);
		allAnimations.put("bat_Left", batLeft);


	}
	
}
