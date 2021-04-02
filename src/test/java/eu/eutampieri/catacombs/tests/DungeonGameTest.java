package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DungeonGameTest {
    @Test
    void testInitialize() {
        final DungeonGame mygame = new DungeonGame();
        final GameConfiguration gc = new GameConfiguration();
        gc.setFps(60);
        gc.setFullScreen(false);
        gc.setGameHeight(600);
        gc.setGameWidth(800);
        gc.setResizeable(true);
        gc.setScaling(true);
        gc.setTitle("Game");
        assertEquals(gc.getFps(),60);
        mygame.setTest();
        /*try {
            mygame.initialize(gc);
            mygame.start();
            mygame.run();
            mygame.stop();
            mygame.getGameHeight();
            mygame.getGameWidth();
            mygame.getHeight();
            mygame.getWidth();
            mygame.getMainFrame();
            mygame.getGraphics();
        } catch (Exception e) {
            assertTrue(true);
        }*/
    }
}
