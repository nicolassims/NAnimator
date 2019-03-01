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
}
