package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.input.KeyManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.Component;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HIDTests {
    private static final Component EVENT_SOURCE = new Component() {
    };

    @Test
    void testKeyPressed() {
        final KeyEvent k = new KeyEvent(EVENT_SOURCE, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_W, 'w');
        KeyManager.getKeyManager().keyPressed(k);
        assertTrue(KeyManager.getKeyManager().isKeyPressed(k.getKeyCode()));
        KeyManager.getKeyManager().keyReleased(k);
        assertFalse(KeyManager.getKeyManager().isKeyPressed(k.getKeyCode()));
    }

}
