package eu.eutampieri.catacombs.model;

/**
 * 
 * A simple weapon that substracts a given value from a player's health
 *
 */
public abstract class SimpleWeapon extends GameObject implements HealthModifier {
	private int damage;
	private int fireRate;
	private int fireDelay; // timer between shots
	private int range;
	private int reloadSpeed; // time taken to reload
	private int magazine; // number of bullets 
	private final String name; 
	
	/**
	 * 
	 * @param damage The amount of damage (i.e. the change in health) that a character
	 * will have after being hit with this weapon
	 * @param name The weapon's name
	 */
	SimpleWeapon(int damage, String name) {
		super(0,0,ID.Weapon);
		this.damage = damage;
		this.name = name;
	}

	@Override
	public int getHealthDelta() {
		return damage * -1;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public int getFireDelay() {
		return fireDelay;
	}

	public void setFireDelay(int fireDelay) {
		this.fireDelay = fireDelay;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getReloadSpeed() {
		return reloadSpeed;
	}

	public void setReloadSpeed(int reloadSpeed) {
		this.reloadSpeed = reloadSpeed;
	}

	public int getMagazine() {
		return magazine;
	}

	public void setMagazine(int magazine) {
		this.magazine = magazine;
	}

	/**
	 * Makes weapon fire on key press
	 */
	public void fireWeapon() {
		// TODO 
		
	}
	
	/**
	 * 
	 * @return true if magazine > 0 and fireDelay timer passed
	 */
	public boolean canFire() {
		return false;
		// TODO 
	}
	
	/**
	 * Reloads weapon on key press
	 */
	public void reloadWeapon() {
		// TODO 
		
	}
	
	

}
