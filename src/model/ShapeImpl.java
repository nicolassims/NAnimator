package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * This class represents a shape, and every motion it will go through is stored within.
 */
public class ShapeImpl implements Shape {
  private List<Motion> motions;
  private String name;
  private Shapes shapeType;
  private Stack<Integer> KeyframeTickOfLastMotionAdded;

  /**
   * Initializes ShapeImpl, given a name and a shapeType. Not in that order.
   *
   * @param name The name of the shape.
   * @param shapeType The name of the shape.
   * @throws IllegalArgumentException if name is unspecified.
   */
  public ShapeImpl(Shapes shapeType, String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Name is unspecified");
    }
    this.name = name;
    this.shapeType = shapeType;
    this.motions = new ArrayList<>();
    this.KeyframeTickOfLastMotionAdded = new Stack<>();
    this.KeyframeTickOfLastMotionAdded.push(0);
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

  @Override
  public void addMotion(Motion m) {
    motions.add(m);
    if (m.getEndFrame().getTick() > this.KeyframeTickOfLastMotionAdded.peek()) {
      this.KeyframeTickOfLastMotionAdded.push(m.getEndFrame().getTick());
    }
  }

  @Override
  public int totalDuration() {
    return this.KeyframeTickOfLastMotionAdded.peek();
  }
}
