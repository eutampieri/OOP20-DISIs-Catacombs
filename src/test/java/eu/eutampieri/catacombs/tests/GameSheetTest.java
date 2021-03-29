package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.GameSheets;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSheetTest {

    @Test
    public void testGameSheets() {
        final GameSheets gs = new GameSheets(Path.of("res/playersheet.png"));
        assertTrue(gs.getImage().isPresent());
    }
}
