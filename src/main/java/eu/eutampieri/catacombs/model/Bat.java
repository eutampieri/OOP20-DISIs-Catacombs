package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Bat class - the bat is an enemy that mostly stands still and fires bullets.
 */
public final class Bat extends Entity {

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;
    private static final int MOVEMENT_SPEED = 4;
    private static final int HEALTH = 8;
    private static final int CB_POS_MOD = 4;
    private static final int CB_DIM_MOD = 9;
    private static final int BASE_DAMAGE = 2;
    private static final int BASE_FIRE_RATE = 2;
    private static final int BASE_RANGE = 2;
    private static final String NAME = "Bat";
    private static final int MOVE_DELAY = 5;
    private static final int PAUSE_DELAY = 5;

    private final SimpleWeapon weapon;
    private boolean isMoving;
    private int delayCounter;
    private int pauseCounter;
    private final CollisionBox radarBox;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Bat is spawned
     */
    public Bat(final int x, final int y, final TileMap tileMap) {
        super(x, y, tileMap, EntityKind.ENEMY);
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        hitBox = new CollisionBox(posX, posY, width, height);
        radarBox = new CollisionBox(posX - width * CB_POS_MOD, posY - width * CB_POS_MOD, width * CB_DIM_MOD,
                height * CB_DIM_MOD);
        weapon = new SimpleWeapon(BASE_DAMAGE, "bat_wpn", BASE_FIRE_RATE, BASE_RANGE, -1) {
        };

        // TODO Animations

    }

    @Override
    public void update(final long delta) {
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
        super.update(delta);
        updateRadarBoxLocation();
        weapon.update(delta);
    }

    @Override
    public Pair<Action, Direction> render() {
        // TODO Auto-generated method stub

        return null;
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

}
