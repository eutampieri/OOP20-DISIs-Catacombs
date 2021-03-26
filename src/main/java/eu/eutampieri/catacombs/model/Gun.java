package eu.eutampieri.catacombs.model;

public class Gun extends SimpleWeapon {

    private static final String WEAPON_NAME = "Gun";

    public Gun(final int damage, final  String name, final  int fireRate, final  int range, final int magazine) {
        super(damage, name, fireRate, range, magazine);
    }

    @Override
    public void update(final int delta) {

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

}
