package model.qualities.dimensions;

import model.qualities.Quality;

/**
 * Represents ways to described an shape size with dimensions.
 */
public interface Size extends Quality {

  /**
   * Returns the size of an shape in terms of width.
   */
  double getWidth();

  /**
   * Returns the size of an shape in terms of height.
   */
  double getHeight();

}
