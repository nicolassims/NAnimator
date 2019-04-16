package cs3500.animator.provider.model;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;

/**
 * This class adapts the IMotion class used by the providers into the more globally-recognized
 * Motion class we created. (A Motion field was omitted from this adaptor, as it could be executed
 * just as easily without.)
 */
public class Motion implements IMotion {

  private int starttime;
  private int endtime;
  private Keyframe keyframe;

  /**
   * Takes an IMotion, then sets this class' fields based on that.
   *
   * @param motion The IMotion that will be used to set this class' fields.
   */
  public Motion(IMotion motion) {
    this.starttime = motion.getBeginTime();
    this.endtime = motion.getBeginTime();
    this.keyframe = new KeyframeImpl(motion.getBeginTime(),
        new Position2D(motion.getNewX(), motion.getNewY()),
        new Size2D(motion.getNewLength(), motion.getNewHeight()),
        new TextureImpl(motion.getColor().getR(), motion.getColor().getG(),
            motion.getColor().getB(), 1));
  }

  /**
   * Takes a Motion--a different kind of Motion, mind you--then sets this class' fields based on
   * that.
   *
   * @param motion The Motion that will be used to set this class' fields.
   */
  public Motion(cs3500.animator.model.Motion motion) {
    this.starttime = motion.getFirstTick();
    this.endtime = motion.getEndFrame().getTick();
    this.keyframe = new KeyframeImpl(motion.getFirstTick(),
        new Position2D(motion.getFirstX(), motion.getFirstY()),
        new Size2D(motion.getFirstWidth(), motion.getFirstHeight()),
        new TextureImpl(motion.getStartFrame().getTexture().getRed(),
            motion.getStartFrame().getTexture().getGreen(),
            motion.getStartFrame().getTexture().getBlue(), 1));
  }

  /**
   * Takes a keyframe, then sets this class' fields based on that.
   *
   * @param keyframe The Keyframe that will be used to set this class' fields.
   */
  public Motion(Keyframe keyframe) {
    this.starttime = keyframe.getTick();
    this.keyframe = keyframe;
  }

  /**
   * Takes several different variables, then uses them to construct keyframes, which are then used
   * to set this class' variables.
   *
   * @param tick The tick this motion starts at.
   * @param name The name of the shape this tick is assigned to.
   * @param x The x-location of the end of this motion.
   * @param y The y-location of the end of this motion.
   * @param w The width of this shape at the end of this motion.
   * @param h The height of this shape at the end of this motion.
   * @param color The color of this shape at the end of this motion.
   */
  public Motion(int tick, int name, int x, int y, int w, int h, Color color) {
    this.keyframe = new KeyframeImpl(tick, new Position2D(x, y), new Size2D(w, h),
        new TextureImpl(color.getR(), color.getG(), color.getB(), 1));
  }

  @Override
  public void setLocation(int newX, int newY) {
    this.keyframe = new KeyframeImpl(keyframe.getTick(), new Position2D(newX, newY),
        keyframe.getSize(), keyframe.getTexture());
  }

  @Override
  public void setSize(int newLength, int newHeight) {
    this.keyframe = new KeyframeImpl(keyframe.getTick(), keyframe.getPosition(),
        new Size2D(newLength, newHeight), keyframe.getTexture());
  }

  @Override
  public void setColor(Color color) {
    this.keyframe = new KeyframeImpl(keyframe.getTick(), keyframe.getPosition(),
        keyframe.getSize(), new TextureImpl(color.getR(), color.getG(), color.getB(), 1));
  }

  @Override
  public String describe() {
    throw new UnsupportedOperationException(
        "Implemented for compatibility purposes, but unused. Returning SVG-formatted descriptions "
            + "of a Motion is something the view should handle, not the Motion proper.");
  }

  @Override
  public boolean timeIsIn(int time) {
    return starttime <= time && time <= endtime;
  }

  @Override
  public boolean overlapping(IMotion other) {
    return other.timeIsIn(starttime) || other.timeIsIn(endtime);
  }

  @Override
  public int getBeginTime() {
    return starttime;
  }

  @Override
  public int getEndTime() {
    return endtime;
  }

  @Override
  public int getNewX() {
    return ((int) keyframe.getFirstX());
  }

  @Override
  public int getNewY() {
    return ((int) keyframe.getFirstY());
  }

  @Override
  public int getNewLength() {
    return ((int) keyframe.getFirstWidth());
  }

  @Override
  public int getNewHeight() {
    return ((int) keyframe.getFirstWidth());
  }

  @Override
  public Color getColor() {
    return new Color(((int) keyframe.getTexture().getRed()),
        ((int) keyframe.getTexture().getBlue()), ((int) keyframe.getTexture().getGreen()));
  }

  @Override
  public String describeBeginAttributes() {
    throw new UnsupportedOperationException(
        "Implemented for compatibility purposes, but unused. Returning SVG-formatted descriptions "
            + "of a Motion is something the view should handle, not the Motion proper.");
  }

  @Override
  public String describeBeginAttributesEllipse() {
    throw new UnsupportedOperationException(
        "Implemented for compatibility purposes, but unused. Returning SVG-formatted descriptions "
            + "of a Motion is something the view should handle, not the Motion proper.");
  }
}
