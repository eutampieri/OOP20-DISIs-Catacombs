package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.TileMap;

/**
 * A single object to be created.
 * @param <T> A class extending GameObject
 * @see GameObject
 */
public interface SingleObject<T extends GameObject> {
    T create(int x, int y, TileMap tm);
}
