import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquipmentTest {

	private final static Gun DEFAULT_GUN = new Gun(21, "Default gun", 1, 10, 1);

	@Test
	void testSimpleWeapon() {
		Player p = new Player(0,0, "Alice");
		final int healthOnCreation = p.getHealth();
		final HealthModifier h = DEFAULT_GUN;
		h.useOn(p);
		assertEquals(p.getHealth(), healthOnCreation - 21);
	}
	
	@Test
	void testHealthUnderflow() {
		Player p = new Player(0,0, "Bob");
		final HealthModifier h = DEFAULT_GUN;
		for(int i = 0; i < 5; i++) {
			h.useOn(p);
		}
		assertEquals(p.getHealth(), 0);
	}
	
	@Test
	void testHealthOverflow() {
		Player p = new Player(0,0, "Charles");
		final HealthModifier h = new SimplePotion(10, "Potion10");
		h.useOn(p);
		assertEquals(p.getHealth(), 100);
	}
	
	@Test
	void testSimplePotionAfterWeapon() {
		Player p = new Player(0,0, "Dan");
		final int healthOnCreation = p.getHealth();
		final HealthModifier w = DEFAULT_GUN;
		final HealthModifier o = new SimplePotion(20, "Potion20");
		w.useOn(p);
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
		int initialMagazineContent = DEFAULT_GUN.getMagazine();
		DEFAULT_GUN.fireWeapon();
		assertEquals(initialMagazineContent - 1, DEFAULT_GUN.getMagazine());
	}

	@Test
	void testGunUpdate() {
		// TODO check that the update produced the desired results
		DEFAULT_GUN.update(100);
	}

	@Test
	void testGunRender() {
		// TODO check that the rendering produced the desired results
		DEFAULT_GUN.render();
	}

}
