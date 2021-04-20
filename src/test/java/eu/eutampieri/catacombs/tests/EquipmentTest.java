package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.Gun;
import eu.eutampieri.catacombs.model.HealthModifier;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.SimplePotion;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquipmentTest {

    private final static Gun DEFAULT_GUN = new Gun(0, 0, 21, "Default gun", 1, 10, 1);
    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);

    @Test
    void testSimpleWeapon() {
        final Player p = new Player(0, 0, "Alice", TILE_MAP);
        final int healthOnCreation = p.getHealth();
        DEFAULT_GUN.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation - 21);
    }

    @Test
    void testHealthUnderflow() {
        final Player p = new Player(0, 0, "Bob", TILE_MAP);
        for (int i = 0; i < 5; i++) {
            DEFAULT_GUN.useOn(p);
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
        DEFAULT_GUN.useOn(p);
        o.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation - 21 + 20);
    }

    @Test
    void testGunGettersAndSetters() {
        DEFAULT_GUN.setMagazine(64);
        assertEquals(64, DEFAULT_GUN.getMagazine());
        DEFAULT_GUN.setReloadSpeed(64);
        assertEquals(64, DEFAULT_GUN.getReloadSpeed());
        DEFAULT_GUN.setRange(64);
        assertEquals(64, DEFAULT_GUN.getRange());
        DEFAULT_GUN.setFireRate(64);
        assertEquals(64, DEFAULT_GUN.getFireRate());
        DEFAULT_GUN.setDamage(64);
        assertEquals(64, DEFAULT_GUN.getDamage());
    }

    @Test
    void testFiringDecreasesMagazineByOne() {
        final int initialMagazineContent = DEFAULT_GUN.getMagazine();
        DEFAULT_GUN.fireWeapon();
        assertEquals(initialMagazineContent - 1, DEFAULT_GUN.getMagazine());
    }

    @Test
    void testGunUpdate() {
        // TODO check that the update produced the desired results
        DEFAULT_GUN.update(100, List.of());
    }

    @Test
    void testGunRender() {
        // TODO check that the rendering produced the desired results
        //DEFAULT_GUN.render();
    }

}
