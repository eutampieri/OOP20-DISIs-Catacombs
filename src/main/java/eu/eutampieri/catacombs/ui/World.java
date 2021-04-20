package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.model.*;
import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.mobgen.MobFactory;
import eu.eutampieri.catacombs.model.mobgen.MobFactoryImpl;
import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import eu.eutampieri.catacombs.ui.input.KeyManager;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    // private final BufferedImage background;
    private final TileMap tileMap;
    private final AssetManager am = AssetManager.getAssetManager();
    private final KeyManagerProxy km = new KeyManagerProxy();
    private final DungeonGame game;
    // private final DungeonGame game = new DungeonGame();
    // TODO Camera
    private final Camera camera;

    private List<GameObject> entities;

    private Player player;

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
    }

    public World(final TileMap tileMap, final DungeonGame game) {
        // this.background = am.getImage("background");
        this.tileMap = tileMap;
        final MobFactory mf = new MobFactoryImpl(this.tileMap);
        camera = new Camera(0, 0, tileMap.width() * 16, tileMap.height() * 16);
        this.entities = mf.spawnRandom().stream().map((x) -> (GameObject)x).collect(Collectors.toList());
        this.player = (Player)mf
                .spawnSome(1, (x, y, tm) -> new Player(x, y, "", tm))
                .get(0);

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
        return Stream.concat(
                this.entities.stream(),
                Stream.of(this.player)
        )
                .filter((x) -> !e.equals(x))
                .collect(Collectors.toUnmodifiableList());
    }

    public void update(final long delta) {
        this.player.stop();
			  if(this.km.up()) {
            this.player.move(Direction.UP);
        } else if(this.km.down()) {
            this.player.move(Direction.DOWN);
        } else if(this.km.left()) {
            this.player.move(Direction.LEFT);
        } else if(this.km.right()) {
            this.player.move(Direction.RIGHT);
        }
        player.update(delta, this.getAllEntitiesExcept(this.player));

        for (final GameObject entity : this.entities) {
            entity.update(delta, this.getAllEntitiesExcept(entity));
        }
        this.entities = this.entities
                .stream()
                .filter((x) -> !x.isMarkedForDeletion())
                .collect(Collectors.toList());
    }

    public void render(final Graphics2D g2) {
        camera.centerOnEntity(this.player, game.getWidth(), game.getHeight());
        // g2.drawImage(background, 0, 0, game.getGameWidth(), game.getGameHeight(),
        // null);
        /*
         * for(int y=0; y<7; y++){ for(int x=0; x<7; x++){ var i = y*7+x; var im =
         * am.getImage(i+""); g2.drawImage(im, null, x*16, y*16); } }
         */
        for (int y = 0; y < tileMap.height(); y++) {
            for (int x = 0; x < tileMap.width(); x++) {
                if (tileMap.at(x, y) == Tile.FLOOR) {
                    g2.drawImage(am.getImage("41"), null, x * 16 - camera.getXOffset(), y * 16 - camera.getYOffset());
                } else if (tileMap.at(x, y) == Tile.WALL) {
                    g2.drawImage(am.getImage("25"), null, x * 16 - camera.getXOffset(), y * 16 - camera.getYOffset());
                }
            }
        }

        // TODO render method
        // this.tileMap.render(g2, camera);

        // slimes

        Stream.concat(this.entities.stream(), Stream.of(this.player))
                .forEach((currentObj) -> {try {
                    final Entity currentEntity = (Entity) currentObj;
                    final Pair<Action, Direction> action = currentEntity.getActionWithDirection();
                    final BufferedImage img = AssetManagerProxy.getFrames(currentEntity, action.getLeft(), action.getRight()).get(0);
                    g2.drawImage(img, null, currentEntity.getPosX() - camera.getXOffset(), currentEntity.getPosY() - camera.getYOffset());
                } catch (ClassCastException e) {
                    // Treat it as a game object
                    final BufferedImage img = AssetManagerProxy.getSprite(currentObj);
                    g2.drawImage(img, null, currentObj.getPosX() - camera.getXOffset(), currentObj.getPosY() - camera.getYOffset());
                }});

        // TODO player.render parameters
        // this.player.render(g2, camera);

    }

}
