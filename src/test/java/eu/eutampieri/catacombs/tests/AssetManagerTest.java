package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class AssetManagerTest {
    @Test
    void assetManagerTest() {
        final AssetManager am = AssetManager.getAssetManager();
        assertNull(am.getImage("ciaone"));
    }
}
