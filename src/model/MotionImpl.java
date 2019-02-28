package model;

public class MotionImpl implements Motion {
  private int startTick;
  private int endTick;
  private Keyframe startFrame;
  private Keyframe endFrame;

  public MotionImpl(int startTick, int endTick, Keyframe startFrame, Keyframe endFrame) {
    if (startTick < 0 || startTick >= endTick || startFrame == null || endFrame == null) {
      throw new IllegalArgumentException("MotionImpl not able to construct properly.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.startFrame = startFrame;
    this.endFrame = endFrame;
  }

  @Override
  public String toFile() {
    return "motion " + startTick + " " + startFrame.toFile()
        + " " + endTick + " " + endFrame.toFile();
  }
}
