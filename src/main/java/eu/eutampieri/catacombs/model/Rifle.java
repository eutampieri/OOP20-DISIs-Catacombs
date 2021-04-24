package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;

public class Rifle extends Weapon {
    private static final int STRENGTH = 3;
    private static final int FIRE_RATE = 210;
    private static final int PROJECTILE_SPEED = 14;
    private static final int BOX_WIDTH = (int) (45 * AssetManagerProxy.getWeaponScalingFactor());
    private static final int BOX_HEIGHT = 17;

    /**
     * @param e        Entity using gun
     * @param tm       Tile map
     * @param x        X position
     * @param y        Y position
     * @param team     The team, i.e. if it's not an enemy
     */
    public Rifle(final Entity e, final TileMap tm, final int x, final int y, final Team team) {
        super(e, tm, x, y, STRENGTH, PROJECTILE_SPEED, FIRE_RATE, BOX_WIDTH, BOX_HEIGHT, team);
    }
}
