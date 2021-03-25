package eu.eutampieri.catacombs.ui.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter {

    private boolean leftPress, rightPress;
    private float x,y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isLeftPressed() {
        return this.leftPress;
    }

    public boolean isRightPressed() {
        return this.rightPress;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPress = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPress = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPress = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPress = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }



}
