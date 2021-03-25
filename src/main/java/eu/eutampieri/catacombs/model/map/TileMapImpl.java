package eu.eutampieri.catacombs.model.map;

public class TileMapImpl implements TileMap {
    Tile[][] m;

    public TileMapImpl(Tile[][] mm) {
        m=mm;
    }

    @Override
    public int height() {
        return m.length;
    }

    @Override
    public int width() {
        return m[0].length;
    }

    @Override
    public Tile at(int x, int y) {
        return m[y][x];
    }

}