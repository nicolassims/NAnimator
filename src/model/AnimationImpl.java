package model;

import java.util.HashMap;
import java.util.Map;
import model.qualities.color.TextureImpl;
import model.qualities.dimensions.Size2D;
import model.qualities.positions.Position2D;

public class AnimationImpl implements Animation {

  private Map<String, Shape> shapes;

  /**
   * Initializes ShapeImpl.
   */
  public AnimationImpl() {
    shapes = new HashMap<>();
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
      throw new IllegalArgumentException("Shape type cannot be null.");
    }

    if (name == null) {
      throw new IllegalArgumentException("Shape name cannot be null.");
    }

    shapes.put(name, new ShapeImpl(shapeType, name));
  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {
    if (end.getTick() <= start.getTick()) {
      throw new IllegalArgumentException("End keyframe's before/concurrent with start keyframe");
    }
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("The given shape does not exist or has not been declared");
    }
    for (Shape s : shapes.values()) {
      if (s.getName().equals(shapeName)) {
        s.addMotion(new MotionImpl(start.getTick(), end.getTick(), s, start, end));
      }
    }
  }

  @Override
  public void addRotationless2DMotion(String shapeName, int startingTick, double x0, double y0,
      double w0, double h0, double r0, double g0, double b0, int endingTick, double x1, double y1,
      double w1, double h1, double r1, double g1, double b1) {

      this.addMotion(shapeName,
          new KeyframeImpl(startingTick,
              new Position2D(x0, y0),
              new Size2D(w0, h0),
              new TextureImpl(r0, g0, b0, 1)),
          new KeyframeImpl(endingTick,
              new Position2D(x1, y1),
              new Size2D(w1, h1),
              new TextureImpl(r1, g1, b1, 1)));
  }

  @Override
  public String toFile() {

    StringBuilder built = new StringBuilder("");
    for (Shape s : shapes.values()) {
      built.append("shape ").append(s.getName()).append(" ").append(s.getShape()).append("\n")
          .append(s.toFile());
    }
    return built.toString().substring(0, built.toString().length() - 1);
  }
}
