package eu.eutampieri.catacombs.model;

import java.util.List;

/**
 * Abstract class for every game object present in the game.
 */
public abstract class GameObject {

    /**
     * A useful enum specifying teams.
     */
    public enum Team {
        /**
         * Player's enemy.
         */
        ENEMY,
        /**
         * Player's ally.
         */
        FRIEND,
    };

    /**
     * Object team.
     */
    protected final Team team;

    /**
     * Object positions.
     */
    protected int posX, posY;
    /**
     * Entity kind.
     */
    protected GameObjectType kind;
    /**
     * Object speed.
     */
    protected int speedX, speedY;
    /**
     * Entity hit box.
     */
    protected CollisionBox hitBox; // Entity hit box

    /**
     *
     * @param x         Object x position
     * @param y         Object y position
     * @param kind      Object kind
     * @param hitBox    Object hit box
     * @param team      Object team
     */
    public GameObject(final int x, final int y, final GameObjectType kind, final CollisionBox hitBox, final Team team) {
        this.setPosX(x);
        this.setPosY(y);
        this.kind = kind;
        this.hitBox = hitBox;
        this.team = team;
    }

    /**
     * Method used in the game loop that updates all elements of a game obj.
     * @param delta time between updates
     * @param others list of the other entities updated in the game loop
     * @return the list of spawned objects
     */
    public abstract List<GameObject> update(long delta, List<GameObject> others);

    /**
     * Getter for object X position.
     * @return Object X position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Setter for object X position.
     * @param posX Object X position to be set
     */
    public void setPosX(final int posX) {
        this.posX = posX;
    }

    /**
     * Getter for object Y position.
     * @return Object Y position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Setter for object Y position.
     * @param posY Object Y position to be set
     */
    public void setPosY(final int posY) {
        this.posY = posY;
    }

    /**
     * Set position corresponding to coordinates (posX, posY).
     * @param posX Position on the X axis
     * @param posY Position on the Y axis
     */
    public void setPos(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Getter for object speed on the X axis.
     * @return Object X speed
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Setter for object speed on the X axis.
     * @param speedX Speed to be set
     */
    public void setSpeedX(final int speedX) {
        this.speedX = speedX;
    }

    /**
     * Getter for object speed on the Y axis.
     * @return Object Y speed
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Setter for object speed on the Y axis.
     * @param speedY Speed to be set
     */
    public void setSpeedY(final int speedY) {
        this.speedY = speedY;
    }

    /**
     * Set both speeds to a specified value (both X and Y).
     * @param speed Speed to be set
     */
    public void setSpeed(final int speed) {
        this.setSpeedX(speed);
        this.setSpeedY(speed);
    }

    /**
     * Getter for GameObject Kind.
     * @return Entity Kind
     */
    public GameObjectType getKind() {
        return kind;
    }

    /**
     * @return whether the object has to be removed from the entity list
     */
    public boolean isMarkedForDeletion() {
        return false;
    }

    /**
     * Getter for GameObject hit box.
     * @return GameObject hit box
     */
    public CollisionBox getHitBox() {
        return hitBox;
    }

    /**
     * Getter for Entity team.
     * @return GameObject team
     */
    public Team getTeam() {
        return team;
    }
}
