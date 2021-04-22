package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.TileMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Slime class - the slime is an enemy that walks toward the targeted character
 * and deals damage on contact (through hit boxes).
 */
public final class Slime extends Entity implements HealthModifier {

    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int MOVEMENT_SPEED = 1;
    private static final int HEALTH = 10;
    private static final String NAME = "Slime";
    private static final int CB_POS_MOD = 15;
    private static final int CB_DIM_MOD = 30;
    private static final int DAMAGE_ON_HIT = 5;
    private static final long HIT_DELAY = 10L * 1_000_000_000;

    /**
     * Character followed by the slime.
     */
    private GameObject characterToFollow;
    /**
     * Slime aggro box.
     */
    private final CollisionBox radarBox;

    /**
     * The entity focussed by the slime currently;.
     * to be implemented maybe..
     */
    /* private final Entity entityToAggro; */

    private boolean canDmg;
    private long dmgDelayCount;

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
        this.canDmg = true;
        this.dmgDelayCount = 0;
    }

    @Override
    public void update(final long delta, final List<GameObject> others) {
        if (!canDmg) {
            dmgDelayCount += delta;
            if (dmgDelayCount >= this.HIT_DELAY) {
                dmgDelayCount = 0;
                canDmg = true;
            }
        }
        if (others.stream().filter((x) -> x instanceof Player)
                .findFirst()
                .get()
                .getHitBox()
                .overlaps(this.radarBox)) {
            setCharacterToFollow(others.stream().filter((x) -> x instanceof Player).findFirst().get());
        } else {
            setCharacterToFollow(null);
        }
        follow();
        super.update(delta, others);
        updateRadarBoxLocation();
        if (this.getHitBox().overlaps(others.stream().filter((x) -> x instanceof Player)
                .findFirst()
                .get()
                .getHitBox()) && canDmg
        ) {
            this.useOn((LivingCharacter) (others.stream().filter((x) -> x instanceof Player).findFirst().get()));
            canDmg = false;
        }
        this.resetMovement();
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
     * Utility method useful and used in GameState to make the Slime follow a
     * GameObject.
     *
     * @param e GameObject to follow (usually an entity, most likely the player)
     */
    public void setCharacterToFollow(final GameObject obj) {
        characterToFollow = obj;
    }

    /**
     * @return Character followed by the slime
     */
    public GameObject getCharacterToFollow() {
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
        } else if (characterToFollow.getPosX() > posX) {
            right = true;
        } else {
            right = false;
            left = false;
        }
        if (characterToFollow.getPosY() < posY) {
            up = true;
        } else if (characterToFollow.getPosY() > posY) {
            down = true;
        } else {
            up = false;
            down = false;
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

    @Override
    public int getHealthDelta() {
        return -this.DAMAGE_ON_HIT;
    }
}
