package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.*;
import eu.eutampieri.catacombs.model.gen.*;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import eu.eutampieri.catacombs.ui.input.KeyManager;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class contains all necessary entities to render the game and coordinates them.
 */
public final class World {
    private static final int BOSS_SPAWN_RANGE = 100;
    private static final int SPAWNED_POTIONS = 5;

    private final TileMap tileMap;
    private final KeyManagerProxy km = new KeyManagerProxy();
    private final DungeonGame game;
    private final Camera camera;
    private boolean bossHasBeenSpawned;

    private List<GameObject> entities;

    private Player player;

    /**
     * Create a new world.
     * @param tileMap the map
     * @param game the game, which is used to get its width &amp; height
     */
    public World(final TileMap tileMap, final DungeonGame game) {
        this.tileMap = tileMap;
        final MobFactory mf = new MobFactoryImpl(this.tileMap);
        camera = new Camera(0, 0, tileMap.width() * AssetManagerProxy.getMapTileSize(),
                tileMap.height() * AssetManagerProxy.getMapTileSize());
        this.entities = mf.spawnRandom().stream().map((x) -> (GameObject) x).collect(Collectors.toList());

        final ObjectFactory objectFactory = new ObjectFactoryImpl(this.tileMap);
        final Random rand = new Random();
        this.entities.addAll(objectFactory.spawnSome(SPAWNED_POTIONS, (x, y, tm) -> {
            final int healingPower = rand.nextInt(101);
            return new SimplePotion(healingPower, "Potion", x, y);
        }));

        this.entities.addAll(objectFactory.spawnSome(3, (x, y, tm) -> {

            if (rand.nextInt(2) == 0) {
                return new Gun(null, tm, x, y, GameObject.Team.FRIEND);
            } else {
                return new Rifle(null, tm, x, y, GameObject.Team.FRIEND);
            }
        }));

        this.player = (Player) mf.spawnSome(1, (x, y, tm) -> new Player(x, y, "", tm)).get(0);
        
        this.game = game;
    }

    /**
     * Getter for the tilemap.
     * @return the currently used TileMap
     */
    public TileMap getTileMap() {
        return this.tileMap;
    }

    /**
     * Player getter.
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Player setter.
     * @param player the new player, which will replace the current one
     */
    public void setPlayer(final Player player) {
        this.player = player;
    }

