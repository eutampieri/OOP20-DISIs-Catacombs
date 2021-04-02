package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class AssetManagerTest {
    @Test
    void assetManagerTest() {
        final AssetManager am = new AssetManager();
        assertDoesNotThrow(am::load);
    }
}
