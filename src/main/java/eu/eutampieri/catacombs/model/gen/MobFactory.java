package eu.eutampieri.catacombs.model.gen;

import eu.eutampieri.catacombs.model.Entity;
import java.util.List;

public interface MobFactory {

    /**
     * Spawns a specified enemy at a certain location.
     * @param x X position
     * @param y Y position
     * @param f Type of enemy
     * @return A list composed of the only created entity
     */
    List<Entity> spawnAt(int x, int y, Obj f);

    /**
     * Spawns n entities of a specified kind.
     * @param n Number of entities
     * @param f Entity type
     * @return A list of the n entities
     */
    List<Entity> spawnSome(int n, Obj f);

    /**
     * Spawns some random entities at random positions in the map.
     * @return A list of the random entities
     */
    List<Entity> spawnRandom();
}
