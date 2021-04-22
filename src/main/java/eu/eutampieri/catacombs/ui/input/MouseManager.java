package eu.eutampieri.catacombs.ui.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class manages the mouse events.
 */

public final class MouseManager extends MouseAdapter {

    private boolean leftPress, rightPress;
    private float x;
    private float y;

    /**
     *
     * @return the x of the mouse pointer.
     */

    public float getX() {
        return x;
    }

    /**
     *
     * @return the y of the mouse pointer.
     */

    public float getY() {
        return y;
    }

    /**
     * This method control if the left key of the mouse is pressed.
     * @return true if is is pressed
     */

    public boolean isLeftPressed() {
        return this.leftPress;
    }

    /**
     * This method control if the right key of the mouse is pressed.
     * @return true if it is pressed
     */

    public boolean isRightPressed() {
        return this.rightPress;
    }

    /**
     * This method check the press of the mouse keys.
     * @param e the mouse event to check
     */

    @Override
    public void mousePressed(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPress = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPress = true;
        }

    }

    /**
     * This method check the release of the mouse keys.
     * @param e the mouse event to check
     */

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPress = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPress = false;
        }
    }

    /**
     * This method set the x and y value using the position of the mouse pointer.
     * @param e the mouse event used to se the position
     */

    @Override
    public void mouseMoved(final MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

}
