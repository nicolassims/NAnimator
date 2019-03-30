package cs3500.animator.model;

/**
 * This class represents a motion affecting one shape across some range of ticks.
 */
public interface Motion {

  /**
   * Returns a string representation of the animation as a file.
   */
  String toFile();

  /**
   * Returns the parent of the motion.
   */
  Shape getParent();

  /**
   * Returns the start frame of the motion.
   */
  Keyframe getStartFrame();

  /**
   * Returns the end frame of the motion.
   */
  Keyframe getEndFrame();

  /**
   * Returns the first x of this motion.
   */
  double getFirstX();

  /**
   * Returns the first y of this motion.
   */
  double getFirstY();

  /**
   * Returns the first width of this motion.
   */
  double getFirstWidth();

  /**
   * Returns the first height of this motion.
   */
  double getFirstHeight();

  /**
   * Returns a string representation of the the first colors of this motion.
   */
  String getFirstColors();

  /**
   * Returns the first tick this motion occurs on.
   */
  int getFirstTick();

  /**
   * Sets the start frame of the motion to be equal to another keyframe.
   *
   * @param keyframe the keyframe to be set equal to another.
   */
  void setStartFrame(Keyframe keyframe);

  /**
   * Sets the start frame of the motion to be something new, though it occurs at the same time.
   *
   * @param x the location of the left side of the shape during this new frame.
   * @param y the location of the top side of the shape during this new frame.
   * @param w the width of the shape during this new frame.
   * @param h the height of the shape during this new frame.
   * @param r the redness of the shape during this new frame.
   * @param g the greenness of the shape during this new frame.
   * @param b the blueness of the shape during this new frame.
   */
  void setStartFrame(double x, double y, double w, double h, double r, double g, double b);

  /**
   * Sets the end frame of the motion to be something new, though it occurs at the same time.
   *
   * @param x the location of the left side of the shape during this new frame.
   * @param y the location of the top side of the shape during this new frame.
   * @param w the width of the shape during this new frame.
   * @param h the height of the shape during this new frame.
   * @param r the redness of the shape during this new frame.
   * @param g the greenness of the shape during this new frame.
   * @param b the blueness of the shape during this new frame.
   */
  void setEndFrame(double x, double y, double w, double h, double r, double g, double b);

  /**
   * Sets the end frame of the motion to be equal to another keyframe.
   *
   * @param keyframe the keyframe to be set equal to another.
   */
  void setEndFrame(Keyframe keyframe);
}
