package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactory;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.ui.world.World;
import eu.eutampieri.catacombs.ui.world.WorldLoader;

import java.awt.*;
import java.util.ArrayList;

public class WorldEditState extends State {

    int tile;
    int type;

    boolean grid = false;

    private World world;
    TileMapFactory map = new TileMapFactoryImpl();
    TileMap tileMap = map.def();
    private ArrayList<Slime> slimes;
    private ArrayList<Bat> bats;
    private int numSlimes, numBats;

    private Font font = new Font("Arial", Font.BOLD, 15);
    String[] modes = {"none", "Tile", "slime", "bat"};
    String[] types = {"empty", "solid", "notsolid"};

    int mode;


    public WorldEditState(DungeonGame game) {
        super(game);
        this.world = WorldLoader.load(""/*map.txt file*/);
        this.world.setPlayer(new Player(/*world.getTileMap(),*/ 32 * 3, 32 * 5, "name"));
        this.tileMap = world.getTileMap();

        this.slimes = world.getSlime();
        this.numSlimes = slimes.size() - 1;
        this.bats = world.getBats();
        this.numBats = bats.size() - 1;
    }

    @Override
    public void update(float delta) {
        world.update(delta);

    }

    @Override
    public void render(Graphics2D g2) {
        world.render(g2);
        g2.setColor((Color.BLACK));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 21; j++){
                g2.drawRect(j * tileMap.width(), i * tileMap.height(),
                            tileMap.width(), tileMap.height());


            }
        }
    }

    private void write(TileMap map){

    }

}
