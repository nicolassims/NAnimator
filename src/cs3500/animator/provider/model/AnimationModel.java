package cs3500.animator.provider.model;

import java.util.ArrayList;

/**
 * A model for the animator which contains a number of shapes.
 */
public interface AnimationModel {

  /**
   * Essentially the toString() method for this object. List out every motion of every shape.
   *
   */
  String describe();

  /**
   * Adds a single shape to the model.
   *
   * @param shape The shape to be added to the model.
   */
  void addShape(IShape shape);

  /**
   * This method finds a shape within the model (based on the ID).
   *
   * @param id The unique name of the shape.
   * @return The shape found
   */
  IShape getShape(String id);


  /**
   * A getter for allMotions, only used in the copy constructor.
   *
   * @return allMotions.
   */
  ArrayList<IMotion> getAllMotions();

  /**
   * A getter for shapes.
   * Added since the last assignment.
   *
   * @return An array list of all of the shapes in the animation
   */
  ArrayList<IShape> getShapes();

  /**
   * A getter of list of shapes containing attributes (intermediate state) at a specific tick.
   * @param tick the tick desired for the intermediate state
   * @return the shapes in their intermediate state at the tick
   */
  ArrayList<IShape> getFrame(double tick);

  /**
   * Add a motion to a shape.
   * @param id the name of the shape
   * @param motion the motion to be added
   */
  void addMotion(String id, IMotion motion);

  /**
   * Getter of bound x.
   * @return x of the canvas
   */
  int getBoundX();

  /**
   * Getter of bound y.
   * @return y of the canvas
   */
  int getBoundY();

  /**
   * Getter of bound width.
   * @return width of the canvas
   */
  int getBoundWidth();

  /**
   * Getter of bound height.
   * @return height of the canvas
   */
  int getBoundHeight();

  /**
   * Delete a key frame.
   * @param shapeID the shape with the key frame to be deleted
   * @param keyFrameIndex the index of the key frame to be deleted in the arraylist of key frames
   *                      in the shape
   */
  void deleteKeyFrame(String shapeID, int keyFrameIndex);

  /**
   * Edit a key frame.
   * @param shapeID the shape with the key frame to be edited
   * @param index the index of the key frame to be edited in the arraylist of key frame
   * @param keyFrame a new key frame that replaces the old one
   */
  void editKeyFrame(String shapeID, int index, IMotion keyFrame);

  /**
   * Insert a key frame. If the targeted tick is before/after the animation of the shape,
   * it creates a new key frame which copies the values from the first/last key frame. If the
   * targeted tick is within the period of the animation, it creates a new key frame which
   * represents the intermediate state at the targeted tick.
   * @param shapeID the shape with the key frame to be edited
   * @param tick the tick at which the new key frame is inserted
   *
   * @return the key frame inserted
   */
  IMotion insertKeyFrame(String shapeID, int tick);

  /**
   * Delete a shape.
   * @param id the id of the shape
   */
  void deleteShape(String id);

  /**
   * Get the last tick of the whole animation.
   * @return last tick of the whole animation
   */
  int getLastTick();
}
