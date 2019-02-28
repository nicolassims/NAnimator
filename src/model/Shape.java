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
}
