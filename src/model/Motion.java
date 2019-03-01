package model;

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
}
