package eu.eutampieri.catacombs.model;

public final class CollisionBox {
    public int posX, posY;
    public int width, height;

    public CollisionBox(final int posX, final int posY, final int width, final int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public CollisionBox(final CollisionBox box) {
        this.posX = box.posX;
        this.posY = box.posY;
        this.width = box.width;
        this.height = box.height;
    }

    public void move(final int dx, final int dy) {
        this.posX += dx;
        this.posY += dy;
    }

    public boolean overlaps(final CollisionBox r) {
        return posX < r.posX + r.width && posX + width > r.posX && posY < r.posY + r.height && posY + height > r.posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(final int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(final int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public void setLocation(final int x, final int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public void debugShowBox() {
        // TODO
    }

}
