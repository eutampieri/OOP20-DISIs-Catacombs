package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Player extends Entity {
    private static final int BASE_MOVEMENT_SPEED = 2;
    private static final int MAX_BASE_HP = 100;
    private int health;
    private final String name;
    private CollisionBox hitbox;

    public Player(final int x, final int y, final String name, final TileMap tm) {
        super(x, y, tm, GameObjectType.PLAYER);
        setSpeed(BASE_MOVEMENT_SPEED);
        this.setHealth(MAX_BASE_HP);
        this.name = name;
        this.face = Direction.RIGHT;
        hitbox = new CollisionBox(getPosX(), getPosY(), getWidth(), getHeight());
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

    /**
     * Updates player status in game loop.
     * 
     * @param delta time between updates
     */
    @Override
    public void update(final long delta, final List<GameObject> others) {
        // TODO Auto-generated method stub

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
            case DIE:
                return true;
            default:
                return false;
        }
    }

    public final void move(final Direction d) {
        this.setSpeed(1);
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
        }
    }

    /**
     * @return Player name
     */
    public String getName() {
        return name;
    }

}
