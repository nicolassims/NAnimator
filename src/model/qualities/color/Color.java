package model.qualities.color;

import model.qualities.Quality;

/**
 * Represents java.awt.Color as modifiable quality by the model animations.
 */
public interface Color extends Quality {

  /**
   * Returns a java awt version of this color.
   */
  java.awt.Color getAsJavaAwtColor();

  double getRed();

  double getGreen();

  double getBlue();

  double getAlpha();

}
