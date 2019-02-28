package model.qualities.color;

import model.qualities.Quality;

/**
 * This class represents a color that some shape could display.
 */
public class TextureImpl implements Texture {

  private double red;
  private double green;
  private double blue;
  private double alpha;

  /**
   * Initializes TextureImpl, given a Color.
   *
   * @param color The color of the texture. Just a different way of representing it, really.
   */
  public TextureImpl(java.awt.Color color) throws IllegalArgumentException {
    this.red = color.getRed();
    this.green = color.getGreen();
    this.blue = color.getBlue();
    this.alpha = color.getAlpha();
  }

  /**
   * Initializes TextureImpl, given rgba values.
   *
   * @param red How red the texture is.
   * @param green How green the texture is.
   * @param blue How blue the texture is.
   * @param alpha How alpha the texture is. (Okay, how transparent it is.)
   */
  public TextureImpl(double red, double green, double blue, double alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  @Override
  public java.awt.Color getAsJavaAwtColor() throws IllegalArgumentException {
    return new java.awt.Color((int) red, (int) green, (int) blue, (int) alpha);
  }

  @Override
  public double getRed() {
    return red;
  }

  @Override
  public double getGreen() {
    return green;
  }

  @Override
  public double getBlue() {
    return blue;
  }

  @Override
  public double getAlpha() {
    return alpha;
  }

  @Override
  public Quality addTogether(Quality quality) {
    if (quality instanceof TextureImpl) {
      TextureImpl otherColor = (TextureImpl) quality;
      double red = this.getRed() + otherColor.getRed();

      double green = this.getGreen() + otherColor.getGreen();

      double blue = this.getBlue() + otherColor.getBlue();

      double alpha = this.getAlpha() + otherColor.getAlpha();

      return new TextureImpl(red, green, blue, alpha);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public String getQualities() {
    return "[Texture:Texture (red: " + this.getRed() + ", green: " + this
        .getGreen() + ", blue: " + this.getBlue() + ", alpha: " + this.getAlpha()
        + ")]";
  }

  @Override
  public Quality getDifference(Quality other) {
    if (other instanceof TextureImpl) {
      TextureImpl otherColor = (TextureImpl) other;
      double red = otherColor.getRed() - this.getRed();

      double green = otherColor.getGreen() - this.getGreen();

      double blue = otherColor.getBlue() - this.getBlue();

      double alpha = otherColor.getAlpha() - this.getAlpha();

      return new TextureImpl(red, green, blue, alpha);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public Quality divideBy(int duration) {
    return new TextureImpl(this.getRed() / duration,
        this.getGreen() / duration, this.getBlue() / duration,
        this.getAlpha() / duration);
  }

  @Override
  public Quality multiplyBy(int currentTick) {
    return new TextureImpl(this.getRed() * currentTick,
        this.getGreen() * currentTick, this.getBlue() * currentTick,
        this.getAlpha() * currentTick);
  }

  public Boolean equals(Quality other) {
    if (other instanceof TextureImpl) {
      TextureImpl otherColor = (TextureImpl) other;
      return this.getRed() == otherColor.getRed() && this.getGreen() == otherColor.getGreen()
          && this.getBlue() == otherColor.getBlue() && this.getAlpha() == otherColor.getAlpha();
    } else {
      return false;
    }
  }

  @Override
  public String toFile() {
    return (int) this.red + " " + (int) this.green + " " + (int) this.blue;
  }
}
