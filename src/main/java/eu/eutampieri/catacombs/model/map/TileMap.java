package eu.eutampieri.catacombs.model.map;

public interface TileMap {
    int height();

    int width();

    Tile at(int x, int y);
}
