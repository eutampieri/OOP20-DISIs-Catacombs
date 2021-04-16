package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import eu.eutampieri.catacombs.model.Camera;

import java.awt.*;
import java.util.List;

public class World {

    // private final BufferedImage background;
    private final TileMap tileMap;
    private final AssetManager am = AssetManager.getAssetManager();
    // private final DungeonGame game = new DungeonGame();
    // TODO Camera
    private final Camera camera;

    private final List<GameObject> entities;

    private Player player;

    public World(final TileMap tileMap, final List<GameObject> entities) {
        // this.background = am.getImage("background");
        this.tileMap = tileMap;
        camera = new Camera(0, 0, tileMap.width() * 16, tileMap.height() * 16);
        this.entities = entities;
        this.player = new Player(1000, 1000, "");
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public void update(final long delta) {
        player.update(delta);

        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).update(delta);
        }
    }

    public void render(final Graphics2D g2) {
        camera.centerOnEntity(this.player);
        // g2.drawImage(background, 0, 0, game.getGameWidth(), game.getGameHeight(),
        // null);
        /*
         * for(int y=0; y<7; y++){ for(int x=0; x<7; x++){ var i = y*7+x; var im =
         * am.getImage(i+""); g2.drawImage(im, null, x*16, y*16); } }
         */
        for (int y = 0; y < tileMap.height(); y++) {
            for (int x = 0; x < tileMap.width(); x++) {
                if (tileMap.at(x, y) == Tile.FLOOR) {
                    g2.drawImage(am.getImage("41"), null, x * 16 - camera.getXOffset(), y * 16 - camera.getYOffset());
                } else if (tileMap.at(x, y) == Tile.WALL) {
                    g2.drawImage(am.getImage("25"), null, x * 16 - camera.getXOffset(), y * 16 - camera.getYOffset());
                }
            }
        }

        // TODO render method
        // this.tileMap.render(g2, camera);

        // slimes
        /*
         * for (int i = 0; i < entities.size(); i++) { // TODO entities.render
         * parameters // this.entities.get(i).render(g2, camera); }
         */

        // TODO player.render parameters
        // this.player.render(g2, camera);

    }

}
