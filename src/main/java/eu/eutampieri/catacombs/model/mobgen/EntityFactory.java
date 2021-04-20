package eu.eutampieri.catacombs.model.mobgen;

import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.map.TileMap;

public interface EntityFactory {
    Entity create(int x, int y, TileMap tm);
}
