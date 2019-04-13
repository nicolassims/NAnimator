package cs3500.animator.provider.model;

/**
 * Represents a color that a shape can have.
 */
public class Color {

  private int r;
  private int g;
  private int b;

  /**
   * Getter for R.
   *
   * @return R
   */
  public int getR() {
    return r;
  }

  /**
   * Getter for G.
   *
   * @return G.
   */
  public int getG() {
    return g;
  }

  /**
   * Getter for B.
   *
   * @return B
   */
  public int getB() {
    return b;
  }

  /**
   * Shape toString(). This is called by describe().
   *
   * @return A string for the shape.
   */
  public String toString() {
    return "" + r + " " + g + " " + b;
  }

  /**
   * Shape toString(), but formatted in the CSV way.
   *
   * @return A string for the shape.
   */
  public String cvgToString() {
    return "rgb(" + r + "," + g + "," + b + ")";
  }

  /**
   * A constructor that takes in the numeric value of a color.
   */
  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

}
