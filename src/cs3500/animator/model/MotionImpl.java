package cs3500.animator.model;

/**
 * This class represents a motion affecting one shape across some range of ticks.
 */
public class MotionImpl implements Motion {

    private Shape parent;
    private Keyframe startFrame;
    private Keyframe endFrame;

    /**
     * Initializes MotionImpl, given two ints, a string, and two Keyframes.
     *
     * @param startTick  The first tick this motion is active on.
     * @param endTick    The last tick this motion is active on.
     * @param startFrame The state of this motion on its first frame.
     * @param endFrame   The state of this motion on its last frame.
     * @throws IllegalArgumentException if the tick measurements are impossible, or if any of the
     *                                  constructor's variables are null.
     */
    public MotionImpl(int startTick, int endTick, Shape parent,
                      Keyframe startFrame, Keyframe endFrame) {
        if (startTick < 0 || startTick > endTick || parent == null
                || startFrame == null || endFrame == null) {
            throw new IllegalArgumentException("MotionImpl not able to construct properly.");
        }
        this.parent = parent;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    @Override
    public String toFile() {
        return "motion " + parent.getName() + " " + startFrame.toFile()
                + " " + endFrame.toFile();
    }

    @Override
    public Shape getParent() {
        return parent;
    }

    @Override
    public Keyframe getStartFrame() {
        return startFrame;
    }

    @Override
    public Keyframe getEndFrame() {
        return endFrame;
    }

    @Override
    public double getFirstX() {
        return startFrame.getFirstX();
    }

    @Override
    public double getFirstY() {
        return startFrame.getFirstY();
    }

    @Override
    public double getFirstWidth() {
        return startFrame.getFirstWidth();
    }

    @Override
    public double getFirstHeight() {
        return startFrame.getFirstHeight();
    }

    @Override
    public String getFirstColors() {
        return startFrame.getFirstColors();
    }

    @Override
    public int getFirstTick() {
        return startFrame.getTick();
    }
}
