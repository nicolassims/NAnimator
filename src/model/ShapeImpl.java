package model;

import java.util.Arrays;
import java.util.List;


/**
 * This class represents a shape, and every motion it will go through is stored within.
 */
public class ShapeImpl implements Shape {
  private List<Motion> motions;

  /**
   * Initializes ShapeImpl, given at least one Motion.
   *
   * @param m the motion, or motions, this shape will go through.
   * @throws IllegalArgumentException if the shape contains no motions.
   */
  public ShapeImpl(Motion... m) {
    if (m.length == 0) {
      throw new IllegalArgumentException("Shape contains no motions");
    }
    motions = Arrays.asList(m);
  }

  @Override
  public String toFile() {
    StringBuilder built = new StringBuilder();
    for (Motion m : motions) {
      built.append(m.toFile()).append("\n");
    }
    return built.toString();
  }
}
