package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.List;
import java.util.Random;

public final class Boss extends Entity {

    private static final int HEIGHT = 64;
    private static final int WIDTH = 64;
    private static final int MOVEMENT_SPEED = 4;
    private static final int HEALTH = 50;
    private static final int RADAR_BOX_POSITION_MODIFIER = 30 * AssetManagerProxy.getMapTileSize();
    private static final int RADAR_BOX_SIZE = 30 * 2 * AssetManagerProxy.getMapTileSize() + (Math.max(WIDTH, HEIGHT));
    private static final String NAME = "Boss";
    private static final long MOVE_DELAY = 15L * 100;
    private static final long PAUSE_DELAY = 10L * 100;
    private static final int BASE_DAMAGE = 15;
    private static final int BASE_PROJECTILE_SPEED = 3;
    private static final int BASE_FIRE_RATE = 3;

    private final Weapon weapon;
    private boolean isMoving;
    private int delayCounter;
    private int pauseCounter;
    private final CollisionBox radarBox;
    private final Point shootingDirection;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Boss(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.BOSS, GameObject.Team.ENEMY);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER, RADAR_BOX_SIZE,
                RADAR_BOX_SIZE);
        weapon = new Weapon(this, tileMap, this.getHitBox().getPosX(), this.getHitBox().getPosY(),
                BASE_DAMAGE, BASE_PROJECTILE_SPEED, BASE_FIRE_RATE, this.getTeam()) { };
        shootingDirection = new Point(0, 0);
        this.delayCounter = 0;
        this.pauseCounter = 0;
        this.isMoving = true;
    }

    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        resetShootingDirection();
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
                .overlaps(this.radarBox) && this.weapon.canFire) {
            setShootingDirection(others.stream().filter((x) -> x instanceof Player).findFirst().get());
        } else {
            this.weapon.setCanFire(false);
        }
        super.update(delta, others);
        updateRadarBoxLocation();
        weapon.update(delta, others);
        if (this.weapon.canFire && this.getShootingDirection().getX() != 0 && this.getShootingDirection().getY() != 0) {
            return weapon.fire((int)getShootingDirection().getX() * weapon.ps, (int)getShootingDirection().getY() * weapon.ps);
        }
        return List.of();
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
        final Random rand = new Random();
        final int c = rand.nextInt(4);
        switch (c) {
            case 0:
                face = Direction.UP;
                up = true;
            break;
            case 1:
                face = Direction.DOWN;
                down = true;
            break;
            case 2:
                face = Direction.LEFT;
                left = true;
            break;
            case 3:
                face = Direction.RIGHT;
                right = true;
            break;

        }

    }

    /**
     * Updates the aggro radar's Bat box.
     */
    private void updateRadarBoxLocation() {
        radarBox.setLocation(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER);
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
    public Point getShootingDirection() {
        return this.shootingDirection;
    }

    public void resetShootingDirection() {
        this.shootingDirection.setLocation(0, 0);
    }

    public void setShootingDirection(final GameObject e) {
        if (e == null) {
            return;
        }
        final int x = Integer.compare(e.getHitBox().getPosX(), this.getHitBox().getPosX());
        final int y = Integer.compare(e.getHitBox().getPosY(), this.getHitBox().getPosY());
        this.shootingDirection.setLocation(x, y);
    }


}
