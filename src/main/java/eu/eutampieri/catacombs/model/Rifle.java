package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

public class Rifle extends Weapon {
    private static final int STRENGTH = 3;
    private static final int FIRE_RATE = 210;
    private static final int PROJECTILE_SPEED = 14;

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param team     The team, i.e. if it's not an enemy
     */
    public Rifle(final Entity e, final TileMap tm, final int x, final int y, final Team team) {
        super(e, tm, x, y, STRENGTH, PROJECTILE_SPEED, FIRE_RATE, team);
    }
}
