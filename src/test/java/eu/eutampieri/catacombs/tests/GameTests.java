package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.game.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTests {
    @Test
    void testGameLaunch() {
        String[] params = new String[0];
        try {
            Main.main(params);
        } catch (Exception e) {
            fail();
        }
        assertTrue(true);
    }
}
