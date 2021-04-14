package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CameraTest {

    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(100, 100);
    private final static Bat BAT = new Bat(15, 15, TILE_MAP);
    private final static Player PLAYER1 = new Player(65, 65, "Player1");

    @Test
    void testCameraPosition() {
        final Camera CAMERA = new Camera(0, 0, TILE_MAP.width(), TILE_MAP.height());
        final int initialX = CAMERA.getXOffset();
        final int initialY = CAMERA.getYOffset();
        assertEquals(initialX, 0);
        assertEquals(initialY, 0);
        CAMERA.centerOnEntity(BAT);
        assertEquals(initialX, CAMERA.getXOffset());
        assertEquals(initialY, CAMERA.getYOffset());
        CAMERA.centerOnEntity(PLAYER1);
        assertNotEquals(initialX, CAMERA.getXOffset());
        assertNotEquals(initialY, CAMERA.getYOffset());
    }

    // TODO add more camera tests
}
