package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Slime class - the slime is an enemy that walks toward the targeted character
 * and deals damage on contact (through hit boxes).
 */
public final class Slime extends Entity {

    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int MOVEMENT_SPEED = 4;
    private static final int HEALTH = 10;
    private static final String NAME = "Slime";
    private static final int CB_POS_MOD = 4;
    private static final int CB_DIM_MOD = 9;
    /* private static final int DAMAGE_ON_HIT = 5; */

    /**
     * Character followed by the slime.
     */
    private Entity characterToFollow;
    /**
     * Slime aggro box.
     */
    private final CollisionBox radarBox;

    /**
     * Slime constructor.
     * 
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Slime is spawned
     */
    public Slime(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.ENEMY);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - (width * CB_POS_MOD), posY - (height * CB_POS_MOD), width * CB_DIM_MOD,
                height * CB_POS_MOD);
        // TODO Animations
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        resetMovement();
        follow();
        super.update(delta, others);
        updateRadarBoxLocation();
    }

    @Override
    public Pair<Action, Direction> getActionWithDirection() {
        return Pair.of(Action.MOVE, this.face);
    }

    @Override
    public boolean canPerform(final Action action) {
        return action == Action.MOVE;
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
     * Utility method useful and used in GameState to make the Slime follow an
     * Entity (most likely the Player). With this method Slimes can follow every
     * entity.
     * 
     * @param e Entity to follow
     */
    public void setCharacterToFollow(final Entity e) {
        characterToFollow = e;
    }

    /**
     * @return Character followed by the slime
     */
    public Entity getCharacterToFollow() {
        return characterToFollow;
    }

    /**
     * Makes Slime path towards the Character(Entity) to follow.
     */
    private void follow() {
        if (characterToFollow == null) {
            return;
        }
        if (characterToFollow.getPosX() < posX) {
            left = true;
        } else if (characterToFollow.getPosX() >= posX) {
            right = true;
        }
        if (characterToFollow.getPosX() < posY) {
            up = true;
        } else if (characterToFollow.getPosX() >= posY) {
            down = true;
        }
    }

    /**
     * Updates the aggro radar's Slime box.
     */
    public void updateRadarBoxLocation() {
        radarBox.setLocation(posX - width * 2, posY - height * 2);
    }

    public String getName() {
        return Slime.NAME;
    }
}
