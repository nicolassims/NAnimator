package model;

/**
 * This class represents an animation. Which is defined as timeline where stuff is going to happen
 * and starts at tick 0 to end on a defined tick. is composed by a list of shapes with a defined
 * space on this time lime, and a defined list of motions they should perform.
 */
public interface Animation {

  /**
   * Given two strings, one for a name and one for a type of shape, it declares a shape. Throws an
   * IllegalArgument Exception if a shape with said name already exist of the shape\ is not
   * supported by the current implementation
   */
  void addShape(String name, String type, int startingTick, int endingTick);

  /**
   * Given a shape name, and two keyframes a motion is added.
   */
  void addMotion(String shapeName, Keyframe start, Keyframe end);

  /**
   * Less flexible version of add motions that trades-off flexibility for future program updates for
   * convenience.
   */
  void addRotationless2DMotion(String shapeName, int startingTick, double x0, double y0, double h0,
      double w0, double r0, double g0, double b0, int endingTick, double x1, double y1, double h1,
      double w1, double r1, double g1, double b1);

  /**
   * Returns a string representation of the animation as a file.
   */
  String toFile();

}
