package eu.eutampieri.catacombs.model;

/**
 *
 * Enemy Slime class.
 *
 */
public final class Slime extends GameObject implements LivingCharacter {

    public Slime(final int x, final int y) {
        super(x, y, ID.ENEMY);

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setHealth(final int health) {
        // TODO Auto-generated method stub

    }

}
