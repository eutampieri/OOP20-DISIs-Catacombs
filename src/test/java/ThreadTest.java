import eu.eutampieri.catacombs.ui.DungeonGame;
import eu.eutampieri.catacombs.ui.Game;
import eu.eutampieri.catacombs.ui.GameConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ThreadTest {
    @Test
    public void StopThreadTest() {
       final DungeonGame game = new DungeonGame();
       final GameConfiguration gc = new GameConfiguration();
       gc.setGameWidth(1);
       gc.setGameHeight(1);
       game.initialize(gc);
       game.getMainFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       game.start();
       assertDoesNotThrow(() -> game.stop());
    }
}
