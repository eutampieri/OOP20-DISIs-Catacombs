package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetManagerTest {
    @Test
    void testLoadFromToml() {
        AssetManager am = new AssetManager();
        try {
            am.load();
        } catch (IOException e) {
            Assertions.fail();
        }
    }
}
