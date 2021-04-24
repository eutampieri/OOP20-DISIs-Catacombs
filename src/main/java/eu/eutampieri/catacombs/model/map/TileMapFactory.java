package eu.eutampieri.catacombs.model.map;

/**
 * A factory for TileMap.
 */
public interface TileMapFactory {
    /**
     * @return a TileMap created with the default settings and a non deterministic
     *         seed.
     */
    TileMap def();

    /**
     * @param seed seed for the rng.
     * @return a TileMap created with the default settings and a given seed.
     */
    TileMap seededDef(long seed);

    /**
     * @param h height of the TileMap in tiles.
     * @param w Width of the TileMap in tiles.
     * @return a TileMap full of Floor and bordered by Wall.
     */
    TileMap empty(int h, int w);
}
