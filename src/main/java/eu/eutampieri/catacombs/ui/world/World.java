package eu.eutampieri.catacombs.ui.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;

public class World {

    private TileMap tileMap;
    private BufferedImage background;
    private AssetManager assetManager;
    private ArrayList<Slime> slimes;
    private ArrayList<Bat> bats;
    private Player player;


    public World(TileMap tileMap, ArrayList<Slime> slimes, ArrayList<Bat> bats){
        this.background = assetManager.getImage("background");
        this.tileMap = tileMap;
        this.slimes = slimes;
        this.bats = bats;
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public Player getPlayer() {
        return this.getPlayer();
    }

    public ArrayList<Slime> getSlime() {
        return this.slimes;
    }
    public ArrayList<Bat> getBats() {
        return bats;
    }

    public void setBats(ArrayList<Bat> bats) {
        this.bats = bats;
    }

    public void setSlimes(ArrayList<Slime> slimes){
        this.slimes = slimes;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void update(float delta){
        // palyer
        this.player.update();
        // slimes
        for (int i = 0; i < this.slimes.size(); i++) {
            this.slimes.get(i).update(/*delta*/);
        }
        //bats
        for (int i = 0; i < this.bats.size(); i++) {
            this.bats.get(i).update(/*delta*/);
        }
    }

    public void render(Graphics2D g2){
        g2.drawImage(this.background, 0, 0, Game.getGameWidth(), Game.getGameHeight(), null);
         // TODO this.tileMap.render
        this.player.render(/*g2, camera*/);
        for ( int i = 0; i < this.slimes.size(); i++) {
            this.slimes.get(i).render(/*g2, camera*/);
        }
        for ( int i = 0;  i < this.bats.size(); i++) {
            this.bats.get(i).render(/*g2, camera*/);
        }

        /*public void drawDebug(){

        }*/
    }
}
