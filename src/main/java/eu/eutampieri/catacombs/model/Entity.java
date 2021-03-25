package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;

/**
 * Abstract class for every living game Entity.
 */
public abstract class Entity extends GameObject implements LivingCharacter{

    public static final int FACE_RIGHT = 0;
    public static final int FACE_LEFT = 1;
    public static final int FACE_DOWN = 2;
    public static final int FACE_UP = 3;
    protected boolean up, down, right, left; //Booleans to keep track of face direction
    protected int face; //Stores which face the Entity is facing

    protected int hp; //Entity health
    protected boolean isAlive;
    protected int width, height; //Entity width and height

    protected TileMap tileMap;
    protected CollisionBox hitBox; //Entity hit box

    /**
     * Entity constructor.
     * @param x X spawn position
     * @param y Y spawn position
     * @param tileMap Tile map in which Entity is spawned
     */
    public Entity(int x, int y, TileMap tileMap) {
        super(x, y, ID.Enemy);
        this.tileMap = tileMap;
    }

    /**
     * Setter for isAlive.
     * @param alive true if the entity is alive (hp>0); false if the entity is dead
     */
    public void setAlive(boolean alive) {
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
    public void setWidth(int width) {
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
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void update(int delta) {
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
        if(up) {
            if (!isUpCollision(speedY)) {
                hitBox.posY -= speedY;
            }
            face = FACE_UP;
        }
        if(down) {
            if (!isDownCollision(speedY)) {
                hitBox.posY += speedY;
            }
            face = FACE_DOWN;
        }
        if(left) {
            if (!isLeftCollision(speedX)) {
                hitBox.posX -= speedX;
            }
            face = FACE_LEFT;
        }
        if(right) {
            if (!isRightCollision(speedX)) {
                hitBox.posX += speedX;
            }
            face = FACE_RIGHT;
        }
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving up.
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isUpCollision(int dy) {
        return tileMap.at(posX, posY-dy)==Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving right.
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isRightCollision(int dx) {
        return tileMap.at(posX+dx, posY)==Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving down.
     * @param dy Entity speedY
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isDownCollision(int dy) {
        return tileMap.at(posX, posY+dy)==Tile.WALL;
    }

    /**
     * Checks if the Entity is going to collide into a wall while moving left.
     * @param dx Entity speedX
     * @return true if moving into a wall; false otherwise
     */
    protected boolean isLeftCollision(int dx) {
        return tileMap.at(posX-dx, posY)==Tile.WALL;
    }

    /**
     * Updates sprite location to coincide with Entity position.
     */
    protected void updateSpriteLocation() {
        this.posX = hitBox.posX;
        this.posY = hitBox.posY;
    }

    /**
     * Utility method that reset direction;
     */
    protected void resetMovement() {
        up = false;
        down = false;
        right = false;
        left = false;
    }

<<<<<<< HEAD
    /**
     * Getter for Entity hit box
     * @return Entity hit box
     */
=======
>>>>>>> 9ecb83a... Updated Slime.java and fixed bugs
    public CollisionBox getHitBox() {
        return hitBox;
    }

}
