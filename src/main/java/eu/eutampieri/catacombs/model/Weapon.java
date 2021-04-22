package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    protected long fireRate;
    /**
     * Stores if the weapon can fire.
     */
    protected boolean canFire;
    /**
     * utility to count delay between shots.
     */
    protected long fireDelay, fireDelayCount;

    /**
     * Map in which resides the gun.
     */
    private TileMap tileMap;

    /**
     * Entity using the weapon.
     */
    private Entity user;

    /**
     *
     * @param e Entity using weapon
     * @param tm Tile map
     * @param x X position
     * @param y Y position
     * @param strength Bullet strength
     * @param ps Bullet speed
     * @param fr Weapon fire rate
     */
    public Weapon(final Entity e, final TileMap tm, final int x, final int y, final int strength, final int ps, final int fr, final Team team) {
        super(x, y, GameObjectType.WEAPON, new CollisionBox(x, y, 0, 0), team);
        this.user = e;
        setTileMap(tm);
        setStrength(strength);
        setProjectileSpeed(ps);
        setFireRate(fr);
        setFireDelay(fireRate * TimeUnit.SECONDS.toNanos(1));
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
        if (this.user != null) {
            this.hitBox.setPosX(this.user.getHitBox().getPosX() + this.user.getSize() + 1);
            this.hitBox.setPosY(this.user.getHitBox().getPosY() + this.user.getSize() + 1);
        }
    }

    public final void setTileMap(final TileMap tm) {
        this.tileMap = tm;
    }

    public final void setStrength(final int str) {
        this.strength = str;
    }

    public final void setProjectileSpeed(final int ps) {
        this.ps = ps;
    }

    public final void setFireRate(final long fr) {
        this.fireRate = fr;

    }

    public final void setFireDelay(final int fd) {
        this.fireDelay = fd;
    }

    public final void setCanFire(final boolean cf) {
        this.canFire = cf;
    }

    public final boolean canFire() {
        return this.canFire;
    }

    public final List<GameObject> fire(final int psx, final int psy) {
       final Projectile p = new Projectile(this.getHitBox().getPosX(), this.getHitBox().getPosY(),
                psx, psy, strength, tileMap, this.getTeam());
        return List.of(p);
    }

    public void setUser(final Entity user) {
        this.user = user;
    }
}
