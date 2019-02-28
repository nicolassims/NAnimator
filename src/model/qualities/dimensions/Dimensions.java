package model.qualities.dimensions;

import cs3500.animation.model.hw06.animatableobject.qualities.Quality;

/**
 * Represents ways to described an object size with dimensions.
 */
public interface Dimensions extends Quality {

  /**
   * Returns the size of an object in terms of width.
   */
  double getWidth();

  /**
   * Returns the size of an object in terms of height.
   */
  double getHeight();

}
