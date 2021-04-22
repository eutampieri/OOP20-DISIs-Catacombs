package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.Bat;
import eu.eutampieri.catacombs.model.Camera;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CameraTest {

     private static final GameConfiguration GAME = new GameConfiguration();
     private static final TileMap TILE_MAP = new TileMapFactoryImpl().empty(100, 100);
     private static final Bat BAT = new Bat(15, 15, TILE_MAP);
     private static final Player PLAYER1 = new Player(65, 65, "Player1", TILE_MAP);

    @Test
    void testCameraPosition() {
        final Camera camera = new Camera(0, 0, TILE_MAP.width(), TILE_MAP.height());
        final int initialX = camera.getXOffset();
        final int initialY = camera.getYOffset();
        assertEquals(initialX, 0);
        assertEquals(initialY, 0);
        camera.centerOnEntity(BAT, GAME.getGameWidth(), GAME.getGameHeight());
        assertNotEquals(initialX, camera.getXOffset());
        assertNotEquals(initialY, camera.getYOffset());
        assertEquals(camera.getXOffset(), BAT.getPosX());
        assertEquals(camera.getYOffset(), BAT.getPosY());
        camera.centerOnEntity(PLAYER1, GAME.getGameWidth(), GAME.getGameHeight());
        assertNotEquals(initialX, camera.getXOffset());
        assertNotEquals(initialY, camera.getYOffset());
        assertEquals(camera.getXOffset(), PLAYER1.getPosX());
        assertEquals(camera.getYOffset(), PLAYER1.getPosY());
    }

    // TODO add more camera tests
}
