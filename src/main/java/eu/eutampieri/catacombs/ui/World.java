package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class World {

    private BufferedImage background;
    private TileMap tileMap;
    private AssetManager am;
    private DungeonGame game = new DungeonGame();
    //TODO Camera
    //private Camera camera;

    private List<Slime> slimes;
    private List<Bat> bats;

    private Player player;

    public World(TileMap tileMap, List<Slime> slimes, List<Bat> bats) {
        this.background = am.getImage("background");
        this.tileMap = tileMap;
        //camera = new Camera();
        this.slimes = slimes;
        this.bats = bats;
    }
    // TODO getCamera
    /*public Camera getCamera() {
        return camera;
    }*/

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public List<Slime> getSlimes() {
        return this.slimes;
    }

    public void setSlimes(List<Slime> slimes) {
        this.slimes = slimes;
    }

    public List<Bat> getBats() {
        return this.bats;
    }

    public void setBats(List<Bat> bats) {
        this.bats = bats;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void update(float delta)  {
        player.update(delta);

        for (int i = 0; i < slimes.size(); i++) {
            this.slimes.get(i).update(delta);
        }
        for (int i = 0; i < bats.size(); i++) {
            this.bats.get(i).update(delta);
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(background, 0, 0, game.getGameWidth(), game.getGameHeight(), null);
        // TODO render method
        // this.tileMap.render(g2, camera);

        // slimes
        for (int i = 0; i < slimes.size(); i++) {
            // TODO slime.render parameters
            // this.slimes.get(i).render(g2, camera);
        }
        for (int i = 0; i < bats.size(); i++) {
            // TODO slime.render parameters
            // this.bats.get(i).render(g2, camera);
        }

        // TODO player.render parameters
        // this.player.render(g2, camera);

    }

}
