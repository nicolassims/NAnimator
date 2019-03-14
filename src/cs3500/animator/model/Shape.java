package cs3500.animator.model;

/**
 * This class represents a shape, and every motion it will go through is stored within.
 */
public interface Shape {

  /**
   * Returns a string representation of the shape's name.
   */
  String getName();

  /**
   * Returns a string representation of the shape's form.
   */
  String getShape();

  /**
   * Returns a string representation of the animation as a file.
   */
  String toFile();

  /**
   * Adds a motion to the shape's motion array.
   */
  void addMotion(Motion m);

  /**
   * Returns the total duration of the animation in ticks.
   */
  int totalDuration();

  /**
   * Returns the first x of this shape.
   */
  double getFirstX();

  /**
   * Returns the first y of this shape.
   */
  double getFirstY();

  /**
   * Returns the first width of this shape.
   */
  double getFirstWidth();

  /**
   * Returns the first height of this shape.
   */
  double getFirstHeight();

  /**
   * Returns the a string representation of the first color of this shape.
   */
  String getFirstColors();

  /**
   * Returns the first tick this shape appears at.
   */
  int getFirstTick();
}
