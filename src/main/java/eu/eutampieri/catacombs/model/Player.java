package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

public class Player extends Entity {
    private static final int BASE_MOVEMENT_SPEED = 2;
    private static final int MAX_BASE_HP = 100;
    private static final int SIZE = 10;
    private int health;
    private final String name;

    public Player(final int x, final int y, final String name, final TileMap tm) {
        super(x, y, SIZE, SIZE, tm, GameObjectType.PLAYER);
        setSpeed(BASE_MOVEMENT_SPEED);
        this.setHealth(MAX_BASE_HP);
        this.name = name;
        this.face = Direction.RIGHT;
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
        }
    }
		public void stop() {
			this.resetMovement();
		}
    public boolean isMoving(){
        return this.right || this.left || this.up || this.down;
    }

    /**
     * @return Player name
     */
    public String getName() {
        return name;
    }
}
