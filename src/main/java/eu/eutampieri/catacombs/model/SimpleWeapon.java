package eu.eutampieri.catacombs.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A simple weapon that subtracts a given value from a player's health.
 */
public abstract class SimpleWeapon extends GameObject implements HealthModifier {
    /**
     * Weapon damage.
     */
    protected int damage;
    /**
     * Weapon rate of fire.
     */
    protected int fireRate;
    /**
     * Timer between each shot.
     */
    // TODO remove suppress when implemented game loop
    @SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
    protected int fireDelay;
    /**
     * Weapon range as distance a bullet travels.
     */
    protected int range;
    /**
     * Weapon reload speed as time taken to reload.
     */
    protected int reloadSpeed;
    /**
     * Reload timer - keeps track of time reloading.
     */
    // TODO remove suppress when implemented game loop
    @SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
    protected int reloadDelay;
    /**
     * Weapon magazine.
     */
    protected int magazine;
    /**
     * Weapon name.
     */
    protected final String name;

    /**
     * Simple weapon constructor.
     * @param damage The amount of damage (i.e. the change in health) that a character
     * will have after being hit with this weapon
     * @param name The weapon's name
     * @param fireRate weapon's rate of fire
     * @param range weapon's range
     * @param magazine weapon's number of bullets (if -1 infinite)
     */
    SimpleWeapon(final int damage, final String name, final int fireRate, final int range, final int magazine) {
        super(0, 0, ID.WEAPON);
        this.damage = damage;
        this.name = name;
        this.fireRate = fireRate;
        this.range = range;
        this.magazine = magazine;
    }

    /**
     * @return Damage as hp delta
     */
    @Override
    public int getHealthDelta() {
        return damage * -1;
    }

    /**
     * @return Weapon name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Updates status of weapon in game loop.
     * @param delta time between updates
     */
    @Override
    public void update(final int delta) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
    }

    /**
     * Getter for weapon damage.
     * @return Weapon damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Setter for weapon damage.
     * @param damage Weapon damage to be set
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * Getter for weapon fire rate.
     * @return Weapon fire rate
     */
    public int getFireRate() {
        return fireRate;
    }

    /**
     * Setter for weapon fire rate.
     * @param fireRate Weapon fire rate to be set
     */
    public void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    /**
     * Getter for weapon range.
     * @return Weapon range as an integer specifying the distance a bullet can travel
     */
    public int getRange() {
        return range;
    }

    /**
     * Setter for weapon range.
     * @param range Weapon range to be set
     */
    public void setRange(final int range) {
        this.range = range;
    }

    /**
     * Getter for weapon reload speed.
     * @return Weapon reload speed as the time taken to reload the weapon
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Setter for weapon reload speed.
     * @param reloadSpeed Weapon reload speed to be set
     */
    public void setReloadSpeed(final int reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    /**
     * Getter for weapon magazine size.
     * @return Weapon magazine size as number of bullets the weapon can shoot before reloading
     */
    public int getMagazine() {
        return magazine;
    }

    /**
     * Setter for weapon magazine.
     * @param magazine Weapone magazine to be set
     */
    public void setMagazine(final int magazine) {
        this.magazine = magazine;
    }

    /**
     * Makes weapon fire on key press.
     */
    public void fireWeapon() {
        this.magazine--;
        // TODO
    }

    /**
     * @return true if magazine > 0 and fireDelay timer passed
     */
    public boolean canFire() {
        return false;
        // TODO 
    }

    /**
     * Reloads weapon on key press.
     */
    public void reloadWeapon() {
        // TODO
    }
}
