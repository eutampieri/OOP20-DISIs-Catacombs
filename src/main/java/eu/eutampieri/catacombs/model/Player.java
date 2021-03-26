package eu.eutampieri.catacombs.model;

public final class Player extends GameObject implements LivingCharacter {
    private static final int BASE_MOVEMENT_SPEED = 2;
    private static final int MAX_BASE_HP = 100;
    private int health;
    private final String name;

    public Player(final int x, final int y, final String name) {
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
        if (this.health > 100) {
            this.health = 100;
        } else if (this.health < 0) {
            this.health = 0;
        }

    }

    @Override
    public void update(final int delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }
    public String getName() {
        return name;
    }



}
