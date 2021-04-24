package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.Slime;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MobFactory Implementation.
 * @see MobFactory
 */
public final class MobFactoryImpl implements MobFactory {

    /**
     * The maximum number of mods generated.
     */
    public static final int MAX_MOB_NUMBER = 420 / 2;
    /**
     * Minimum number of mob to be generated.
     */
    public static final int MIN_MOB_NUMBER = 69;
    /**
     * Current number of mob kind excluding boss.
     */
    private static final int MOB_KIND_NUMBER = 2;

    private TileMap tileMap;
    private final transient Random rand = new Random();

    /**
     *
     * @param tileMap Tile map in which to spawn mobs
     */
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
    public List<Entity> spawnAt(final int x, final int y, final SingleObject<Entity> f) {
        if (f == null || !tileMap.canSpawnAt(x, y)) {
            return List.of();
        }
        final List<Entity> enemies = new ArrayList<>();
        enemies.add(f.create(x * AssetManagerProxy.getMapTileSize(), y * AssetManagerProxy.getMapTileSize(), this.tileMap));
        return enemies;
    }

    @Override
    public List<Entity> spawnSome(final int n, final SingleObject<Entity> f) {
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

    @Override
    public List<Entity> spawnNear(final int range, final GameObject e, final SingleObject<Entity> f) {
        int randX, randY;

        do {
            randX = rand.nextInt(range) + 1;
            randY = rand.nextInt(range) + 1;
            if (rand.nextBoolean()) {
                randX *= -1;
            }
            if (rand.nextBoolean()) {
                randY *= -1;
            }
            randX += e.getPosX() / AssetManagerProxy.getMapTileSize();
            randY += e.getPosY() / AssetManagerProxy.getMapTileSize();

        } while (!tileMap.canSpawnAt(randX, randY));
         return spawnAt(randX, randY, f);
    }
}
