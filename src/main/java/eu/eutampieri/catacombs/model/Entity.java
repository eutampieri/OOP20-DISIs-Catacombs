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
    protected Face face;
    /**
     * Entity current health.
     */
    protected int hp;
    /**
     * Liveness status of the entity.
     */
    protected boolean isAlive;
    /**
     * Entity dimensions.
     */
    protected int width, height; //Entity width and height
    /**
     * Tile map where the entity is.
     */
    protected TileMap tileMap;
    /**
     * Entity hit box.
     */
    protected CollisionBox hitBox; //Entity hit box

    /**
     * @param x X spawn position
     * @param y Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Entity(final int x, final int y, final TileMap tileMap) {
        super(x, y, ID.ENEMY);
        this.tileMap = tileMap;
    }

    /**
     * Setter for isAlive.
     * @param alive true if the entity is alive (hp>0); false if the entity is dead
     */
    public void setAlive(final boolean alive) {
        isAlive = alive;
    }

    /**
     * Getter for width.
     * @return Entity width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter for width.
     * @param width Width dimension for the entity
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Getter for height.
     * @return Entity height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for height.
     * @param height Height dimension for the entity
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Updates entity status in game loop.
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
        if (up) {
            if (!isUpCollision(speedY)) {
                hitBox.setPosX(-speedY);
            }
            face = Face.FACE_UP;
        }
        if (down) {
            if (!isDownCollision(speedY)) {
                hitBox.setPosX(speedY);
            }
            face = Face.FACE_DOWN;
        }
        if (left) {
            if (!isLeftCollision(speedX)) {
                hitBox.setPosX(-speedX);
            }
            face = Face.FACE_LEFT;
        }
        if (right) {
            if (!isRightCollision(speedX)) {
                hitBox.setPosX(speedX);
            }
            face = Face.FACE_RIGHT;
        }
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving up.
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isUpCollision(final int dy) {
        return tileMap.at(posX, posY - dy) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving right.
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isRightCollision(final int dx) {
        return tileMap.at(posX + dx, posY) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving down.
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isDownCollision(final int dy) {
        return tileMap.at(posX, posY + dy) == Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving left.
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isLeftCollision(final int dx) {
        return tileMap.at(posX - dx, posY) == Tile.WALL;
    }

    /**
     * Updates sprite location to coincide with Entity position.
     */
    protected void updateSpriteLocation() {
        this.posX = hitBox.getPosX();
        this.posY = hitBox.getPosY();
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
     * @return Entity hit box
     */
    public CollisionBox getHitBox() {
        return hitBox;
    }

}