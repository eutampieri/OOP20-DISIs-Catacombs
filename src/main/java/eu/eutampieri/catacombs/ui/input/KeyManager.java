package eu.eutampieri.catacombs.ui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {

    private boolean keys[], keyJustPressed[],keyPressable[];

    public KeyManager() {
        this.keys = new boolean[KeyEvent.RESERVED_ID_MAX];
        this.keyJustPressed = new boolean[KeyEvent.RESERVED_ID_MAX];
        this.keyPressable = new boolean[KeyEvent.RESERVED_ID_MAX];
    }

    public boolean isKeyPressed(int keyCode) {
        return this.keys[keyCode];
    }

    public boolean isKeyJustPressed(int keyCode) {
        return this.keyJustPressed[keyCode];
    }

    public void update(float delta) {
        for (int i=0; i<this.keys.length; i++) {
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
    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }
	
	/*@Override
	public void keyTyped(KeyEvent e) {
	}*/

}
