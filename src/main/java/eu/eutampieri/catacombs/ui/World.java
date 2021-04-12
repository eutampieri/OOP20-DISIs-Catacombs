package eu.eutampieri.catacombs.ui;


import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class World {

    private final BufferedImage background;
    private final TileMap tileMap;
    private final AssetManager am = AssetManager.getAssetManager();
    private final DungeonGame game = new DungeonGame();
    //TODO Camera
    //private Camera camera;

    private final List<GameObject> entities;

    private Player player;

    public World(final TileMap tileMap, final List<GameObject> entities) {
        this.background = am.getImage("background");
        this.tileMap = tileMap;
        //camera = new Camera();
        this.entities = entities;
    }
    // TODO getCamera
    /*public Camera getCamera() {
        return camera;
    }*/

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public void update(final long delta)  {
        player.update(delta);

        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).update(delta);
        }
    }

    public void render(final Graphics2D g2) {
        g2.drawImage(background, 0, 0, game.getGameWidth(), game.getGameHeight(), null);
        // TODO render method
        // this.tileMap.render(g2, camera);

        // slimes
        /*for (int i = 0; i < entities.size(); i++) {
            // TODO entities.render parameters
            // this.entities.get(i).render(g2, camera);
        }*/

        // TODO player.render parameters
        // this.player.render(g2, camera);

    }

}
