package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.GameSheet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSheetTest {

    @Test
    public void testGameSheets() {
        final GameSheet gs = new GameSheet("res/playersheet.png");
        assertTrue(gs.getImage().isPresent());
    }
}
