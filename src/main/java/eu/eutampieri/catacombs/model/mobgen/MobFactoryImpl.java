package eu.eutampieri.catacombs.model.mobgen;

import eu.eutampieri.catacombs.model.*;
import eu.eutampieri.catacombs.model.map.TileMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MobFactoryImpl implements MobFactory {

    private static final int MAX_MOB_NUMBER = 40;
    private static final int MOB_KIND_NUMBER = 2;

    private TileMap tileMap;
    private final transient Random rand = new Random();

    public MobFactoryImpl(final TileMap tileMap) {
        this.setNewTileMap(tileMap);
    }

    public void setNewTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public List<Entity> spawnAt(int x, int y, EntityFactory f) {
        if (f == null){
            return List.of();
        }
        List<Entity> enemies = new ArrayList<Entity>();
        enemies.add(f.create(x, y, this.tileMap));
        return enemies;
    }

    public List<Entity> spawnSome(int n, EntityFactory f) {
        if (f == null){
            return List.of();
        }
        int randX, randY;
        List<Entity> enemies = new ArrayList<Entity>();
        for (int i = 0; i < n; i++) {
            do {
                randX = rand.nextInt(tileMap.width());
                randY = rand.nextInt(tileMap.height());
            } while (!tileMap.canSpawnAt(randX, randY));
            enemies.addAll(spawnAt(randX, randY, f));
        }
        return enemies;
    }

    public List<Entity> spawnRandom() {
        int randX, randY, randKind;
        int mobNum = rand.nextInt(MAX_MOB_NUMBER);

        List<Entity> enemies = new ArrayList<Entity>();
        for (int i = 0; i < mobNum; i++) {
            do {
                randX = rand.nextInt(tileMap.width());
                randY = rand.nextInt(tileMap.height());
            } while (!tileMap.canSpawnAt(randX, randY));
            randKind = rand.nextInt(MOB_KIND_NUMBER);
            if (randKind == 0){
                enemies.addAll(spawnAt(randX, randY, Bat::new));
            }
            if (randKind == 1){
                enemies.addAll(spawnAt(randX, randY, Slime::new));
            }
        }
        return enemies;
    }
}