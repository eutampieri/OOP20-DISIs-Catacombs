package eu.eutampieri.catacombs.model.map;

/**
 * A TileMap.
 */
public final class TileMapImpl implements TileMap {

    private final Tile[][] map;

    /**
     * constructs a tilemaps from a Tile[][].
     * 
     * @param m a matrix of Tiles.
     */
    public TileMapImpl(final Tile[][] m) {
        map = m.clone();
    }

    @Override
    public int height() {
        return map.length;
    }

    @Override
    public int width() {
        return map[0].length;
    }

    @Override
    public Tile at(final int x, final int y) {
        if (y < 0 || x < 0 || y >= this.height() || x >= this.width()) {
            return Tile.VOID;
        }
        return map[y][x];
    }

    /**
     * @return Internal representation of the tilemap. used for testing.
     */
    public Tile[][] getMap() {
        return map.clone();
    }

    @Override
    public boolean canSpawnAt(final int x, final int y) {
        return this.at(x, y).isWalkable() && this.at(x - 1, y).isWalkable() && this.at(x + 1, y).isWalkable()
                && this.at(x, y - 1).isWalkable() && this.at(x, y + 1).isWalkable();
    }
}
