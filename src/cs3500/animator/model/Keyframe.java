package cs3500.animator.model;

import cs3500.animator.model.qualities.color.Texture;
import cs3500.animator.model.qualities.dimensions.Size;
import cs3500.animator.model.qualities.positions.Position;

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

  /**
   * Returns the shape's texture described by the keyframe.
   */
  Texture getTexture();

  /**
   * Returns the shape's size described by the keyframe.
   */
  Size getSize();

  /**
   * Returns the shape's position described by the keyframe.
   */
  Position getPosition();
}
