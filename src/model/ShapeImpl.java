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
  private Stack<Integer> keyframeTickOfLastMotionAdded;

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
    this.keyframeTickOfLastMotionAdded = new Stack<>();
    this.keyframeTickOfLastMotionAdded.push(0);
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
    if (m.getEndFrame().getTick() > this.keyframeTickOfLastMotionAdded.peek()) {
      this.keyframeTickOfLastMotionAdded.push(m.getEndFrame().getTick());
    }
  }

  @Override
  public int totalDuration() {
    return this.keyframeTickOfLastMotionAdded.peek();
  }

  @Override
  public double getFirstX() {
    return motions.get(0).getFirstX();
  }

  @Override
  public double getFirstY() {
    return motions.get(0).getFirstY();
  }

  @Override
  public double getFirstWidth() {
    return motions.get(0).getFirstWidth();
  }

  @Override
  public double getFirstHeight() {
    return motions.get(0).getFirstHeight();
  }

  @Override
  public String getFirstColors() {
    return motions.get(0).getFirstColors();
  }

  @Override
  public int getFirstTick() {
    return motions.get(0).getFirstTick();
  }
}
