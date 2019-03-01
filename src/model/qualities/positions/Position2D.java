package model.qualities.positions;

import model.qualities.Quality;

/**
 * Represents the position of an object in a 2D plane.
 */
public class Position2D implements Position {

  private final double x;
  private final double y;

  /**
   * Creates a position 2d.
   *
   * @param x is the x coordinate.
   * @param y is the y coordinate.
   */
  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double getZ() {
    return 0;
  }

  @Override
  public String getQualities() {
    return "[Position:Position2D (x: " + this.x + ", y: " + this.y + ")]";
  }

  @Override
  public Quality getDifference(Quality other) {
    if (other instanceof Position2D) {
      Position2D otherPosition2D = (Position2D) other;
      return new Position2D(otherPosition2D.getX() - this.x, otherPosition2D.getY() - this.y);
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

  @Override
  public Quality divideBy(int duration) {
    return new Position2D(x / duration, y / duration);
  }

  @Override
  public Quality multiplyBy(int currentTick) {
    return new Position2D(this.x * currentTick, this.y * currentTick);
  }

  /**
   * Sees if another Quality is equal to this Position2D.
   *
   * @param other The other Quality to be checked for equality.
   * @return true if the other Quality has the same values, and if not, false.
   */
  public Boolean equalsQuality(Quality other) {
    if (other instanceof Position2D) {
      Position2D otherPosition2D = (Position2D) other;
      return (this.getX() == otherPosition2D.getX() && this.getY() == otherPosition2D.getY());
    } else {
      return false;
    }
  }

  @Override
  public String toFile() {
    return (int) this.x + " " + (int) this.y;
  }


  @Override
  public Quality addTogether(Quality quality) {
    if (quality instanceof Position2D) {
      Position2D otherPosition2D = (Position2D) quality;
      return new Position2D(this.x + otherPosition2D.getX(), this.y + otherPosition2D.getY());
    } else {
      throw new IllegalArgumentException("qualities must be of the same type to be added together");
    }
  }

}
