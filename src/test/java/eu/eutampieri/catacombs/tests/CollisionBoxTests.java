package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.CollisionBox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CollisionBoxTests {

    private static final CollisionBox MY_BOX = new CollisionBox(5, 10, 5, 5);

    @Test
    void testOverlapsWithSelf() {
        assertTrue(MY_BOX.overlaps(MY_BOX));
    }

    @Test
    void testDoesntOverlapWithNonOverlappingBox() {
        final CollisionBox nonOverlapping = new CollisionBox(MY_BOX);
        nonOverlapping.move(MY_BOX.getWidth() * 2, MY_BOX.getHeight() * 2);
        assertFalse(MY_BOX.overlaps(nonOverlapping));
    }

    @Test
    void testOverlapsWithOverlappingBox() {
        final CollisionBox overlapping = new CollisionBox(MY_BOX);
        overlapping.move(MY_BOX.getWidth() / 2, MY_BOX.getHeight() / 2);
        assertTrue(MY_BOX.overlaps(overlapping));
    }

    @Test
    void testDoesntOverlapWithTouchingBox() {
        final CollisionBox touching = new CollisionBox(MY_BOX);
        touching.move(MY_BOX.getWidth(), MY_BOX.getHeight());
        assertFalse(MY_BOX.overlaps(touching));
    }

    @Test
    void testGetterSetters() {
        final CollisionBox newBox = new CollisionBox(MY_BOX);
        newBox.setLocation(0, 0);
        assertEquals(0, newBox.getPosX());
        assertEquals(0, newBox.getPosY());
        newBox.setDimensions(0, 0);
        assertEquals(0, newBox.getWidth());
        assertEquals(0, newBox.getHeight());
    }
}
