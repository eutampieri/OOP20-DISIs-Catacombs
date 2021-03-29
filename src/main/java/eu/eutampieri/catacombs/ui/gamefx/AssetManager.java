package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import eu.eutampieri.catacombs.ui.utils.ImageRotator;

public class AssetManager {

	private static final Path PLAYER_SHEET = Path.of("/playersheet.png");
	private static final Path SLIME_SHEET = Path.of("/slimesheet.png");
	private static final Path BAT_SHEET = Path.of("/batsheet.png");
	private static final Path GUN_SHEET = Path.of("/projectiles.png");
	private static final String PNG = ".png";
	private static final ImageRotator IMAGE_ROTATOR = new ImageRotator();

	
	private static Map<String,ArrayList<Optional<BufferedImage>>> allAnimations = new HashMap<>();
	private final Map<String,BufferedImage> allImages = new HashMap<>();
	
	public static List<Optional<BufferedImage>> getFrames(final String key) {
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
	
	public Optional<BufferedImage> horizontalFlip(final Optional<BufferedImage> image) {
		final int width = image.get().getWidth();
		final int height = image.get().getHeight();
		final BufferedImage flippedImage = new BufferedImage(width, height, image.get().getType());
		final Graphics2D g = flippedImage.createGraphics();
		g.drawImage(image.get() ,0 ,0 ,width ,height ,width ,0 ,0 ,height , null);
		g.dispose();
		return Optional.of(flippedImage);
	}
	
	public void loadImages() {
		// Tiles
		final GameSheets tileSheet = new GameSheets(Path.of("/tileSheet.png"));
		int count = 1;
		final Optional<BufferedImage> image = Optional.of(tileSheet.cutImage(112, 0, 16, 16));
		
		allImages.put(String.valueOf(count++), image.get());
		allImages.put(String.valueOf(count++), IMAGE_ROTATOR.rotate(image.get(), 90));
		allImages.put(String.valueOf(count++), IMAGE_ROTATOR.rotate(image.get(), 180));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 0, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112, 16, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 16, 16, 16));
		allImages.put(String.valueOf(count++), tileSheet.cutImage(112 + 16, 16, 16, 16));
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 0 && j < 3) {
					Optional<BufferedImage> img;
					img = Optional.of(tileSheet.cutImage(j * 16, i * 16, 16, 16));
					this.allImages.put(String.valueOf(count++), img.get());
					this.allImages.put(String.valueOf(count++), IMAGE_ROTATOR.rotate(image.get(), 90));
					this.allImages.put(String.valueOf(count++), IMAGE_ROTATOR.rotate(image.get(), 180));
					this.allImages.put(String.valueOf(count++), IMAGE_ROTATOR.rotate(image.get(), 270));
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

	public void loadAnimations(final String name, final Path image, final int numFrames, final int offset, final int dimension, final boolean flip) {
		final GameSheets sheet = new GameSheets(image);
		final ArrayList<Optional<BufferedImage>> res = new ArrayList<>();
		for (int i = 0; i < numFrames; i++) {
			if (!flip){
				res.set(i, Optional.of(sheet.cutImage(dimension * i, offset * dimension, dimension, dimension)));
			} else {
				res.set(i,horizontalFlip(Optional.of(sheet.cutImage(dimension * i, offset * dimension, dimension, dimension))));
			}

		}
		allAnimations.put(name, res);
	}

	public void loadBossAnimations(final String name, final int numFrames, final boolean flip, final boolean idle) {
		final ArrayList<Optional<BufferedImage>> res = new ArrayList<>();
		for (int i = 0; i < numFrames; i++) {
			if (idle) {
				if (!flip) {
					res.set(i,ImageLoader.loadImage(Path.of("/boss/Golem_Idle_" + (i + 1) + PNG)));
				} else {
					res.set(i, horizontalFlip(ImageLoader.loadImage(Path.of("/boss/Golem_Idle_" + (i + 1) + PNG))));
				}
			} else {
				if (!flip) {
					res.set(i,ImageLoader.loadImage(Path.of("/boss/Golem_Walk_" + (i + 1) + PNG)));
				} else {
					res.set(i, horizontalFlip(ImageLoader.loadImage(Path.of("/boss/Golem_Walk_" + (i + 1) + PNG))));
				}
			}



		}
		allAnimations.put(name, res);
	}

	public void loadGunAnimations(final String name, final Path image, final int numFrames, final int y, final int dimension, final int offset) {
		final GameSheets sheet = new GameSheets(image);
		final ArrayList<Optional<BufferedImage>> res = new ArrayList<>();
		for (int i = 0; i < numFrames; i++) {
			res.set(i, Optional.of(sheet.cutImage( offset + ( dimension * i ), y, dimension, dimension)));
		}
		allAnimations.put(name, res);
	}



}
