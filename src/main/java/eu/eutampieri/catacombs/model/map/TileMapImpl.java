package eu.eutampieri.catacombs.model.map;

public final class TileMapImpl implements TileMap {
    public static final int TILE_SIZE = 16;

    private final Tile[][] map;

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

    public Tile[][] getMap() {
        return map.clone();
    }

    @Override
    public boolean canSpawnAt(final int x, final int y) {
        return this.at(x, y).isWalkable() && this.at(x - 1, y).isWalkable() && this.at(x + 1, y).isWalkable()
                && this.at(x, y - 1).isWalkable() && this.at(x, y + 1).isWalkable();
    }
}
