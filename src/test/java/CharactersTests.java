import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CharactersTests {

    private final TileMap TILE_MAP = new TileMapFactoryImpl().def();
    private final Bat BAT = new Bat(0, 0, TILE_MAP);
    private final HealthModifier ONE_HP_SUB = new Gun(1, "1xp", 1, 1, 1000);

    @Test
    void testPlayerName() {
        final String name = "John Appleseed";
        Player p = new Player(0, 0, name);
        assertEquals(p.getName(), name);
    }

    @Test
    void testPlayerHealth() {
        final String name = "John Appleseed";
        Player p = new Player(0, 0, name);
        assertEquals(p.getHealth(), 100);
        assertTrue(p.isAlive());
    }

    @Test
    void testBatGettersSetters() {
        int initialHealth = BAT.getHealth();
        ONE_HP_SUB.useOn(BAT);
        assertEquals(initialHealth - 1, BAT.getHealth());
    }

    @Test
    void testEntitiesSizesPowersOfTwo() {
        // Bat
        assertEquals(0, BAT.getWidth() & (BAT.getWidth() - 1));
        assertEquals(0, BAT.getHeight() & (BAT.getHeight() - 1));
    }
}
