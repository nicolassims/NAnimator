package cs3500.animator.provider.model;

import java.util.ArrayList;

/**
 * Represents a single shape and contains a list of Motion.
 */
public interface IShape {

  /**
   * Add a motion to the back of the list of motions for the shape.
   * @param motion the motion to be added
   */
  void addMotion(IMotion motion);

  /**
   * Add one or more motions to the back of the list of motions for the shape.
   * @param motions the motions to be added
   */
  void addMotions(IMotion... motions);

  /**
   * Get the ID of the shape.
   * @return the ID of the shape
   */
  String getID();

  /**
   * Get an arraylist of all motions.
   * @return arraylist of motions
   */
  ArrayList<IMotion> getMotions();

  /**
   * Get an motion that is happening at a specific time.
   * @param time a time in the period of occurrence of the motion
   * @return the motion that is happening at that time
   */
  IMotion getMotion(int time);

  /**
   * Get a string description of the shape type.
   * @return the shape type (e.g. RECTANGLE)
   */
  String shapeTypeToString();

  /**
   * Get a text description of all the motions of the shape.
   * @return the text description of the motions
   */
  String describe();


  /**
   * Delete a key frame.
   * @param keyFrameIndex the index of the key frame to be deleted in the arraylist of key frames
   *                      in the shape
   */
  void deleteKeyFrame(int keyFrameIndex);

  /**
   * Edit a key frame.
   * @param index the index of the key frame to be edited in the arraylist of key frame
   * @param keyFrame a new key frame that replaces the old one
   */
  void editKeyFrame(int index, IMotion keyFrame);

  /**
   * Insert a key frame. If the targeted tick is before/after the animation of the shape,
   * it creates a new key frame which copies the values from the first/last key frame. If the
   * targeted tick is within the period of the animation, it creates a new key frame which
   * represents the intermediate state at the targeted tick.
   * @param tick the tick at which the new key frame is inserted
   *
   * @return the key frame inserted
   */
  IMotion insertKeyFrame(int tick);

  /**
   * Get an intermediate state of the shape at a specific tick.
   * @param tick the time desired
   * @return the intermediate state of the shape represented in a shape object
   */
  IShape getFrame(double tick);//FIX THIS Shape-->IShape

  /**
   * Getter for shape type.
   * @return the type of the shape
   */
  ShapeType getShapeType();

  /**
   * Getter for x of the shape.
   * @return x
   */
  int getX();

  /**
   * Getter for y of the shape.
   * @return y
   */
  int getY();

  /**
   * Getter for width of the shape.
   * @return width
   */
  int getWidth();

  /**
   * Getter for height of the shape.
   * @return height
   */
  int getHeight();

  /**
   * Getter for color of the shape.
   * @return color
   */
  Color getColor();
}


