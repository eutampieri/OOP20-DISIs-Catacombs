package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.gen.ObjectFactoryImpl;
import eu.eutampieri.catacombs.model.gen.ObjectFactory;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bat class - the bat is an enemy that mostly stands still and fires bullets.
 * @see Entity
 */
public final class Bat extends Entity {

    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int MOVEMENT_SPEED = 3;
    private static final int HEALTH = 8;
    private static final int RADAR_BOX_POSITION_MODIFIER = 20 * AssetManagerProxy.getMapTileSize();
    private static final int RADAR_BOX_SIZE = 20 * 2 * AssetManagerProxy.getMapTileSize() + Math.max(WIDTH, HEIGHT);
    private static final int BASE_DAMAGE = 5;
    private static final int BASE_FIRE_RATE = 40;
    private static final int BASE_PROJECTILE_SPEED = 3;
    private static final String NAME = "Bat";
    private static final long MOVE_DELAY = 5L * 100;
    private static final long PAUSE_DELAY = 10L * 100;
    private static final int POTION_DROP_CHANCE = 15;
    private static final int WEAPON_DROP_CHANCE = 7;
    private static final int MAX_CHANCE = 100;

    private final Weapon weapon;
    private boolean isMoving;
    private long delayCounter;
    private long pauseCounter;
    private final CollisionBox radarBox;
    private final Point shootingDirection;
    private boolean hasDropped;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Bat is spawned
     */
    public Bat(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.ENEMY, GameObject.Team.ENEMY);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER,
                RADAR_BOX_SIZE, RADAR_BOX_SIZE);
        weapon = new Weapon(this, tileMap, this.getHitBox().getPosX(), this.getHitBox().getPosY(),
                BASE_DAMAGE, BASE_PROJECTILE_SPEED, BASE_FIRE_RATE, this.getTeam()) { };
        shootingDirection = new Point(0, 0);
        this.delayCounter = 0;
        this.pauseCounter = 0;
        this.isMoving = true;
    }

    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        final Random rand = new Random();
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
        others.stream().filter((x) -> x instanceof Player)
                .filter((x) -> x.getHitBox().overlaps(this.radarBox)).findFirst()
                .ifPresentOrElse((x) -> {
                    if (this.weapon.canFire()) {
                        setShootingDirection(x);
                    }
                }, () -> this.weapon.setCanFire(false));

        if (!this.isAlive()) {
            final List<GameObject> drops = new ArrayList<>();
            this.hasDropped = true;
            final ObjectFactory objectFactory = new ObjectFactoryImpl(this.tileMap);
            if (rand.nextInt(MAX_CHANCE) + 1 <= POTION_DROP_CHANCE) {
                drops.addAll(objectFactory.spawnAt(this.getHitBox().getPosX() / AssetManagerProxy.getMapTileSize(), this.getHitBox().getPosY() / AssetManagerProxy.getMapTileSize(),
                        (x, y, tm) -> {
                            final int healingPower = rand.nextInt(101);
                            return new SimplePotion(healingPower, "Potion", x, y);
                        }));
            }
            if (rand.nextInt(MAX_CHANCE) + 1 <= WEAPON_DROP_CHANCE) {
                drops.addAll(objectFactory.spawnAt(this.getHitBox().getPosX() / AssetManagerProxy.getMapTileSize(), this.getHitBox().getPosY() / AssetManagerProxy.getMapTileSize(),
                        (x, y, tm) -> {
                            if(rand.nextInt(2) == 0) {
                                return new Gun(null, tm, x, y, GameObject.Team.FRIEND);
                            } else {
                                return new Rifle(null, tm, x, y, GameObject.Team.FRIEND);
                            }
                        }));
            }
            return drops;
        }
        super.update(delta, others);
        updateRadarBoxLocation();
        weapon.update(delta, others);
        if (this.weapon.canFire && this.getShootingDirection().getX() != 0 && this.getShootingDirection().getY() != 0) {
            return weapon.fire((int) getShootingDirection().getX() * weapon.ps, (int) getShootingDirection().getY() * weapon.ps);
        }
        return List.of();
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
        radarBox.setLocation(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER);
    }

    /**
     *
     * @return The bat name
     */
    public String getName() {
        return Bat.NAME;
    }

    /**
     *
     * @return Bat shooting direction
     */
    public Point getShootingDirection() {
        return this.shootingDirection;
    }

    /**
     * Resets the shooting direction of the bat.
     */
    public void resetShootingDirection() {
        this.shootingDirection.setLocation(0, 0);
    }

    /**
     * Sets the shooting direction of the Bat.
     * @param e GameObject to aim
     */
    public void setShootingDirection(final GameObject e) {
        if (e == null) {
            return;
        }
        final int x = Integer.compare(e.getHitBox().getPosX(), this.getHitBox().getPosX());
        final int y = Integer.compare(e.getHitBox().getPosY(), this.getHitBox().getPosY());
        this.shootingDirection.setLocation(x, y);
    }

    @Override
    public boolean isMarkedForDeletion() {
        return !this.isAlive() && this.hasDropped;
    }
}
