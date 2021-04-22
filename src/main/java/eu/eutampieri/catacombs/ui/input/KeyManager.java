package eu.eutampieri.catacombs.ui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * this class manages the key events.
 */

public final class KeyManager extends KeyAdapter {

    private static final KeyManager SINGLETON_KEYMANAGER = new KeyManager();
    private boolean[] keys;

    /**
     * Key manager constructor.
     */

    private KeyManager() {
        this.keys = new boolean[KeyEvent.RESERVED_ID_MAX];
    }

    /**
     * this Method is due to a single ton and i use to generate a KeyManager.
     *
     * @return a KeyManager
     */

    public static KeyManager getKeyManager() {
        return SINGLETON_KEYMANAGER;
    }

    /**
     * This method controls if a key is presse.
     * @param keyCode the key to control
     * @return true if the key is pressed
     */

    public boolean isKeyPressed(final int keyCode) {
        return this.keys[keyCode];
    }

    /**
     * This method keep track of the pressed keys.
     * @param e the key pressed
     */

    @Override
    public void keyPressed(final KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    /**
     * this method keep track of the released keys.
     * @param e the released key
     */

    @Override
    public void keyReleased(final KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }

    /*
     * @Override public void keyTyped(KeyEvent e) { }
     */

}
