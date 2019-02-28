package model.qualities.positions;


import model.qualities.Quality;

/**
 * Describes the position of an object in different dimensions.
 */
public interface Position extends Quality {

  /**
   * Returns the position of an object in terms of x.
   */
  double getX();

  /**
   * Returns the position of an object in terms of y.
   */
  double getY();

  /**
   * Returns the position of an object in terms of z.
   */
  double getZ();


}
