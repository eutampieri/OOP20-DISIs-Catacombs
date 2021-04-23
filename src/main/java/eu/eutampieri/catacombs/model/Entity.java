package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.ui.gamefx.Animatable;
import eu.eutampieri.catacombs.ui.gamefx.AssetManagerProxy;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Abstract class for every living game Entity.
 */
public abstract class Entity extends GameObject implements LivingCharacter, Animatable {
    /**
     * Booleans to keep track of face direction.
     */
    protected boolean up, down, right, left;
    /**
     * Stores where the entity is facing.
     */
    protected Direction face;
    /**
     * Entity current health.
     */
    protected int hp;
    /**
     * Entity dimensions.
     */
    protected int width, height, size; // Entity width and height
    /**
     * Tile map where the entity is.
     */
    protected TileMap tileMap;

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Entity(final int x, final int y, final int width, final int height, final TileMap tileMap,
            final GameObjectType kind, final Team team) {
        super(x, y, kind, new CollisionBox(x, y, width, height), team);
        this.tileMap = tileMap;
        this.width = width;
        this.height = height;
        this.size = width >= height ? width : height;
    }

    /**
     * Getter for width.
     *
     * @return Entity width
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Setter for width.
     *
     * @param width Width dimension for the entity
     */
    public final void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Getter for height.
     *
     * @return Entity height
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Setter for height.
     *
     * @param height Height dimension for the entity
     */
    public final void setHeight(final int height) {
        this.height = height;
    }

    public final int getSize() {
        return this.size;
    }

    /**
     * Updates entity status in game loop.
     *
     * @param delta time between updates
     */
    @Override
    public List<GameObject> update(final long delta, final List<GameObject> others) {
        move();
        updateSpriteLocation();
        return List.of();
    }

    /**
     * Move the Entity based on its speed and direction facing.
     */
    protected void move() {

        int dx = 0, dy = 0;
        if (up) {
            dy = -speedY;
            face = Direction.UP;
            while (isUpCollision(Math.abs(dy))) {
                dy++;
            }
        }
        if (down) {
            dy = speedY;
            face = Direction.DOWN;
            while (isDownCollision(dy)) {
                dy--;
            }
        }
        if (left) {
            dx = -speedX;
            face = Direction.LEFT;
            while (isLeftCollision(Math.abs(dx))) {
                dx++;
            }
        }
        if (right) {
            dx = speedX;
            face = Direction.RIGHT;
            while (isRightCollision(dx)) {
                dx--;
            }
        }
        hitBox.move(dx, dy);
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving up.
     *
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isUpCollision(final int dy) {
        return tileMap.at(hitBox.getPosX() / AssetManagerProxy.getMapTileSize(),
                (hitBox.getPosY() - dy) / AssetManagerProxy.getMapTileSize()) == Tile.WALL
                || tileMap.at((hitBox.getPosX() + hitBox.getWidth()) / AssetManagerProxy.getMapTileSize(),
                        (hitBox.getPosY() - dy) / AssetManagerProxy.getMapTileSize()) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving right.
     *
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isRightCollision(final int dx) {
        return tileMap.at((hitBox.getPosX() + hitBox.getWidth() + dx) / AssetManagerProxy.getMapTileSize(),
                hitBox.getPosY() / AssetManagerProxy.getMapTileSize()) == Tile.WALL
                || tileMap.at((hitBox.getPosX() + hitBox.getWidth() + dx) / AssetManagerProxy.getMapTileSize(),
                        (hitBox.getPosY() + hitBox.getHeight()) / AssetManagerProxy.getMapTileSize()) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving down.
     *
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isDownCollision(final int dy) {
        return tileMap.at(hitBox.getPosX() / AssetManagerProxy.getMapTileSize(),
                (hitBox.getPosY() + hitBox.getHeight() + dy) / AssetManagerProxy.getMapTileSize()) == Tile.WALL
                || tileMap.at((hitBox.getPosX() + hitBox.getWidth()) / AssetManagerProxy.getMapTileSize(),
                        (hitBox.getPosY() + hitBox.getHeight() + dy) / AssetManagerProxy.getMapTileSize()) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving left.
     *
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isLeftCollision(final int dx) {
        return tileMap.at((hitBox.getPosX() - dx) / AssetManagerProxy.getMapTileSize(),
                hitBox.getPosY() / AssetManagerProxy.getMapTileSize()) == Tile.WALL
                || tileMap.at((hitBox.getPosX() - dx) / AssetManagerProxy.getMapTileSize(),
                        (hitBox.getPosY() + hitBox.getHeight()) / AssetManagerProxy.getMapTileSize()) == Tile.WALL;
    }

    /**
     * Updates GameObject location to coincide with hit box position.
     */
    protected void updateSpriteLocation() {
        posX = hitBox.getPosX();
        posY = hitBox.getPosY();
    }

    /**
     * Utility method that reset movement direction.
     */
    protected void resetMovement() {
        up = false;
        down = false;
        right = false;
        left = false;
    }

    /**
     * Renders object with the corresponding sprite.
     */
    public abstract Pair<Action, Direction> getActionWithDirection();

    @Override
    public boolean isMarkedForDeletion() {
        return !this.isAlive();
    }

    public boolean isMoving(){
        return this.right || this.left || this.up || this.down;
    }
}
