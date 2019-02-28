package model.qualities.dimensions;


import model.qualities.Quality;

/**
 * Represents the size of an object described by width and height dimensions.
 */
public class Size2D implements Size {

  private final double width;
  private final double height;

  /**
   * Creates a bi-dimensional size of width and height.
   */
  public Size2D(double width, double height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public String getQualities() {
    return "[Size:Size2D (width: " + this.width + ", height: " + this.height
        + ")]";
  }

  @Override
  public Quality getDifference(Quality other) {
    if (other instanceof Size2D) {
      Size2D otherDimension = (Size2D) other;
      return new Size2D(otherDimension.getWidth() - this.width,
          otherDimension.getHeight() - this.height);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public Quality divideBy(int duration) {
    return new Size2D(this.width / duration, this.height / duration);
  }

  @Override
  public Quality multiplyBy(int currentTick) {
    return new Size2D(this.width * currentTick, this.height * currentTick);
  }

  public Boolean equals(Quality other) {
    if (other instanceof Size2D) {
      Size2D otherDimension = (Size2D) other;
      return (this.getWidth() == otherDimension.getWidth() && this.getHeight() == otherDimension
          .getHeight());
    } else {
      return false;
    }
  }

  @Override
  public String toFile() {
    return (int) this.width + " " + (int) this.height;
  }

  @Override
  public Quality addTogether(Quality quality) {
    if (quality instanceof Size2D) {
      Size2D otherDimension = (Size2D) quality;
      return new Size2D(this.width + otherDimension.getWidth(),
          this.height + otherDimension.getHeight());
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }
}
