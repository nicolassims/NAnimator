package cs3500.animator.provider.model;

/**
 * The type of the shape.
 */
public enum ShapeType {
  RECTANGLE, ELLIPSE;

  /**
   * The toString method, returns in a way CSV can read.
   * @return A string rect or ellipse.
   */
  public String toString() {
    if (this == RECTANGLE) {
      return "rect";
    }
    if (this == ELLIPSE) {
      return "ellipse";
    }
    throw new IllegalArgumentException("error in ShapeType");
  }
}
