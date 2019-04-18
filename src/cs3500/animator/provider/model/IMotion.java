package cs3500.animator.provider.model;

/**
 * Represents a period in which all properties of the shape are modified at a
 * constant rate until the shape presumes the properties described in the Motion object.
 */
public interface IMotion {

  /**
   * Modifies the location at the end of the Motion.
   *
   * @param newX The X location
   * @param newY The Y location
   */
  void setLocation(int newX, int newY);

  /**
   * Modifies the size at the end of the Motion.
   *
   * @param newLength The width
   * @param newHeight The height
   */
  void setSize(int newLength, int newHeight);

  /**
   * Modifies the location of the Motion.
   *
   * @param color The Color
   */
  void setColor(Color color);

  /**
   * Returns the motion in string form. Is called in the describe() method in Shape.
   *
   * @return A string value of the motion.
   */
  String describe();

  /**
   * Given a time, tests whether that time occurs within the Motion.
   *
   * @return whether the time is in the Motion.
   */
  boolean timeIsIn(int time);

  /**
   * Given an other Motion, tests whether the start and end times are overlapping.
   *
   * @param other The other Motion being compared.
   * @return a boolean result.
   */
  boolean overlapping(IMotion other);

  /**
   * Getter method.
   *
   * @return The begin time.
   */
  int getBeginTime();

  /**
   * Getter method.
   *
   * @return The end time.
   */
  int getEndTime();

  /**
   * Getter method.
   * @return x
   */
  int getNewX();

  /** Getter method.
   * @return y
   */
  int getNewY();

  /**
   * Getter method.
   * @return new length
   */
  int getNewLength();

  /**
   * Getter method.
   * @return new height
   */
  int getNewHeight();

  /**
   * Getter method.
   * @return color
   */
  Color getColor();

  /**
   * A method that helps the CSV view get the information about the starting position of a shape.
   * @return String in CSV format
   */
  String describeBeginAttributes();

  /**
   * A method that helps the CSV view get the information about the starting position of a shape.
   * The difference is: it does it for the ELLIPSE shape.
   * @return String in CSV format
   */
  String describeBeginAttributesEllipse();
}

