package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.Gun;
import eu.eutampieri.catacombs.model.HealthModifier;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.Projectile;
import eu.eutampieri.catacombs.model.SimplePotion;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquipmentTest {

     private static final TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);
     private static final Gun DEFAULT_GUN = new Gun(null, TILE_MAP, 3, 3, 21, 1, 1, GameObject.Team.FREIND);
     private static final HealthModifier BULLET = (Projectile) DEFAULT_GUN.fire(0, 0).get(0);

    @Test
    void testSimpleWeapon() {
        final Player p = new Player(0, 0, "Alice", TILE_MAP);
        final int healthOnCreation = p.getHealth();
        BULLET.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation + BULLET.getHealthDelta());
    }

    @Test
    void testHealthUnderflow() {
        final int numberOfShots = 5;
        final Player p = new Player(0, 0, "Bob", TILE_MAP);
        for (int i = 0; i < numberOfShots; i++) {
            BULLET.useOn(p);
        }
        assertEquals(p.getHealth(), 0);
    }

    @Test
    void testHealthOverflow() {
        final Player p = new Player(0, 0, "Charles", TILE_MAP);
        final HealthModifier h = new SimplePotion(10, "Potion10", 0, 0);
        h.useOn(p);
        assertEquals(p.getHealth(), 100);
    }

    @Test
    void testSimplePotionAfterWeapon() {
        final int potionPower = 20;
        final Player p = new Player(0, 0, "Dan", TILE_MAP);
        final int healthOnCreation = p.getHealth();
        final HealthModifier o = new SimplePotion(potionPower, "Potion20", 0, 0);
        BULLET.useOn(p);
        o.useOn(p);
        assertEquals(p.getHealth(), healthOnCreation + potionPower + BULLET.getHealthDelta());
    }

    @Test
    void testGunUpdate() {
        // TODO check that the update produced the desired results
        DEFAULT_GUN.update(100, List.of());
    }

}
