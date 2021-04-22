package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.*;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquipmentTest {

    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);
    private final static Gun DEFAULT_GUN = new Gun(null, TILE_MAP, 0, 0, 21, 3, 1);
    private final static HealthModifier BULLET = (Projectile) DEFAULT_GUN.fire(0,0).get(0);

    @Test
    void testSimpleWeapon() {
        final Player p = new Player(0, 0, "Alice", TILE_MAP);
        final int healthOnCreation = p.getHealth();
        BULLET.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation - 21);
    }

    @Test
    void testHealthUnderflow() {
        final Player p = new Player(0, 0, "Bob", TILE_MAP);
        for (int i = 0; i < 5; i++) {
            BULLET.useOn(p);
        }
        assertEquals(p.getHealth(), 0);
    }

    @Test
    void testHealthOverflow() {
        final Player p = new Player(0, 0, "Charles", TILE_MAP);
        final HealthModifier h = new SimplePotion(10, "Potion10");
        h.useOn(p);
        assertEquals(p.getHealth(), 100);
    }

    @Test
    void testSimplePotionAfterWeapon() {
        final Player p = new Player(0, 0, "Dan", TILE_MAP);
        final int healthOnCreation = p.getHealth();
        final HealthModifier o = new SimplePotion(20, "Potion20");
        BULLET.useOn(p);
        o.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation - 21 + 20);
    }



    @Test
    void testGunUpdate() {
        // TODO check that the update produced the desired results
        DEFAULT_GUN.update(100, List.of());
    }

}
