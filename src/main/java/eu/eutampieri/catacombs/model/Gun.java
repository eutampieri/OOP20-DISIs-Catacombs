package eu.eutampieri.catacombs.model;

import java.util.List;

public class Gun extends SimpleWeapon {
    /**
     * @param damage   Weapon damage
     * @param name     Weapon name
     * @param fireRate Weapon rate of fire
     * @param range    Weapon range as the distance a bullet travels
     * @param magazine Weapon magazine as the number of bullets contained and usable
     *                 before firing
     */
    public Gun(final int damage, final String name, final int fireRate, final int range, final int magazine) {
        super(damage, name, fireRate, range, magazine);
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        // TODO Auto-generated method stub
    }

}
