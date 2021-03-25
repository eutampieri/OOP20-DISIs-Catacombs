package eu.eutampieri.catacombs.model;

public class Player extends GameObject implements LivingCharacter{
    private static final int BASE_MOVEMENT_SPEED = 2;
    private static final int MAX_BASE_HP = 100;
    private static final int MIN_BASE_HP = 0;
    private int health;
    private final String name;

    public Player(int x, int y, String name) {
        super(x, y, ID.PLAYER);
        setVelX(BASE_MOVEMENT_SPEED);
        setVelY(BASE_MOVEMENT_SPEED);
        this.setHealth(MAX_BASE_HP);
        this.name = name;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(final int health) {
        this.health = health;
        if(this.health > this.MAX_BASE_HP) {
            this.health = this.MAX_BASE_HP;
        } else if(this.health < this.MIN_BASE_HP) {
            this.health = this.MIN_BASE_HP;
        }

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }
    public String getName() {
        return this.name;
    }



}
