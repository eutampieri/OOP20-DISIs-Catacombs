package eu.eutampieri.catacombs.ui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class KeyManager extends KeyAdapter {

    private boolean[] keys;
    private boolean[] keyJustPressed;
    private boolean[] keyPressable;

    public KeyManager() {
        this.keys = new boolean[KeyEvent.RESERVED_ID_MAX];
        this.keyJustPressed = new boolean[KeyEvent.RESERVED_ID_MAX];
        this.keyPressable = new boolean[KeyEvent.RESERVED_ID_MAX];
    }

    public boolean isKeyPressed(final int keyCode) {
        return this.keys[keyCode];
    }

    public boolean isKeyJustPressed(final int keyCode) {
        return this.keyJustPressed[keyCode];
    }

    public void update(final float delta) {
        for (int i = 0; i < this.keys.length; i++) {
            if (!this.keyPressable[i] && !this.keys[i]) {
                this.keyPressable[i] = true;
            } else if (this.keyJustPressed[i]) {
                this.keyPressable[i] = false;
                this.keyJustPressed[i] = false;
            }
            if (this.keyPressable[i] && this.keys[i]) {
                this.keyJustPressed[i] = true;
            }
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }

    /*@Override
    public void keyTyped(KeyEvent e) {
    }*/

}