    private List<GameObject> getAllEntitiesExcept(final GameObject e) {
        return Stream.concat(this.entities.stream(), Stream.of(this.player)).filter((x) -> !e.equals(x))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Update all the entities in the world.
     * This method call will mainly update the state of the entities.
     * @param delta the number of milliseconds elapsed since the last update
     */
    public void update(final long delta) {
        this.player.stop();
        if (this.km.up()) {
            this.player.move(Direction.UP);
        } else if (this.km.down()) {
            this.player.move(Direction.DOWN);
        } else if (this.km.left()) {
            this.player.move(Direction.LEFT);
        } else if (this.km.right()) {
            this.player.move(Direction.RIGHT);
        }

        if (this.km.fire() && this.player.getWeapon().canFire()) {
            this.player.fire();
            this.player.getWeapon().setCanFire(false);
        }

        final List<GameObject> newEntities = Stream.concat(entities.stream(), Stream.of(player))
                .filter((entity) -> this.isOnCamera(entity.getPosX(), entity.getPosY()))
                .flatMap((x) -> x.update(delta, this.getAllEntitiesExcept(x)).stream())
                .collect(Collectors.toList());
        this.entities.addAll(newEntities);

        this.entities = this.entities.stream().filter((x) -> !x.isMarkedForDeletion()).collect(Collectors.toList());

        if (this.playerHasKilledAllEntities() && !this.bossHasBeenSpawned) {
            // Spawn boss
            final List<Entity> bossList = new MobFactoryImpl(tileMap).spawnNear(BOSS_SPAWN_RANGE, this.player, Boss::new);
            assert bossList.size() == 1;
            final GameObject boss = bossList.get(0);
            this.entities.add(boss);
            this.bossHasBeenSpawned = true;
        }
    }

    private boolean isOnCamera(final int x, final int y) {
        final int canvasX = x - camera.getXOffset();
        final int canvasY = y - camera.getYOffset();
        return canvasX > -AssetManagerProxy.getMapTileSize() && canvasX <= game.getWidth()
                && canvasY > -AssetManagerProxy.getMapTileSize() && canvasY <= game.getHeight();
    }

    /**
     * Render on screen the entities and the map.
     * @param g2 The canvas onto which the rendering will be done.
     */
    public void render(final Graphics2D g2) {
        camera.centerOnEntity(this.player, game.getWidth(), game.getHeight());

        for (int y = 0; y < tileMap.height(); y++) {
            for (int x = 0; x < tileMap.width(); x++) {
                final int canvasX = x * AssetManagerProxy.getMapTileSize() - camera.getXOffset();
                final int canvasY = y * AssetManagerProxy.getMapTileSize() - camera.getYOffset();
                if (isOnCamera(x * AssetManagerProxy.getMapTileSize(), y * AssetManagerProxy.getMapTileSize())) {
                    final Optional<BufferedImage> tile = AssetManagerProxy.getTileSprite(tileMap.at(x, y));
                    tile.ifPresent(bufferedImage -> g2.drawImage(bufferedImage, null, canvasX, canvasY));
                }
            }
        }

        Stream.concat(this.entities.stream(), Stream.of(this.player))
                .filter((x) -> this.isOnCamera(x.getPosX(), x.getPosY()))
                .forEach((currentObj) -> {
                    try {
                        final Entity currentEntity = (Entity) currentObj;
                        final Pair<Action, Direction> action = currentEntity.getActionWithDirection();
                        final Animation animation = AssetManagerProxy.getFrames(currentEntity, action.getLeft(), action.getRight());
                        if (currentEntity.isMoving()) {
                            final BufferedImage toShow = animation.getCurrentFrame().get();
                            g2.drawImage(toShow, null, currentEntity.getPosX() - camera.getXOffset(), currentEntity.getPosY() - camera.getYOffset());
                        } else {
                            final BufferedImage toShow = animation.getFrames().get(0).get();
                            g2.drawImage(toShow, null, currentEntity.getPosX() - camera.getXOffset(), currentEntity.getPosY() - camera.getYOffset());
                        }
                    } catch (ClassCastException e) {
                        // Treat it as a game object
                        final BufferedImage img = AssetManagerProxy.getSprite(currentObj);
                        g2.drawImage(img, null, currentObj.getPosX() - camera.getXOffset(), currentObj.getPosY() - camera.getYOffset());
                    }
                });
    }

    private boolean playerHasKilledAllEntities() {
        return this.entities.parallelStream()
                .map(GameObject::getKind)
                .noneMatch((x) -> x == GameObjectType.ENEMY || x == GameObjectType.BOSS);
    }

   /**
     * This function checks if the player has won, i.e. if the boss has already been spawned (and killed)
     * and every other LivingCharacter (except for the player) has died.
     * @return Whether the player has won the game or not.
     */
    public boolean playerHasWon() {
        return this.player.isAlive()
                && this.playerHasKilledAllEntities()
                && this.bossHasBeenSpawned;
    }

    private static final class KeyManagerProxy {
        private final KeyManager km = KeyManager.getKeyManager();

        public boolean up() {
            return this.km.isKeyPressed(KeyEvent.VK_W) || this.km.isKeyPressed(KeyEvent.VK_UP);
        }

        public boolean down() {
            return this.km.isKeyPressed(KeyEvent.VK_S) || this.km.isKeyPressed(KeyEvent.VK_DOWN);
        }

        public boolean left() {
            return this.km.isKeyPressed(KeyEvent.VK_A) || this.km.isKeyPressed(KeyEvent.VK_LEFT);
        }

        public boolean right() {
            return this.km.isKeyPressed(KeyEvent.VK_D) || this.km.isKeyPressed(KeyEvent.VK_RIGHT);
        }

        public boolean fire() {
            return this.km.isKeyPressed(KeyEvent.VK_SPACE);
        }
    }
}
