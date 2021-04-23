package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

public class Gun extends Weapon {
    private static final int STRENGTH = 7;
    private static final int FIRE_RATE = 90;
    private static final int PROJECTILE_SPEED = 15;

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param team     The team, i.e. if it's not an enemy
     */
    public Gun(final Entity e, final TileMap tm, final int x, final int y, final Team team) {
        super(e, tm, x, y, STRENGTH, PROJECTILE_SPEED, FIRE_RATE, team);
    }

    /**
     *
     * @return Gun strengths
     */
    public int getStrength() {
        return STRENGTH;
    }

    /**
     *
     * @return Gun fire rate as bullets/minute
     */
    public int getFireRate() {
        return FIRE_RATE;
    }

    /**
     *
     * @return Gun projectile speed
     */
    public int getProjectileSpeed() {
        return PROJECTILE_SPEED;
    }
}
