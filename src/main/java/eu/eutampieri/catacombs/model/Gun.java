package eu.eutampieri.catacombs.model;

public class Gun extends SimpleWeapon{

	private static final String WEAPON_NAME = "Gun";

	Gun(int damage, String name, int fireRate, int range, int magazine) {
		super(damage, name, fireRate, range, magazine);
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
