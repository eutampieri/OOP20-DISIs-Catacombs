package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

public class Gun extends Weapon {

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param strength Bullet strength
     * @param ps       Bullet speed
     * @param fr       Weapon fire rate
     */
    public Gun(final Entity e, final TileMap tm, final int x, final int y, final int strength, final int ps,
            final int fr) {
        super(e, tm, x, y, strength, ps, fr);
    }
}
