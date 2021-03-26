package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Test;

import javax.swing.WindowConstants;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ThreadTest {
    /*@Test
    public void testStopThread() {
       final DungeonGame game = new DungeonGame();
       final GameConfiguration gc = new GameConfiguration();
       gc.setGameWidth(1);
       gc.setGameHeight(1);
       game.initialize(gc);
       game.getMainFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       game.start();
       assertDoesNotThrow(() -> game.stop());
    }*/// TODO Fix this test
}
