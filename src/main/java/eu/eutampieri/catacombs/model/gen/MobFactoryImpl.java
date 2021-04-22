package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MobFactoryImpl implements MobFactory {

    /**
     * The maximum number of mods generated.
     */
    public static final int MAX_MOB_NUMBER = 420 / 2;
    /**
     * minimum number of mob to be generated.
     */
    public static final int MIN_MOB_NUMBER = 69;
    private static final int MOB_KIND_NUMBER = 2;

    private TileMap tileMap;
    private final transient Random rand = new Random();

    public MobFactoryImpl(final TileMap tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * Sets a new TileMap. Useful if you change map mid-game.
     * @param tileMap The TileMap to be changed into
     */
    public void setNewTileMap(final TileMap tileMap) {
        this.tileMap = tileMap;
    }

    @Override
    public List<Entity> spawnAt(final int x, final int y, final SingleObject f) {
        if (f == null || !tileMap.canSpawnAt(x, y)) {
            return List.of();
        }
        final List<Entity> enemies = new ArrayList<>();
        enemies.add(f.create(x * AssetManagerProxy.getMapTileSize(), y * AssetManagerProxy.getMapTileSize(), this.tileMap));
        return enemies;
    }

    @Override
    public List<Entity> spawnSome(final int n, final SingleObject f) {
        if (f == null) {
            return List.of();
        }
        int randX, randY;
        final List<Entity> enemies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            do {
                randX = rand.nextInt(tileMap.width());
                randY = rand.nextInt(tileMap.height());
            } while (!tileMap.canSpawnAt(randX, randY));
            enemies.addAll(spawnAt(randX, randY, f));
        }
        return enemies;
    }

    @Override
    public List<Entity> spawnRandom() {
        int randX, randY, randKind;
        final int mobNum = rand.nextInt(MAX_MOB_NUMBER - MIN_MOB_NUMBER) + MIN_MOB_NUMBER;

        final List<Entity> enemies = new ArrayList<>();
        for (int i = 0; i < mobNum; i++) {
            do {
                randX = rand.nextInt(tileMap.width());
                randY = rand.nextInt(tileMap.height());
            } while (!tileMap.canSpawnAt(randX, randY));
            randKind = rand.nextInt(MOB_KIND_NUMBER);
            if (randKind == 0) {
                enemies.addAll(spawnAt(randX, randY, Bat::new));
            }
            if (randKind == 1) {
                enemies.addAll(spawnAt(randX, randY, Slime::new));
            }
        }
        return enemies;
    }
}
