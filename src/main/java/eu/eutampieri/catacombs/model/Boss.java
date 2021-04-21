package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public final class Boss extends Entity {

    private static final int HEIGHT = 4;
    private static final int WIDTH = 4;
    private static final int MOVEMENT_SPEED = 4;
    private static final int HEALTH = 40;
    private static final int CB_POS_MOD = 4;
    private static final int CB_DIM_MOD = 9;
    private static final int BASE_DAMAGE = 3;
    private static final int BASE_FIRE_RATE = 2;
    private static final int BASE_RANGE = 2;
    private static final String NAME = "Boss";
    private static final int MOVE_DELAY = 5;
    private static final int PAUSE_DELAY = 5;

    //private final SimpleWeapon weapon;
    private boolean isMoving;
    private int delayCounter;
    private int pauseCounter;
    private final CollisionBox radarBox;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Boss(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.BOSS);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - width * CB_POS_MOD, posY - width * CB_POS_MOD, width * CB_DIM_MOD,
                height * CB_DIM_MOD);
        //weapon = new SimpleWeapon(x, y, BASE_DAMAGE, "boss_wpn", BASE_FIRE_RATE, BASE_RANGE, -1) {
        //};
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        if (!isAlive()) {
            return;
        }

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

        super.update(delta, others);
        updateRadarBoxLocation();
        //weapon.update(delta, others);
    }

    @Override
    public Pair<Action, Direction> getActionWithDirection() {
        return Pair.of(Action.MOVE, this.face);
    }

    @Override
    public boolean canPerform(final Action action) {
        switch (action) {
            case IDLE:
            case MOVE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Makes the boss change facing direction.
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

    @Override
    public int getHealth() {
        return this.hp;
    }

    @Override
    public void setHealth(final int health) {
        this.hp = health;
    }

    public String getName() {
        return Boss.NAME;
    }

}
