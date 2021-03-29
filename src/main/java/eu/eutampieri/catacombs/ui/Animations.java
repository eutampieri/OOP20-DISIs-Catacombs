package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import java.awt.image.BufferedImage;


public class Animations {

    private BufferedImage[] frames;
    private float frameDelay;
    private float timer;
    private int index;
    private boolean restart = true;
    private boolean reverse;
    private boolean reverseMode;

    public Animations(final BufferedImage[] frames, final float frameDelay) {
        this.frames = frames.clone();
        this.index = 0;
        this.frameDelay = frameDelay;
    }

    public Animations(final String frameskey,final float frameDelay) {
        this(AssetManager.getFrames(frameskey), frameDelay);
    }

    public BufferedImage[] getFrames() {
        return this.frames.clone();
    }

    public float getFrameDelay() {
        return frameDelay;
    }

    public BufferedImage getCurrentFrame(){
        return this.frames[this.index];
    }

    public BufferedImage getCurrentFrame(final float stateTime) {
        int i = (int) (stateTime / this.frameDelay);
        if (i >= this.frames.length) {
            i = this.frames.length -1;
        }
        return this.frames[i];
    }

    public int getIndex() {
        return index;
    }

    public float getTimer() {
        return this.timer;
    }

    public void setReverseAble(final boolean reverseAble) {
        this.reverse = reverseAble;
    }

    public void setIndex(final int i) {
        this.index = i;
    }

    public void setFrameDelay(final float frameDelay) {
        this.frameDelay = frameDelay;
    }

    public boolean isAtLastIndex() {
        return this.index == this.frames.length - 1;
    }

    public boolean isComplete() {
        return this.isAtLastIndex();
    }

    public void update(final float delta){
        final int step = 1;
        this.timer +=delta;
        if (this.index < 0) {
            this.index = 0; // TODO test index
            this.reverseMode = false;
        }
        if(this.index >= this.frames.length) {
            if (this.restart) {
                if (this.reverse) {
                    this.index = this.frames.length - 1;
                    this.reverseMode = true;
                } else {
                    this.index = 0;
                }
            } else {
                this.index = this.frames.length - 1;
            }
        }
        if(this.timer >= this.frameDelay) {
            this.index += reverseMode ? - step : step;
            this.timer = 0;
        }
    }

    public void setRestartAble(final boolean restartAble) {
        this.restart = restartAble;
    }

    public void reset () {
        this.index = 0;
        this.timer = 0;

    }

    public void copyState(final Animations animation) {
        this.timer = animation.getTimer();
        this.index = animation.getIndex();
    }

}
