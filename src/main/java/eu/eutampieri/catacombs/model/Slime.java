package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.gen.ObjectFactory;
import eu.eutampieri.catacombs.model.gen.ObjectFactoryImpl;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Slime class - the slime is an enemy that walks toward the targeted character
 * and deals damage on contact (through hit boxes).
 * @see Entity
 * @see HealthModifier
 */
public final class Slime extends Entity implements HealthModifier {

    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int MOVEMENT_SPEED = 1;
    private static final int HEALTH = 10;
    private static final String NAME = "Slime";
    private static final int RADAR_BOX_POSITION_MODIFIER = 20 * AssetManagerProxy.getMapTileSize();
    private static final int RADAR_BOX_SIZE = 20 * 2 * AssetManagerProxy.getMapTileSize() + Math.max(WIDTH, HEIGHT);
    private static final int DAMAGE_ON_HIT = 10;
    private static final long HIT_DELAY = 1_000;
    private static final int POTION_DROP_CHANCE = 20;
    private static final int WEAPON_DROP_CHANCE = 5;
    private static final int MAX_CHANCE = 100;

    /**
     * Character followed by the slime.
     */
    private GameObject characterToFollow;
    /**
     * Slime aggro box.
     */
    private final CollisionBox radarBox;

    private boolean canDmg;
    private long dmgDelayCount;
    private boolean hasDropped;

    /**
     *
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Slime is spawned
     */
    public Slime(final int x, final int y, final TileMap tileMap) {
        super(x, y, WIDTH, HEIGHT, tileMap, GameObjectType.ENEMY, GameObject.Team.ENEMY);
        setSpeed(MOVEMENT_SPEED);
        setHealth(HEALTH);
        face = Direction.RIGHT;
        radarBox = new CollisionBox(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER, RADAR_BOX_SIZE,
                RADAR_BOX_SIZE);
        this.canDmg = true;
        this.dmgDelayCount = 0;
    }

    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        final Random rand = new Random();
        this.resetMovement();
        if (!canDmg) {
            dmgDelayCount += delta;
            if (dmgDelayCount >= HIT_DELAY) {
                dmgDelayCount = 0;
                canDmg = true;
            }
        }
        others.stream().filter((x) -> x instanceof Player)
                .filter((x) -> x.getHitBox().overlaps(this.radarBox)).findFirst()
                .ifPresentOrElse(this::setCharacterToFollow, () -> setCharacterToFollow(null));

        follow();
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
                            if (rand.nextInt(2) == 0) {
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
        return List.of();
    }

    @Override
    public void useOn(final LivingCharacter character) {
        if (this.canDmg) {
            int currentHealth = character.getHealth();
            currentHealth += this.getHealthDelta();
            character.setHealth(currentHealth);
            this.canDmg = false;
        }
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
     * @param obj GameObject to follow (usually an entity, most likely the player)
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
        if ((characterToFollow.getPosX()
                + (characterToFollow.getHitBox().getWidth() / 2)
                - (this.width / 2)) < posX) {
            left = true;
        } else if ((characterToFollow.getPosX()
                + (characterToFollow.getHitBox().getWidth() / 2)
                - (this.width / 2)) > posX) {
            right = true;
        } else {
            right = false;
            left = false;
        }
        if ((characterToFollow.getPosY()
                + (characterToFollow.getHitBox().getHeight() / 2)
                - (this.height / 2)) < posY) {
            up = true;
        } else if ((characterToFollow.getPosY()
                + (characterToFollow.getHitBox().getHeight() / 2)
                - (this.height / 2)) > posY) {
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
        radarBox.setLocation(posX - RADAR_BOX_POSITION_MODIFIER, posY - RADAR_BOX_POSITION_MODIFIER);
    }

    @Override
    public String getName() {
        return Slime.NAME;
    }

    @Override
    public int getHealthDelta() {
        return -DAMAGE_ON_HIT;
    }

    @Override
    public boolean isMarkedForDeletion() {
        return !this.isAlive() && this.hasDropped;
    }
}
