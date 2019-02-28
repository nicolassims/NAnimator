package model.qualities.color;

import cs3500.animation.model.hw06.animatableobject.qualities.Quality;

public class ColorImpl implements Color {

  private double red;
  private double green;
  private double blue;
  private double alpha;

  /**
   * Initializes ColorImpl, given a Color.
   *
   * @returns a ColorImpl version of a java.awt.Color
   */
  public ColorImpl(java.awt.Color color) throws IllegalArgumentException {
    this.red = color.getRed();
    this.green = color.getGreen();
    this.blue = color.getBlue();
    this.alpha = color.getAlpha();
  }

  /**
   * Initializes ColorImpl, given rgba values.
   */
  public ColorImpl(double red, double green, double blue, double alpha) {
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
    if (quality instanceof ColorImpl) {
      ColorImpl otherColor = (ColorImpl) quality;
      double red = this.getRed() + otherColor.getRed();

      double green = this.getGreen() + otherColor.getGreen();

      double blue = this.getBlue() + otherColor.getBlue();

      double alpha = this.getAlpha() + otherColor.getAlpha();

      return new ColorImpl(red, green, blue, alpha);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public String getQualities() {
    return "[Color:Color (red: " + this.getRed() + ", green: " + this
        .getGreen() + ", blue: " + this.getBlue() + ", alpha: " + this.getAlpha()
        + ")]";
  }

  @Override
  public Quality getDifference(Quality other) {
    if (other instanceof ColorImpl) {
      ColorImpl otherColor = (ColorImpl) other;
      double red = otherColor.getRed() - this.getRed();

      double green = otherColor.getGreen() - this.getGreen();

      double blue = otherColor.getBlue() - this.getBlue();

      double alpha = otherColor.getAlpha() - this.getAlpha();

      return new ColorImpl(red, green, blue, alpha);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public Quality divideBy(int duration) {
    return new ColorImpl(this.getRed() / duration,
        this.getGreen() / duration, this.getBlue() / duration,
        this.getAlpha() / duration);
  }

  @Override
  public Quality multiplyBy(int currentTick) {
    return new ColorImpl(this.getRed() * currentTick,
        this.getGreen() * currentTick, this.getBlue() * currentTick,
        this.getAlpha() * currentTick);
  }

  public Boolean equals(Quality other) {
    if (other instanceof ColorImpl) {
      ColorImpl otherColor = (ColorImpl) other;
      return this.getRed() == otherColor.getRed() && this.getGreen() == otherColor.getGreen()
          && this.getBlue() == otherColor.getBlue() && this.getAlpha() == otherColor.getAlpha();
    } else {
      return false;
    }
  }

  @Override
  public String getTextualOutPut() {
    return (int) this.red + " " + (int) this.green + " " + (int) this.blue;
  }
}
