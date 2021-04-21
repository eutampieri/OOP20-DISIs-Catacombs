package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

import java.util.List;

public abstract class Weapon extends GameObject{

    /**
     * Projectile strength.
     */
    protected int strength;
    /**
     * projectile speed.
     */
    protected int ps;
    /**
     * weapon fire rate.
     */
    protected int fr;
    /**
     * Stores if the weapon can fire.
     */
    protected boolean canFire;
    /**
     * utility to count delay between shots.
     */
    protected int fireDelay, fireDelayCount;

    /**
     * Map in which resides the gun.
     */
    TileMap tileMap;

    /**
     *
     * @param tm Tile map
     * @param x X position
     * @param y Y position
     * @param strength Bullet strength
     * @param ps Bullet speed
     * @param fr Weapon fire rate
     */
    public Weapon(final TileMap tm, final int x, final int y, final int strength, final int ps, final int fr) {
        super(x, y, GameObjectType.WEAPON, new CollisionBox(x, y, 0, 0));
        setTileMap(tm);
        setStrength(strength);
        setProjectileSpeed(ps);
        setFireRate(fr);
        setFireDelay(fr * 1_000_000_000);
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        if (!canFire) {
            fireDelayCount += delta;
            if (fireDelayCount >= fireDelay) {
                fireDelayCount = 0;
                canFire = true;
            }
        }
    }

    public void setTileMap(final TileMap tm){
        this.tileMap = tm;
    }

    public void setStrength(final int str) {
        this.strength = str;
    }

    public void setProjectileSpeed(final int ps) {
        this.ps = ps;
    }

    public void setFireRate(final int fr) {
        this.fr = fr;
    }

    public void setFireDelay(final int fd) {
        this.fireDelay = fd;
    }

    public void setCanFire(final boolean cf){
        this.canFire = cf;
    }

    public boolean getCanFire(){
        return this.canFire;
    }

    public List<GameObject> fire(final int psx, final int psy){
        Projectile p = new Projectile(this.getHitBox().getPosX(), this.getHitBox().getPosY(),
                psx, psy, strength, tileMap);
        return List.of(p);
    }
}
