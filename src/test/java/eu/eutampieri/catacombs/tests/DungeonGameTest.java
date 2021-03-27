package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DungeonGameTests {
    @Test
    void testInitialize() {
        DungeonGame mygame = new DungeonGame();
        GameConfiguration gc = new GameConfiguration();
        gc.setFps(60);
        gc.setFullScreen(false);
        gc.setGameHeight(600);
        gc.setGameWidth(800);
        gc.setResizeable(true);
        gc.setScaling(true);
        gc.setTitle("Game");
        mygame.initialize(gc);
        mygame.run();
        mygame.start();
        // mygame.stop();
        mygame.getGameHeight();
        mygame.getGameWidth();
        mygame.getHeight();
        mygame.getWidth();
        mygame.getMainFrame();
        mygame.getGraphics();
    }
}
