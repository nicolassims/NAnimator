package model;

import java.util.ArrayList;
import java.util.List;

public class AnimationImpl implements Animation {

  private List<Shape> shapes;

  /**
   * Initializes ShapeImpl.
   */
  public AnimationImpl() {
    shapes = new ArrayList<>();
  }


  @Override
  public void addShape(String name, String type) {
    Shapes shapeType = null;
    for (Shapes s : Shapes.values()) {
      if (s.name().equals(type)) {
        shapeType = s;
      }
    }
    if (shapeType == null) {
      throw new IllegalArgumentException("Shape not valid--it's bounds are impossible, or its "
          + "type is nonexistent.");
    }

    shapes.add(new ShapeImpl(shapeType, name));
  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {
    if (end.getTick() <= start.getTick()) {
      throw new IllegalArgumentException("End keyframe's before/concurrent with start keyframe");
    }
    for (Shape s : shapes) {
      if (s.getName().equals(shapeName)) {
        s.addMotion(new MotionImpl(start.getTick(), end.getTick(), s, start, end));
      }
    }
  }

  @Override
  public void addRotationless2DMotion(String shapeName, int startingTick, double x0, double y0,
      double h0, double w0, double r0, double g0, double b0, int endingTick, double x1, double y1,
      double h1, double w1, double r1, double g1, double b1) {

  }

  @Override
  public String toFile() {

    StringBuilder built = new StringBuilder("");
    for (Shape s : shapes) {
      built.append("shape ").append(s.getName()).append(" ").append(s.getShape()).append("\n")
          .append(s.toFile());
    }
    return built.toString();
  }
}
