package model;

/**
 * This class represents a motion affecting one shape across some range of ticks.
 */
public class MotionImpl implements Motion {

  private int startTick;
  private int endTick;
  private String name;
  private Keyframe startFrame;
  private Keyframe endFrame;

  /**
   * Initializes MotionImpl, given two ints, a string, and two Keyframes.
   *
   * @param startTick The first tick this motion is active on.
   * @param endTick The last tick this motion is active on.
   * @param name The name of this motion.
   * @param startFrame The state of this motion on its first frame.
   * @param endFrame The state of this motion on its last frame.
   * @throws IllegalArgumentException if the tick measurements are impossible, or if any of the
   * constructor's variables are null.
   */
  public MotionImpl(int startTick, int endTick, String name, Keyframe startFrame,
      Keyframe endFrame) {
    if (startTick < 0 || startTick >= endTick ||
        startFrame == null || endFrame == null || name == null) {
      throw new IllegalArgumentException("MotionImpl not able to construct properly.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.name = name;
    this.startFrame = startFrame;
    this.endFrame = endFrame;
  }

  @Override
  public String toFile() {
    return "motion " + name + " " + startTick + " " + startFrame.toFile()
        + " " + endTick + " " + endFrame.toFile();
  }
}
