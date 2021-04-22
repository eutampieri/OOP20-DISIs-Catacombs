package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.game.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTests {
    @Test
    void testGameLaunch() {
        final String[] params = new String[0];
        try {
            Main.main(params);
        } catch (java.awt.HeadlessException e) {
            e.printStackTrace();
        }
    }
}
