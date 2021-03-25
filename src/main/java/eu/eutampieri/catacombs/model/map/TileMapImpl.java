package eu.eutampieri.catacombs.model.map;

public final class TileMapImpl implements TileMap {
    private final Tile[][] map;

    public TileMapImpl(final Tile[][] m) {
        map = m;
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
        return map[y][x];
    }

}
