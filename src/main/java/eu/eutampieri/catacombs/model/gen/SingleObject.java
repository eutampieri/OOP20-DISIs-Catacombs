package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.TileMap;

public interface SingleObject<T extends GameObject> {
    T create(int x, int y, TileMap tm);
}
