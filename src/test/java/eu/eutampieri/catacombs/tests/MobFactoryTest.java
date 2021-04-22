package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.model.mobgen.MobFactoryImpl;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MobFactoryTest {
    private static final int SPAWN_X_TILE = 5;
    private static final int SPAWN_Y_TILE = 5;
    private static final TileMap TILE_MAP = new TileMapFactoryImpl().empty(200, 200);
    private static final MobFactoryImpl MF = new MobFactoryImpl(TILE_MAP);

    @Test
    void testSpawnAt() {
        List<GameObject> entities;
        entities = MF.spawnAt(SPAWN_X_TILE, SPAWN_Y_TILE, Bat::new).stream().map((x) -> (GameObject) x).collect(Collectors.toList());
        assertEquals(entities.get(0).getPosX(), SPAWN_X_TILE * AssetManagerProxy.getMapTileSize());
        assertEquals(entities.get(0).getPosY(), SPAWN_Y_TILE * AssetManagerProxy.getMapTileSize());
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
