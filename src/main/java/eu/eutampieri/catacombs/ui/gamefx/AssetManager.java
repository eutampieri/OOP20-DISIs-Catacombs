package eu.eutampieri.catacombs.ui.gamefx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import eu.eutampieri.catacombs.model.*;
import eu.eutampieri.catacombs.ui.utils.ImageLoader;
import eu.eutampieri.catacombs.ui.utils.ImageRotator;
import me.grison.jtoml.TomlParser;
import me.grison.jtoml.impl.Toml;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.text.html.Option;

public class AssetManager {

    private static final String PLAYER_SHEET = "/playersheet.png";
    private static final String SLIME_SHEET = "/slimesheet.png";
    private static final String BAT_SHEET = "/batsheet.png";
    private static final String GUN_SHEET = "/projectiles.png";
    private static final String PNG = ".png";
    private Map<String, Map<Action, Map<Optional<Direction>, List<BufferedImage>>>> assets;


    private static Map<String, BufferedImage[]> allAnimations = new HashMap<>();
    private final Map<String, BufferedImage> allImages = new HashMap<>();

    private ImageRotator imageRot;

    public static BufferedImage[] getFrames(final String key) {
        return allAnimations.get(key);
    }

    public BufferedImage getImage(final String key) {
        return this.allImages.get(key);
    }

    public String getImageKey(final BufferedImage image) {
        for (final Entry<String, BufferedImage> entry : this.allImages.entrySet()) {
            if (entry.getValue().equals(image)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void load() throws IOException {
        Toml toml;
        try {
            toml = Toml.parse(new File("assets.toml"));
        } catch (IOException exception) {
            throw exception;
        }
        this.assets = List.of("player", "slime")
                .stream()
                .map((character) -> {
                    var characterData = toml.getMap(character);
                    return Pair.of(character, characterData.entrySet().stream().map((x) -> {
                        Pair<Action, Map<Direction, List<BufferedImage>>> result;
                        Action action = Action.fromString(x.getKey());
                        Map<String, Object> value = (Map<String, Object>) x.getValue();
                        GameSheets slicer = new GameSheets((String) value.get("asset"));
                        List<Optional<Direction>> actionKeys = action.getDirections().stream().map(Optional::of).collect(Collectors.toList());
                        if (actionKeys.size() == 0) {
                            actionKeys = List.of(Optional.empty());
                        }
                        return Pair.of(action, actionKeys.stream().map((direction) -> {
                            List<BufferedImage> listOfKeyFrames = ((List<List<Long>>) value.get(direction.map((b) -> b.toString()).orElse(action.toString().toLowerCase())))
                                    .stream()
                                    .map((keyframeData) -> {
                                        return slicer.cutImage(
                                                keyframeData.get(0).intValue(),
                                                keyframeData.get(1).intValue(),
                                                keyframeData.get(2).intValue(),
                                                keyframeData.get(3).intValue()
                                        );
                                    }).collect(Collectors.toList());
                            return Pair.of(direction, listOfKeyFrames);
                        }).collect(Collectors.toMap(Pair::getLeft, Pair::getRight)));
                    }).collect(Collectors.toMap(Pair::getLeft, Pair::getRight)));
                }).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

    public List<BufferedImage> getFramesFor(String character, Action action, Optional<Direction> direction) {
        return assets.get(character).get(action).get(direction);
    }

    public BufferedImage horizontalFlip(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        final Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
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
        allImages.put("coin", tileSheet.cutImage(0, 144, 16, 16));
        allImages.put("skull", tileSheet.cutImage(16, 144, 16, 16));
        allImages.put("fire", tileSheet.cutImage(0, 160, 8, 21));
        allImages.put("potion", tileSheet.cutImage(0, 181, 9, 11));
        allImages.put("gun", tileSheet.cutImage(0, 193, 21, 17));
    }

    public void loadAnimations(final String name, final String image, final int numFrames, final int offset, final int dimension, final boolean flip) {
        final GameSheets sheet = new GameSheets(image);
        BufferedImage[] res = new BufferedImage[numFrames];
        for (int i = 0; i < numFrames; i++) {
            if (!flip) {
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
            res[i] = sheet.cutImage(offset + (dimension * i), y, dimension, dimension);
        }
        allAnimations.put(name, res);
    }


}
