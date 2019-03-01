package model;

public interface Keyframe {

  /**
   * Returns a string representation of the animation as a file.
   */
  String toFile();

  /**
   * Returns the tick at which this keyframe is present.
   */
  int getTick();
}
