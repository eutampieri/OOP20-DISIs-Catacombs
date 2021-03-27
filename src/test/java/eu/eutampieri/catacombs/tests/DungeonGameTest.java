package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DungeonGameTests {
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
        mygame.setTest();
        mygame.initialize(gc);
        mygame.start();
        mygame.run();
        // mygame.stop();
        mygame.getGameHeight();
        mygame.getGameWidth();
        mygame.getHeight();
        mygame.getWidth();
        mygame.getMainFrame();
        mygame.getGraphics();
    }
}
