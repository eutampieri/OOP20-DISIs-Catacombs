package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class SingleObjectFactoryImpl implements SingleObjectFactory {

    private TileMap tileMap;
    private final transient Random rand = new Random();

    /**
     *
     * @param tileMap Tile map in which to spawn mobs
     */
    public SingleObjectFactoryImpl(final TileMap tileMap) {
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
    public List<GameObject> spawnAt(final int x, final int y, final SingleObject<GameObject> f) {
        if (f == null || !tileMap.canSpawnAt(x, y)) {
            return List.of();
        }
        final List<GameObject> objects = new ArrayList<>();
        objects.add(f.create(x * AssetManagerProxy.getMapTileSize(), y * AssetManagerProxy.getMapTileSize(), this.tileMap));
        return objects;
    }

    @Override
    public List<GameObject> spawnSome(final int n, final SingleObject<GameObject> f) {
        if (f == null) {
            return List.of();
        }
        int randX, randY;
        final List<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            do {
                randX = rand.nextInt(tileMap.width());
                randY = rand.nextInt(tileMap.height());
            } while (!tileMap.canSpawnAt(randX, randY));
            objects.addAll(spawnAt(randX, randY, f));
        }
        return objects;
    }
}
