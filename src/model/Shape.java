package model;

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
}
