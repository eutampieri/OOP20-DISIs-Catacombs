package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.GameObject;

import java.util.List;

public interface SingleObjectFactory {
    /**
     * Spawns a specified object at a certain location.
     * @param x X position
     * @param y Y position
     * @param f Type of object
     * @return A list composed of the only created entity
     */
    List<GameObject> spawnAt(int x, int y, SingleObject<GameObject> f);

    /**
     * Spawns n objects of a specified kind.
     * @param n Number of objects
     * @param f Entity type
     * @return A list of the n entities
     */
    List<GameObject> spawnSome(int n, SingleObject<GameObject> f);

}
