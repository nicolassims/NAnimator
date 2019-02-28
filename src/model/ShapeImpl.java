package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * This class represents a shape, and every motion it will go through is stored within.
 */
public class ShapeImpl implements Shape {
  private List<Motion> motions;
  private String name;
  private Shapes shapeType;

  /**
   * Initializes ShapeImpl, given at least one Motion.
   *
   * @param m the motion, or motions, this shape will go through.
   * @param name The name of the shape.
   * @param shapeType The name of the shape.
   * @throws IllegalArgumentException if the shape contains no motions, or name is unspecified.
   */
  public ShapeImpl(Shapes shapeType, String name, Motion... m) {
    if (m.length == 0 || name.equals("")) {
      throw new IllegalArgumentException("Shape contains no motions, or the name is unspecified");
    }
    this.name = name;
    this.shapeType = shapeType;
    motions = Arrays.asList(m);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getShape() {
    return shapeType.name();
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
