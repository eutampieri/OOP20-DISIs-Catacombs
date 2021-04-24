package eu.eutampieri.catacombs.model.map;

/**
 * TileMap represents a map for the game.
 */
public interface TileMap {
    /**
     * @return the height of the map in tiles.
     */
    int height();

    /**
     * @return the width of the map in tiles.
     */
    int width();

    /**
     * @param x column.
     * @param y row.
     * @return the Tile at column x and row y in the TileMap.
     */
    Tile at(int x, int y);

    /**
     * @param x column.
     * @param y row.
     * @return if the Tile at column x and row y is a tile an entity can spawn on.
     */
    boolean canSpawnAt(int x, int y);
}
