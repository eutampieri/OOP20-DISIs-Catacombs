<<<<<<< HEAD:src/test/java/CharactersTests.java
import eu.eutampieri.catacombs.model.map.Tile;
=======
package eu.eutampieri.catacombs.tests;

>>>>>>> 4a5497551d23bb96e4a9c17efbf4a0945cf92c1b:src/test/java/eu/eutampieri/catacombs/tests/CharactersTests.java
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CharactersTests {

<<<<<<< HEAD:src/test/java/CharactersTests.java
    private final TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);
    private final Bat BAT = new Bat(3, 3, TILE_MAP);
    private final Slime SLIME = new Slime(1, 1, TILE_MAP);
    private final HealthModifier ONE_HP_SUB = new Gun(1, "1xp", 1, 1, 1000);
=======
    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);
    private final static Bat BAT = new Bat(1, 1, TILE_MAP);
    private final static Slime SLIME = new Slime(1, 1, TILE_MAP);
    private final static HealthModifier ONE_HP_SUB = new Gun(1, "1xp", 1, 1, 1000);
>>>>>>> 4a5497551d23bb96e4a9c17efbf4a0945cf92c1b:src/test/java/eu/eutampieri/catacombs/tests/CharactersTests.java

    @Test
    void testPlayerName() {
        final String name = "John Appleseed";
        final Player p = new Player(0, 0, name);
        assertEquals(p.getName(), name);
    }

    @Test
    void testPlayerHealth() {
        final String name = "John Appleseed";
        final Player p = new Player(0, 0, name);
        assertEquals(p.getHealth(), 100);
        assertTrue(p.isAlive());
    }

    @Test
    void testEnemyNames() {
        assertEquals("Slime", SLIME.getName());
        assertEquals("Bat", BAT.getName());
    }

    @Test
    void testEnemyID() {
        assertEquals(ID.ENEMY, SLIME.getId());
        assertEquals(ID.ENEMY, SLIME.getId());
    }

    @Test
    void testBatGettersSetters() {
        final int initialHealth = BAT.getHealth();
        ONE_HP_SUB.useOn(BAT);
        assertEquals(initialHealth - 1, BAT.getHealth());
    }

    @Test
    void testEntitiesSizesPowersOfTwo() {
        // Bat
        assertEquals(0, BAT.getWidth() & (BAT.getWidth() - 1));
        assertEquals(0, BAT.getHeight() & (BAT.getHeight() - 1));
        // Slime
        assertEquals(0, SLIME.getWidth() & (SLIME.getWidth() - 1));
        assertEquals(0, SLIME.getHeight() & (SLIME.getHeight() - 1));

    }

    /*@Test
    void testBoxSize() {
        assertEquals(BAT.getHitBox().getHeight(), 32);
        assertEquals(BAT.getHitBox().getWidth(), 32);
        assertEquals(SLIME.getHitBox().getHeight(), 32);
        assertEquals(SLIME.getHitBox().getWidth(), 32);
    }*/

    @Test
    void testSlimeFollowBat() {
        final Bat bat = new Bat(3, 3, TILE_MAP);
        final int initialX = SLIME.getPosX();
        final int initialY = SLIME.getPosY();
        SLIME.follow(bat);
        SLIME.update(100);
        assertNotEquals(initialX, SLIME.getPosX());
        assertNotEquals(initialY, SLIME.getPosY());
    }

    @Test
    void testBatUpdate() {
        BAT.update(10);
        // TODO implement checks
    }
}
