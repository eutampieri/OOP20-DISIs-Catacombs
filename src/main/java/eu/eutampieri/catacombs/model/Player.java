package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * This class represents a player.
 */
public final class Player extends Entity {
    private static final int BASE_MOVEMENT_SPEED = 4;
    private static final int MAX_BASE_HP = 100;
    private static final int SIZE = 28;

    private int health;
    private final String name;
    private boolean fire;
    private Weapon weapon;

    public Player(final int x, final int y, final String name, final TileMap tm) {
        super(x, y, SIZE, SIZE, tm, GameObjectType.PLAYER, GameObject.Team.FRIEND);
        setSpeed(BASE_MOVEMENT_SPEED);
        this.setHealth(MAX_BASE_HP);
        this.name = name;
        this.face = Direction.RIGHT;
        this.weapon = new Gun(this, tm, x, y, this.getTeam());
    }

    /**
     * @return Player current health
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * @param health the new health value
     */
    @Override
    public void setHealth(final int health) {
        this.health = health;
        if (this.health > 100) {
            this.health = 100;
        } else if (this.health < 0) {
            this.health = 0;
        }

    }

    @Override
    public Pair<Action, Direction> getActionWithDirection() {
        return Pair.of(Action.MOVE, this.face);
    }

    @Override
    public boolean canPerform(final Action action) {
        switch (action) {
        case ATTACK:
        case MOVE:
        case DIE:
            return true;
        default:
            return false;
        }
    }

    /**
     * Request movement for this specific player.
     * @param d The direction of the movement
     */
    public void move(final Direction d) {
        this.resetMovement();
        switch (d) {
        case UP:
            this.up = true;
            break;
        case DOWN:
            this.down = true;
            break;
        case LEFT:
            this.left = true;
            break;
        case RIGHT:
            this.right = true;
            break;
        default:
            break;
        }
    }

    /**
     * Ask this player to stop moving.
     */
    public void stop() {
        this.resetMovement();
    }

    /**
     * @return Player name
     */
    public String getName() {
        return name;
    }

    /**
     * Ask this player to shoot.
     */
    public void fire() {
        this.fire = true;
    }

    private List<GameObject> spawnObject() {
        if (this.fire) {
            this.fire = false;
            switch (this.face) {
            case DOWN:
                return this.weapon.fire(0, 1);
            case RIGHT:
                return this.weapon.fire(1, 0);
            case LEFT:
                return this.weapon.fire(-1, 0);
            case UP:
                return this.weapon.fire(0, -1);
            default:
                return List.of();
            }
        } else {
            return List.of();
        }
    }

    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        super.update(delta, others);
        this.weapon.update(delta, others);

        others.parallelStream().filter((x) -> x instanceof Weapon)
                .filter((x) -> x.getHitBox().overlaps(this.getHitBox())).map((x) -> (Weapon) (x)).findAny()
                .ifPresent((x) -> {
                    x.setUser(this);
                    x.setPickedUp();
                    this.weapon = x;
                });

        others.parallelStream().filter((x) -> x instanceof HealthModifier && !(x instanceof Projectile))
                .filter((x) -> x.getHitBox().overlaps(this.getHitBox())).map((x) -> (HealthModifier) (x))
                .forEach((x) -> x.useOn(this));
        return this.spawnObject();
    }

    /**
     * Getter for player's weapon.
     * @return the currently used weapon
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Weapon setter.
     * @param weapon the new weapon, which will replace the previous one.
     */
    public void setWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }
}
