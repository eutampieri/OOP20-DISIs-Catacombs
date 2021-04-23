package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.Action;
import eu.eutampieri.catacombs.model.Camera;
import eu.eutampieri.catacombs.model.Direction;
import eu.eutampieri.catacombs.model.Entity;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.GameObjectType;
import eu.eutampieri.catacombs.model.Player;
import eu.eutampieri.catacombs.model.SimplePotion;
import eu.eutampieri.catacombs.model.gen.SingleObjectFactory;
import eu.eutampieri.catacombs.model.gen.SingleObjectFactoryImpl;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.gen.MobFactory;
import eu.eutampieri.catacombs.model.gen.MobFactoryImpl;
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

public final class World {
    // private final BufferedImage background;
    private final TileMap tileMap;
    private final KeyManagerProxy km = new KeyManagerProxy();
    private final DungeonGame game;
    // private final DungeonGame game = new DungeonGame();
    // TODO Camera
    private final Camera camera;

    private List<GameObject> entities;

    private Player player;

    public World(final TileMap tileMap, final DungeonGame game) {
        // this.background = am.getImage("background");
        this.tileMap = tileMap;
        final MobFactory mf = new MobFactoryImpl(this.tileMap);
        camera = new Camera(0, 0, tileMap.width() * AssetManagerProxy.getMapTileSize(),
                tileMap.height() * AssetManagerProxy.getMapTileSize());
        this.entities = mf.spawnRandom().stream().map((x) -> (GameObject) x).collect(Collectors.toList());

        final SingleObjectFactory objectFactory = new SingleObjectFactoryImpl(this.tileMap);
        final Random rand = new Random();
        this.entities.addAll(objectFactory.spawnSome(700, (x, y, tm) -> {
            final int healingPower = rand.nextInt(101);
            return new SimplePotion(healingPower, "Potion", x, y);
        }));

        this.player = (Player) mf.spawnSome(1, (x, y, tm) -> new Player(x, y, "", tm)).get(0);

        this.game = game;
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    private List<GameObject> getAllEntitiesExcept(final GameObject e) {
        return Stream.concat(this.entities.stream(), Stream.of(this.player)).filter((x) -> !e.equals(x))
                .collect(Collectors.toUnmodifiableList());
    }

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

        if (this.km.fire()) {
            this.player.fire();
        }

        final List<GameObject> newEntities = Stream.concat(entities.stream(), Stream.of(player))
                .filter((entity) -> this.isOnCamera(entity.getPosX(), entity.getPosY()))
                .flatMap((x) -> x.update(delta, this.getAllEntitiesExcept(x)).stream())
                .collect(Collectors.toList());
        this.entities.addAll(newEntities);

        this.entities = this.entities.stream().filter((x) -> !x.isMarkedForDeletion()).collect(Collectors.toList());
    }

    private boolean isOnCamera(final int x, final int y) {
        final int canvasX = x - camera.getXOffset();
        final int canvasY = y - camera.getYOffset();
        return canvasX > -AssetManagerProxy.getMapTileSize() && canvasX <= game.getWidth()
                && canvasY > -AssetManagerProxy.getMapTileSize() && canvasY <= game.getHeight();
    }

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
                    g2.drawRect(currentObj.getHitBox().getPosX() - camera.getXOffset(), currentObj.getHitBox()
                            .getPosY() - camera.getYOffset(), currentObj.getHitBox().getWidth(), currentObj.getHitBox().getHeight());
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

    public final boolean playerHasWon() {
        return this.player.isAlive() &&
                this.entities.stream()
                        .map((x) -> x.getKind())
                        .filter((x) -> x == GameObjectType.ENEMY || x == GameObjectType.BOSS)
                .count() == 0;
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
