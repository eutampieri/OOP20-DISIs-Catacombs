package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.model.mobgen.MobFactoryImpl;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MobFactoryTest {
    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(200, 200);
    private final static MobFactoryImpl mf = new MobFactoryImpl(TILE_MAP);

    @Test
    void testSpawnAt() {
        List<GameObject> entities;
        entities = mf.spawnAt(5, 5, Bat::new).stream().map((x) -> (GameObject)x).collect(Collectors.toList());
        assertEquals(entities.get(0).getPosX(),  5 * 16);
        assertEquals(entities.get(0).getPosY(),  5 * 16);
    }

    @Test
    void testSpawnSome() {
        final int entityNum = 10;
        List<GameObject> entities;
        entities = mf.spawnSome(entityNum, Bat::new).stream().map((x) -> (GameObject)x).collect(Collectors.toList());
        assertEquals(entities.size(), 10);
    }

    @Test
    void testSpawnRandom() {
        final int entityNum = 10;
        List<GameObject> entities;
        entities = mf.spawnRandom().stream().map((x) -> (GameObject)x).collect(Collectors.toList());
        assertTrue(entities.size() > 0);
        assertTrue(entities.size() <= mf.MAX_MOB_NUMBER);
    }


}
