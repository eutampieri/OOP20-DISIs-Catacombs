package eu.eutampieri.catacombs.model.map;

public interface TileMapFactory {
    TileMap def();

    TileMap seededDef(long seed);

    TileMap empty(int h, int w);
}
