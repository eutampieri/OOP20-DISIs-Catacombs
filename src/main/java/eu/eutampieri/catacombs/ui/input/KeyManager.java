package eu.eutampieri.catacombs.ui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class KeyManager extends KeyAdapter {

    private static final KeyManager SINGLETON_KEYMANAGER = new KeyManager();
    private boolean[] keys;

    private KeyManager() {
        this.keys = new boolean[KeyEvent.RESERVED_ID_MAX];
    }

    public static KeyManager getKeyManager() {
        return SINGLETON_KEYMANAGER;
    }

    public boolean isKeyPressed(final int keyCode) {
        return this.keys[keyCode];
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }

    /*
     * @Override public void keyTyped(KeyEvent e) { }
     */

}
