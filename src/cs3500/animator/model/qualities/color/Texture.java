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

  double getRed();

  double getGreen();

  double getBlue();

  double getAlpha();

}
