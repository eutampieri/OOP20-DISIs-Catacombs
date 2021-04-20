package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CameraTest {

    private final static GameConfiguration game = new GameConfiguration();
    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(100, 100);
    private final static Bat BAT = new Bat(15, 15, TILE_MAP);
    private final static Player PLAYER1 = new Player(65, 65, "Player1", TILE_MAP);

    @Test
    void testCameraPosition() {
        final Camera camera = new Camera(0, 0, TILE_MAP.width(), TILE_MAP.height());
        final int initialX = camera.getXOffset();
        final int initialY = camera.getYOffset();
        assertEquals(initialX, 0);
        assertEquals(initialY, 0);
        camera.centerOnEntity(BAT, game.getGameWidth(), game.getGameHeight());
        assertNotEquals(initialX, camera.getXOffset());
        assertNotEquals(initialY, camera.getYOffset());
        assertEquals(camera.getXOffset(), BAT.getPosX());
        assertEquals(camera.getYOffset(), BAT.getPosY());
        camera.centerOnEntity(PLAYER1, game.getGameWidth(), game.getGameHeight());
        assertNotEquals(initialX, camera.getXOffset());
        assertNotEquals(initialY, camera.getYOffset());
        assertEquals(camera.getXOffset(), PLAYER1.getPosX());
        assertEquals(camera.getYOffset(), PLAYER1.getPosY());
    }

    // TODO add more camera tests
}
