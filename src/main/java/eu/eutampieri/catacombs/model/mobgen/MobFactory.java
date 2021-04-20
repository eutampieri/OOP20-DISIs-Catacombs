package eu.eutampieri.catacombs.model.mobgen;

import eu.eutampieri.catacombs.model.Entity;
import java.util.List;

public interface MobFactory{

    List<Entity> spawnAt(int x, int y, EntityFactory f);

    List<Entity> spawnSome(int n, EntityFactory f);

    List<Entity> spawnRandom();
}
