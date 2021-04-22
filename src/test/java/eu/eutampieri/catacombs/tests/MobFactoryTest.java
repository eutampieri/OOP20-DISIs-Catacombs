package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.model.mobgen.MobFactoryImpl;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MobFactoryTest {
    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(200, 200);
    private final static MobFactoryImpl MF = new MobFactoryImpl(TILE_MAP);

    @Test
    void testSpawnAt() {
        List<GameObject> entities;
        entities = MF.spawnAt(5, 5, Bat::new).stream().map((x) -> (GameObject) x).collect(Collectors.toList());
        assertEquals(entities.get(0).getPosX(), 5 * AssetManagerProxy.getMapTileSize());
        assertEquals(entities.get(0).getPosY(), 5 * AssetManagerProxy.getMapTileSize());
    }

    @Test
    void testSpawnSome() {
        final int entityNum = 10;
        List<GameObject> entities;
        entities = MF.spawnSome(entityNum, Bat::new).stream().map((x) -> (GameObject) x).collect(Collectors.toList());
        assertEquals(entities.size(), 10);
    }

    @Test
    void testSpawnRandom() {
        List<GameObject> entities;
        entities = MF.spawnRandom().stream().map((x) -> (GameObject) x).collect(Collectors.toList());
        assertFalse(entities.isEmpty());
        assertTrue(entities.size() <= MF.MAX_MOB_NUMBER);
    }

}
