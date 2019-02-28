package model;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a shape, and every motion it will go through is stored within.
 */
public class ShapeImpl implements Shape {
  private List<Motion> motions;
  private String name;
  private Shapes shapeType;

  /**
   * Initializes ShapeImpl, given a name and a shapeType. Not in that order.
   *
   * @param name The name of the shape.
   * @param shapeType The name of the shape.
   * @throws IllegalArgumentException if the shape contains no motions, or name is unspecified.
   */
  public ShapeImpl(Shapes shapeType, String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Name is unspecified");
    }
    this.name = name;
    this.shapeType = shapeType;
    this.motions = new ArrayList<>();
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
