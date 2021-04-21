package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CharactersTests {

    private final static TileMap TILE_MAP = new TileMapFactoryImpl().empty(20, 20);
    private final static Bat BAT = new Bat(1, 1, TILE_MAP);
    private final static Slime SLIME = new Slime(
            AssetManagerProxy.getMapTileSize() * 2,
            AssetManagerProxy.getMapTileSize() * 2,
            TILE_MAP
    );
    private final static Boss BOSS = new Boss(5, 5, TILE_MAP);
    private final static Weapon GUN = new Gun(TILE_MAP, 0, 0, 1, 1, 1);
    private final static HealthModifier ONE_HP_SUB = (Projectile)GUN.fire(0,0).get(0);
    private final static Player PLAYER = new Player(0, 0, "John Appleseed", TILE_MAP);

    @Test
    void testPlayerName() {
        final String name = "John Appleseed";
        assertEquals(PLAYER.getName(), name);
    }

    @Test
    void testPlayerHealth() {
        assertEquals(PLAYER.getHealth(), 100);
        assertTrue(PLAYER.isAlive());
    }

    @Test
    void testEnemyNames() {
        assertEquals("Slime", SLIME.getName());
        assertEquals("Bat", BAT.getName());
        assertEquals("Boss", BOSS.getName());
    }

    @Test
    void testEnemyEntityKind() {
        assertEquals(GameObjectType.ENEMY, SLIME.getKind());
        assertEquals(GameObjectType.ENEMY, SLIME.getKind());
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

    @Test
    void testBoxSize() {
        assertEquals(BAT.getHitBox().getHeight(), BAT.getHeight());
        assertEquals(BAT.getHitBox().getWidth(), BAT.getWidth());
        assertEquals(SLIME.getHitBox().getHeight(), SLIME.getHeight());
        assertEquals(SLIME.getHitBox().getWidth(), SLIME.getWidth());
        assertEquals(BOSS.getHitBox().getHeight(), BOSS.getHeight());
        assertEquals(BOSS.getHitBox().getWidth(), BOSS.getWidth());
    }

    @Test
    void testSlimeFollowBat() {
        final Bat bat = new Bat(3, 3, TILE_MAP);
        final int initialX = SLIME.getPosX();
        final int initialY = SLIME.getPosY();
        SLIME.setCharacterToFollow(bat);
        assertEquals(SLIME.getCharacterToFollow(), bat);
        SLIME.update(100, List.of());
        assertNotEquals(initialX, SLIME.getPosX());
        assertNotEquals(initialY, SLIME.getPosY());
    }

    @Test
    void testBatUpdate() {
        BAT.update(10, List.of(PLAYER));
        // TODO implement checks
    }

    @Test
    void testBossUpdate() {
        BOSS.update(10, List.of());
        // TODO implement checks
    }
}
