package model;

/**
 * This interface represents a collection of qualities that some shape could display at one tick.
 */
public interface Keyframe {

  /**
   * Returns a string representation of the animation as a file.
   */
  String toFile();

  /**
   * Returns the tick at which this keyframe is present.
   */
  int getTick();

  /**
   * Returns the first x of this keyframe.
   */
  double getFirstX();

  /**
   * Returns the first y of this keyframe.
   */
  double getFirstY();

  /**
   * Returns the first width of this keyframe.
   */
  double getFirstWidth();

  /**
   * Returns the first height of this keyframe.
   */
  double getFirstHeight();

  /**
   * Returns a string representation of the first colors of this keyframe.
   */
  String getFirstColors();
}
