package eu.eutampieri.catacombs.model;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class EquipmentTest {

	@Test
	void testSimpleWeapon() {
		Player p = new Player(0,0, ID.Player);
		final int healthOnCreation = p.getHealth();
		final HealthModifier h = new Gun(21);
		h.useOn(p);
		assertEquals(p.getHealth(), healthOnCreation - 21);
	}
	
	@Test
	void testHealthUnderflow() {
		Player p = new Player(0,0, ID.Player);
		final HealthModifier h = new Gun(21);
		for(int i = 0; i < 5; i++) {
			h.useOn(p);
		}
		assertEquals(p.getHealth(), 0);
	}
	
	@Test
	void testHealthOverflow() {
		Player p = new Player(0,0, ID.Player);
		final HealthModifier h = new SimplePotion(10, "Potion10");
		h.useOn(p);
		assertEquals(p.getHealth(), 100);
	}
	
	@Test
	void testSimplePotionAfterWeapon() {
		Player p = new Player(0,0, ID.Player);
		final int healthOnCreation = p.getHealth();
		final HealthModifier w = new Gun(21);
		final HealthModifier o = new SimplePotion(20, "Potion20");
		w.useOn(p);
		o.useOn(p);
		assertEquals(p.getHealth(), healthOnCreation - 21 + 20);
	}

}
