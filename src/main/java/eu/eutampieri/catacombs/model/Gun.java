package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

import java.util.List;

public class Gun extends Weapon {

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param damage   Bullet strength
     * @param ps       Bullet speed
     * @param fireRate Weapon fire rate
     */
    public Gun(final Entity e, TileMap tm, final int x, final int y, final int damage, final int ps, final int fireRate) {
        super(e, tm, x, y, damage, ps, fireRate);
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        // TODO Auto-generated method stub
    }

}
