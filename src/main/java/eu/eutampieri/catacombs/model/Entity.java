package eu.eutampieri.catacombs.model;

import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;

public abstract class Entity extends GameObject implements LivingCharacter{

    public static final int FACE_RIGHT = 0;
    public static final int FACE_LEFT = 1;
    public static final int FACE_DOWN = 2;
    public static final int FACE_UP = 3;
    protected boolean up, down, right, left;
    protected int face;

    protected int hp;
    protected boolean isAlive;
    protected int width, height;

    protected TileMap tileMap;
    protected CollisionBox hitBox;

    public Entity(int x, int y, TileMap tileMap) {
        super(x, y, ID.Enemy);
        this.tileMap = tileMap;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

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

    protected boolean isUpCollision(float dy) {
        return tileMap.at(posX, posY-1)==Tile.WALL;
    }

    protected boolean isRightCollision(float dx) {
        return tileMap.at(posX+1, posY)==Tile.WALL;
    }

    protected boolean isDownCollision(float dy) {
        return tileMap.at(posX, posY+1)==Tile.WALL;
    }

    protected boolean isLeftCollision(float dx) {
        return tileMap.at(posX-1, posY)==Tile.WALL;
    }

    protected void updateSpriteLocation() {
        this.posX = hitBox.posX;
        this.posY = hitBox.posY;
    }

    protected void resetMovement() {
        up = false;
        down = false;
        right = false;
        left = false;
    }

    public CollisionBox getHitBox() {
        return hitBox;
    }

}
