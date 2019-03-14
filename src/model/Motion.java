package model;

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
}
