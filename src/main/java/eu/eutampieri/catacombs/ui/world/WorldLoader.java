package eu.eutampieri.catacombs.ui.world;

import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapImpl;
import eu.eutampieri.catacombs.ui.utils.TextLoader;

import java.util.ArrayList;


public class WorldLoader {

    public static World load(String worldPath) {
        String map[] = TextLoader.loadText(worldPath).split(" ");

        //  load TILES
        int cols = Integer.parseInt(map[1]);
        int rows = Integer.parseInt(map[0]);

        Tile tiles[][] = new Tile[rows][cols];
        String[] tile = null;
        int count = 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++ ){
                int type = Integer.parseInt(tile[1]);
/*
                switch (type) {
                    case Tile.VOID:
                        break;
                    case Tile.WALL:
                        break;
                    case Tile.FLOOR:
                        break;
                }*/
            }
        }

        TileMap tileMap = new TileMapImpl(/*rows, cols,*/ tiles);

        // entities
        ArrayList<Slime> slimes = new ArrayList<Slime>();
        ArrayList<Bat> bats = new ArrayList<Bat>();
        Player player = null;
        String object = null;
        int x = 0, y = 0;
        for (int i = count; i < map.length; i += 3) {
            object = map[i];
            x = Integer.parseInt(map[i + 1]);
            y = Integer.parseInt(map[i + 2]);

            switch (object) {
                case "player":
                    player = new Player(/*tileMap,*/ x, y, "name");
                    break;
                case "slime":
                    slimes.add(new Slime(/*tileMap,*/ x, y));
                    break;
                case "bat":
                    bats.add(new Bat(/*tileMap,*/ x, y));
                    break;
            }
        }

        World world = new World(tileMap, slimes, bats);
        world.setPlayer(player);

        return world;



    }
}
