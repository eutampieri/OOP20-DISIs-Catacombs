package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import eu.eutampieri.catacombs.ui.utils.ImageRotator;

public class AssetManager {

	private static final String PLAYER_SHEET = "/playersheet.png";
	private static final String SLIME_SHEET = "/slimesheet.png";
	private static final String BAT_SHEET = "/batsheet.png";
	private static final String GUN_SHEET = "/projectiles.png";
	private static final String PNG = ".png";

	
	private static Map<String,BufferedImage[]> allAnimations = new HashMap<>();
	private final Map<String,BufferedImage> allImages = new HashMap<>();
	
	private ImageRotator imageRot;
	
	public static BufferedImage[] getFrames(final String key) {
		return allAnimations.get(key);
	}
	
	public BufferedImage getImage(final String key) {
		return this.allImages.get(key);
	}
	
	public String getImageKey(final BufferedImage image) {
		for (final Entry<String,BufferedImage> entry : this.allImages.entrySet()) {
			if (entry.getValue().equals(image)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public void load() {
		loadAnimations("Walk_up", PLAYER_SHEET, 8, 3, 32, false);
		loadAnimations("Walk_down", PLAYER_SHEET, 8, 2, 32, false );
		loadAnimations("Walk_left", PLAYER_SHEET, 8, 1, 32, false );
		loadAnimations("Walk_right", PLAYER_SHEET,8, 0, 32, false );

		loadAnimations("Die", PLAYER_SHEET, 8, 128, 32, false );

		loadAnimations("Attack_up", PLAYER_SHEET, 8, 5, 32, false );
		loadAnimations("Attack_down", PLAYER_SHEET, 8, 6, 32, false );
		loadAnimations("Attack_left", PLAYER_SHEET, 8, 8, 32, false );
		loadAnimations("Attack_right", PLAYER_SHEET, 8, 7, 32, false );

		loadAnimations("Slime_up", SLIME_SHEET, 4, 2, 32, false );
		loadAnimations("Slime_down", SLIME_SHEET, 4, 0, 32, false );
		loadAnimations("Slime_left", SLIME_SHEET, 4, 3, 32, false );
		loadAnimations("Slime_right", SLIME_SHEET, 4, 1, 32, false );

		loadAnimations("Bat_right", BAT_SHEET, 3, 7, 32, false );
		loadAnimations("Bat_left", BAT_SHEET, 3, 7, 32, true );

		loadBossAnimations("Boss_Idle_right",  6, false, true);
		loadBossAnimations("Boss_Idle_left",  6, true, true);
		loadBossAnimations("Boss_Walk_right",  6, false, false);
		loadBossAnimations("Boss_Walk_right",  6, true, false);

		loadGunAnimations("Projectile_1", GUN_SHEET, 3, 1226, 64, 26);
		loadGunAnimations("Projectile_2", GUN_SHEET, 5, 1418, 46, 118);
		loadGunAnimations("Projectile_3", GUN_SHEET, 3, 1798, 37, 1187);
		loadGunAnimations("Projectile_4", GUN_SHEET, 5, 2636, 64, 0);

		loadImages();
	}
	
	public BufferedImage horizontalFlip(final BufferedImage image) {
		final int width = image.getWidth();
		final int height = image.getHeight();
		final BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
		final Graphics2D g = flippedImage.createGraphics();
		g.drawImage(image ,0 ,0 ,width ,height ,width ,0 ,0 ,height , null);
		g.dispose();
		return flippedImage;
	}
	
	public void loadImages() {
		// Tiles
		final GameSheets tileSheet = new GameSheets("/tileSheet.png");
		int count = 1;
		final BufferedImage image = tileSheet.cutImage(112, 0, 16, 16);
		
		allImages.put(String.valueOf(count++), image);
		allImages.put(String.valueOf(count++), imageRot.rotate(image, 90));
		allImages.put(String.valueOf(count++), imageRot.rotate(image, 180));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112, 16, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 16, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 16, 16, 16));
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; i < 7; j++) {
				if (i == 0 && j < 3) {
					BufferedImage img;
					img = tileSheet.cutImage(j * 16, i * 16, 16, 16);
					this.allImages.put(String.valueOf(count++), img);
					this.allImages.put(String.valueOf(count++), imageRot.rotate(image, 90));
					this.allImages.put(String.valueOf(count++), imageRot.rotate(image, 180));
					this.allImages.put(String.valueOf(count++), imageRot.rotate(image, 270));
				} else {
					this.allImages.put(String.valueOf(count++), tileSheet.cutImage(j * 16, i * 16, 16, 16));
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

	public void loadAnimations(final String name, final String image, final int numFrames, final int offset, final int dimension, final boolean flip) {
		final GameSheets sheet = new GameSheets(image);
		BufferedImage[] res = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {
			if (!flip){
				res[i] = sheet.cutImage(dimension * i, offset * dimension, dimension, dimension);
			} else {
				res[i] = horizontalFlip(sheet.cutImage(dimension * i, offset * dimension, dimension, dimension));
			}

		}
		allAnimations.put(name, res);
	}

	public void loadBossAnimations(final String name, final int numFrames, final boolean flip, final boolean idle) {
		BufferedImage[] res = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {
			if (idle) {
				if (!flip) {
					res[i] = ImageLoader.loadImage("/boss/Golem_Idle_" + (i + 1) + PNG);
				} else {
					res[i] = horizontalFlip(ImageLoader.loadImage("/boss/Golem_Idle_" + (i + 1) + PNG));
				}
			} else {
				if (!flip) {
					res[i] = ImageLoader.loadImage("/boss/Golem_Walk_" + (i + 1) + PNG);
				} else {
					res[i] = horizontalFlip(ImageLoader.loadImage("/boss/Golem_Walk_" + (i + 1) + PNG));
				}
			}



		}
		allAnimations.put(name, res);
	}

	public void loadGunAnimations(final String name, final String image, final int numFrames, final int y, final int dimension, final int offset) {
		final GameSheets sheet = new GameSheets(image);
		BufferedImage[] res = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {
			res[i] = sheet.cutImage( offset + ( dimension * i ), y, dimension, dimension);
		}
		allAnimations.put(name, res);
	}



}
