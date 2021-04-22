package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.List;

/**
 * Bat class - the bat is an enemy that mostly stands still and fires bullets.
 */
public final class Bat extends Entity {

    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int SIZE = 16;
    private static final int MOVEMENT_SPEED = 1;
    private static final int HEALTH = 8;
    private static final int CB_POS_MOD = 4;
    private static final int CB_DIM_MOD = 9;
    private static final int BASE_DAMAGE = 2;
    private static final int BASE_FIRE_RATE = 2;
    private static final int BASE_PROJECTILE_SPEED = 2;
    private static final String NAME = "Bat";
    private static final long MOVE_DELAY = 1_000_000_000;
    private static final long PAUSE_DELAY = 7L * 1_000_000_000;

    private final Weapon weapon;
    private boolean isMoving;
    private long delayCounter;
    private long pauseCounter;
    private final CollisionBox radarBox;
    private final Point shootingDirection;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Bat is spawned
     */
    public Bat(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.ENEMY);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - width * CB_POS_MOD, posY - width * CB_POS_MOD, width * CB_DIM_MOD,
                height * CB_DIM_MOD);
        weapon = new Weapon(this, tileMap, this.getHitBox().getPosX(), this.getHitBox().getPosY(),
                BASE_DAMAGE, BASE_PROJECTILE_SPEED, BASE_FIRE_RATE){};
        shootingDirection = new Point(0, 0);

    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        if (isMoving) {
            delayCounter += delta;
            if (delayCounter >= MOVE_DELAY) {
                delayCounter = 0;
                isMoving = false;
                resetMovement();
            }
        } else {
            pauseCounter += delta;
            if (pauseCounter >= PAUSE_DELAY) {
                pauseCounter = 0;
                isMoving = true;
                changeDirection();
            }
        }
        if (others.stream().filter((x) -> x instanceof Player)
                .findFirst()
                .get()
                .getHitBox()
                .overlaps(this.getHitBox()) && this.weapon.canFire()){
            setShootingDirection(others.stream().filter((x) -> x instanceof Player).findFirst().get());
            spawnObject();
        } else {
            resetShootingDirection();
        }
        super.update(delta, others);
        updateRadarBoxLocation();
        weapon.update(delta, others);
    }

    @Override
    public Pair<Action, Direction> getActionWithDirection() {
        // TODO Auto-generated method stub

        return Pair.of(Action.MOVE, this.face);
    }

    @Override
    public boolean canPerform(final Action action) {
        switch (action) {
            case ATTACK:
            case MOVE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getHealth() {
        return this.hp;
    }

    @Override
    public void setHealth(final int health) {
        this.hp = health;
    }

    /**
     * Utility class that makes the bat change movement direction. As of now bats
     * can only go left or right.
     */
    private void changeDirection() {
        if (face == Direction.RIGHT) {
            left = true;
            right = false;
            face = Direction.LEFT;
        } else {
            right = true;
            left = false;
            face = Direction.RIGHT;
        }
    }

    /**
     * Updates the aggro radar's Bat box.
     */
    private void updateRadarBoxLocation() {
        radarBox.setLocation(posX - width * CB_POS_MOD, posY - height * CB_POS_MOD);
    }

    public String getName() {
        return Bat.NAME;
    }

    @Override
    public List<GameObject> spawnObject(){
        return weapon.fire((int)getShootingDirection().getX() * weapon.ps, (int)getShootingDirection().getY() * weapon.ps);
    }

    public Point getShootingDirection(){
        return this.shootingDirection;
    }

    public void resetShootingDirection(){
        this.shootingDirection.setLocation(0, 0);
    }

    public void setShootingDirection(final GameObject e){
        if (e == null) {
            return;
        }
        int x, y;
        if (e.getHitBox().getPosX() < this.getHitBox().getPosX()){
            x = -1;
        } else if (e.getHitBox().getPosX() > this.getHitBox().getPosX()){
            x = 1;
        } else {
            x = 0;
        }
        if (e.getHitBox().getPosY() < this.getHitBox().getPosY()){
            y = -1;
        } else if (e.getHitBox().getPosY() > this.getHitBox().getPosY()){
            y = 1;
        } else {
            y = 0;
        }
        this.shootingDirection.setLocation(x, y);
    }
}
