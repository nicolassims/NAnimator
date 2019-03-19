package cs3500.animator.model.qualities.color;

import cs3500.animator.model.qualities.Quality;

/**
 * Represents java.awt.Texture as modifiable quality by the cs3500.animator.model animations.
 */
public interface Texture extends Quality {

  /**
   * Returns a java awt version of this color.
   */
  java.awt.Color getAsJavaAwtColor();

  /**
   * Returns the red value of this color.
   */
  double getRed();

  /**
   * Returns the green value of this color.
   */
  double getGreen();

  /**
   * Returns the blue value of this color.
   */
  double getBlue();

  /**
   * Returns the alpha value of this color.
   */
  double getAlpha();
}