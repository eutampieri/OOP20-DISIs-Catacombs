package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

public class Gun extends Weapon {

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param damage   Bullet strength
     * @param ps       Bullet speed
     * @param fireRate Weapon fire rate
     * @param team     The team, i.e. if it's not an enemy
     */
    public Gun(final Entity e, final TileMap tm, final int x, final int y, final int damage, final int ps,
            final int fireRate, final Team team) {
        super(e, tm, x, y, damage, ps, fireRate, team);
    }
}
