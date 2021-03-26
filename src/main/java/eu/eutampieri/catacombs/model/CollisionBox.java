package eu.eutampieri.catacombs.model;

public class CollisionBox {
    public int posX, posY;
    public int width, height;

    public CollisionBox(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public CollisionBox(CollisionBox box) {
        this.posX = box.posX;
        this.posY = box.posY;
        this.width = box.width;
        this.height = box.height;
    }

    public void move(int dx, int dy) {
        this.posX += dx;
        this.posY += dy;
    }

    public boolean overlaps(CollisionBox r) {
        return posX < r.posX + r.width && posX + width > r.posX && posY < r.posY + r.height && posY + height > r.posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public void setLocation(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void debugShowBox(){
        // TODO
    }

}
