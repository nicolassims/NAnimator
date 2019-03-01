package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimationImpl implements Animation {

  private List<Shape> shapes;
  private Map<String, Shapes> supportedShapes;

  /**
   * Initializes ShapeImpl, given at least one Motion.
   *
   * //   * @param s the shape, or shapes, this animation contains. //   * @throws
   * IllegalArgumentException if the animation contains no shapes.
   */
  public AnimationImpl(/*Shapes... s*/) {
    shapes = new ArrayList<>();
    /*if (s.length == 0) {
      throw new IllegalArgumentException("Animation contains no shapes");
    }
    shapes = Arrays.asList(s);*/
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

    /*Position pos = new Position2D(0, 0);
    Size siz = new Size2D(0, 0);
    Texture text = new TextureImpl(0,0,0,0);
    Keyframe key = new KeyframeImpl(pos, siz, text);*/
    shapes.add(new ShapeImpl(shapeType, name));
  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {

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
