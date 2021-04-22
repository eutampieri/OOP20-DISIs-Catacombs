package eu.eutampieri.catacombs.ui;

import eu.eutampieri.catacombs.ui.gamefx.AssetManager;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

/**
 * This class controls the animation of the game characters.
 */
public class Animation {

    private List<Optional<BufferedImage>> frames;
    private float frameDelay;
    private float timer;
    private int index;
    private boolean restart;
    private boolean reverse;
    private boolean reverseMode;

    /**
     * Animation constructor.
     *
     * @param frames the images used for the animation
     * @param frameDelay the frame delay
     */
    public Animation(final List<Optional<BufferedImage>> frames, final float frameDelay) {
        this.frames = frames;
        this.index = 0;
        this.frameDelay = frameDelay;
        this.restart = true;
    }

    /**
     * This constructor takes from the asset manager the images for the animation.
     *
     * @param frameskey the name of the image list
     * @param frameDelay the frame delay
     */

    public Animation(final String frameskey, final float frameDelay) {
        this(AssetManager.getAssetManager().getFrames(frameskey), frameDelay);
    }

    /**
     *
     * @return the list of images of an animation
     */

    public List<Optional<BufferedImage>> getFrames() {
        return this.frames;
    }

    /**
     *
     * @return the frame delay setted
     */

    public float getFrameDelay() {
        return frameDelay;
    }

    /**
     *
     * @return the single image of an animation
     */
    public Optional<BufferedImage> getCurrentFrame() {
        return this.frames.get(this.index);
    }

    /**
     *
     * @param stateTime an time index in an animation sequence
     * @return the frame at the index specified
     */

    public Optional<BufferedImage> getCurrentFrame(final float stateTime) {
        int i = (int) (stateTime / this.frameDelay);
        if (i >= this.frames.size()) {
            i = this.frames.size() - 1;
        }
        return this.frames.get(i);
    }

    /**
     *
     * @return the current index
     */

    public int getIndex() {
        return index;
    }

    /**
     *
     * @return teh current timer
     */

    public float getTimer() {
        return this.timer;
    }

    /**
     * This method decides if ana animation can be reversible.
     * @param reverseAble true if the animation is reversible
     */

    public void setReverseAble(final boolean reverseAble) {
        this.reverse = reverseAble;
    }

    /**
     * This method set the index for teh list of images.
     *
     * @param i the choosen index
     */

    public void setIndex(final int i) {
        this.index = i;
    }

    /**
     * This method set the frame delay for the animation.
     * @param frameDelay the frame delay to set
     */

    public void setFrameDelay(final float frameDelay) {
        this.frameDelay = frameDelay;
    }

    /**
     *
     * @return the last image of the List
     */

    public boolean isAtLastIndex() {
        return this.index == this.frames.size() - 1;
    }

    /**
     * This method check if the animation is finish so it can restart.
     * @return tue if the animation is finished
     */

    public boolean isComplete() {
        return this.isAtLastIndex();
    }

    /**
     * This method manages the sequences of the images for the animation.
     * @param delta gap time from the previous render
     */
    public void update(final float delta) {
        final int step = 1;
        this.timer += delta;
        if (this.index < 0) {
            this.index = 0;
            this.reverseMode = false;
        }
        if(this.index >= this.frames.size() - 1 ) {
            if (this.restart) {
                if (this.reverse) {
                    this.index = this.frames.size() - 1;
                    this.reverseMode = true;
                } else {
                    this.index = 0;
                }
            } else {
                this.index = this.frames.size() - 1;
            }
        }
        if (this.timer >= this.frameDelay) {
            this.index += reverseMode ? -step : step;
            this.timer = 0;
        }
    }

    /**
     *  This method reset the sequence of image and so the animation.
     */

    public void reset() {
        this.index = 0;
        this.timer = 0;

    }

    /**
     * This method copies the state of an animation.
     * @param animation the animation to be copied
     */
    public void copyState(final Animation animation) {
        this.timer = animation.getTimer();
        this.index = animation.getIndex();
    }

}
