package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.ui.input.MouseManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HIDTests {
    private static final MouseManager MOUSE_MANAGER = new MouseManager();
    private static final Component EVENT_SOURCE = new Component() {
    } ;

    @Test
    void testMouseMovement() {
        final MouseEvent me = new MouseEvent(EVENT_SOURCE, 0, 0, 0, 100, 100, 1, false);
        MOUSE_MANAGER.mouseMoved(me);
        assertEquals(100, MOUSE_MANAGER.getX());
        assertEquals(100, MOUSE_MANAGER.getY());
    }

    @Test
    void testMouseLeftClick() {
        final MouseEvent press = new MouseEvent(EVENT_SOURCE, MouseEvent.MOUSE_PRESSED, 0, InputEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false, MouseEvent.BUTTON1);
        MOUSE_MANAGER.mousePressed(press);
        assertTrue(MOUSE_MANAGER.isLeftPressed());
        assertFalse(MOUSE_MANAGER.isRightPressed());
        final MouseEvent release = new MouseEvent(EVENT_SOURCE, MouseEvent.MOUSE_RELEASED, 0, InputEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false, MouseEvent.BUTTON1);
        MOUSE_MANAGER.mouseReleased(release);
        assertFalse(MOUSE_MANAGER.isLeftPressed());
    }

    @Test
    void testMouseRightClick() {
        final MouseEvent press = new MouseEvent(EVENT_SOURCE, MouseEvent.MOUSE_PRESSED, 0, InputEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false, MouseEvent.BUTTON3);
        MOUSE_MANAGER.mousePressed(press);
        assertFalse(MOUSE_MANAGER.isLeftPressed());
        assertTrue(MOUSE_MANAGER.isRightPressed());
        final MouseEvent release = new MouseEvent(EVENT_SOURCE, MouseEvent.MOUSE_RELEASED, 0, InputEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false, MouseEvent.BUTTON3);
        MOUSE_MANAGER.mouseReleased(release);
        assertFalse(MOUSE_MANAGER.isRightPressed());

    }
}
