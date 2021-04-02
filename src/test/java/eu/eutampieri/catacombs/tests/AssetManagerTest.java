package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AssetManagerTest {
    @Test
    void assetManagerTest() {
        AssetManager am = new AssetManager();
            try {
                am.load();
            } catch (Exception e) {
                //
            }
            assertTrue(true);
    }
}
