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
  void addShape(String name, String type);

  /**Given a shape name, from tick, a to tick and both starting and ending position, size,
   *  either the shape does not exist, there is overlap on the timing with other motions or*/
  //void addMontion(String shape, )

}
