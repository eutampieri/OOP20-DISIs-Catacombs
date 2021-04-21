package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

import java.util.List;

public class Gun extends Weapon {


    /**
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param strength Bullet strength
     * @param ps       Bullet speed
     * @param fr       Weapon fire rate
     */
    public Gun(TileMap tm, int x, int y, int strength, int ps, int fr) {
        super(tm, x, y, strength, ps, fr);
    }
}
