package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DungeonGameTest {
    private static final int GAME_SIZE = 700;
    private static final int FPS = 60;
    @Test
    void testInitialize() {
        final GameConfiguration gc = new GameConfiguration();
        gc.setFps(FPS);
        gc.setFullScreen(false);
        gc.setGameHeight(GAME_SIZE);
        gc.setGameWidth(GAME_SIZE);
        gc.setResizeable(true);
        gc.setScaling(true);
        gc.setTitle("Game");
        assertEquals(gc.getFps(), FPS);
    }
}
