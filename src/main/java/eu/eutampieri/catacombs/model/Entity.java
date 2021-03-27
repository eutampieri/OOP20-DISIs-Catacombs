package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;

/**
 * Abstract class for every living game Entity.
 */
public abstract class Entity extends GameObject implements LivingCharacter {
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
    protected int width, height; // Entity width and height
    /**
     * Tile map where the entity is.
     */
    protected TileMap tileMap;
    /**
     * Entity hit box.
     */
    protected CollisionBox hitBox; // Entity hit box

    /**
     * @param x       X spawn position
     * @param y       Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Entity(final int x, final int y, final TileMap tileMap) {
        super(x, y, ID.ENEMY);
        this.tileMap = tileMap;
    }

    /**
     * Getter for width.
     * 
     * @return Entity width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter for width.
     * 
     * @param width Width dimension for the entity
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Getter for height.
     * 
     * @return Entity height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for height.
     * 
     * @param height Height dimension for the entity
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Updates entity status in game loop.
     * 
     * @param delta time between updates
     */
    @Override
    public void update(final int delta) {
        move();
        updateSpriteLocation();
    }

    @Override
    public void render() {

    }

    /**
     * Move the Entity based on its speed and direction facing.
     */
    protected void move() {

        int maxMovementUp = speedY;
        int maxMovementRight = speedX;
        int maxMovementDown = speedY;
        int maxMovementLeft = speedX;

        if (hitBox.getPosX() - speedX < 0) {
            maxMovementLeft = hitBox.getPosX();
        }
        if (hitBox.getPosX() + hitBox.getWidth() + speedX > tileMap.width() - 1) {
            maxMovementRight = tileMap.width() - hitBox.getPosX() - hitBox.getWidth() - 1;
        }
        if (hitBox.getPosY() - speedY < 0) {
            maxMovementUp = hitBox.getPosY();
        }
        if (hitBox.getPosY() + hitBox.getHeight() + speedY > tileMap.height() - 1) {
            maxMovementDown = tileMap.height() - hitBox.getPosY() - hitBox.getHeight() - 1;
        }

        if (up) {
            if (!isUpCollision(maxMovementUp)) {
                hitBox.move(0, -maxMovementUp);
            }
            face = Direction.UP;
        }
        if (down) {
            if (!isDownCollision(maxMovementDown)) {
                hitBox.move(0, maxMovementDown);
            }
            face = Direction.DOWN;
        }
        if (left) {
            if (isLeftCollision(maxMovementLeft)) {
                hitBox.move(-maxMovementLeft, 0);
            }
            face = Direction.LEFT;
        }
        if (right) {
            if (!isRightCollision(maxMovementRight)) {
                hitBox.move(maxMovementRight, 0);
            }
            face = Direction.RIGHT;
        }
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving up.
     * 
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isUpCollision(final int dy) {
        return tileMap.at(hitBox.getPosX(), hitBox.getPosY() - dy) == Tile.WALL
                || tileMap.at(hitBox.getPosX() + hitBox.getWidth(), hitBox.getPosY() - dy) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving right.
     * 
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isRightCollision(final int dx) {
        return tileMap.at(hitBox.getPosX() + hitBox.getWidth() + dx, hitBox.getPosY()) == Tile.WALL || tileMap
                .at(hitBox.getPosX() + hitBox.getWidth() + dx, hitBox.getPosY() + hitBox.getHeight()) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving down.
     * 
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isDownCollision(final int dy) {
        return tileMap.at(hitBox.getPosX(), hitBox.getPosY() + hitBox.getHeight() + dy) == Tile.WALL || tileMap
                .at(hitBox.getPosX() + hitBox.getWidth(), hitBox.getPosY() + hitBox.getHeight() + dy) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving left.
     * 
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isLeftCollision(final int dx) {
        return tileMap.at(hitBox.getPosX() - dx, hitBox.getPosY()) == Tile.WALL
                || tileMap.at(hitBox.getPosX() - dx, hitBox.getPosY() + hitBox.getHeight()) == Tile.WALL;
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
     * Getter for Entity hit box.
     * 
     * @return Entity hit box
     */
    public CollisionBox getHitBox() {
        return hitBox;
    }
}
