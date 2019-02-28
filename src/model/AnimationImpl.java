package model;

import java.util.Arrays;
import java.util.List;
import model.qualities.color.Texture;
import model.qualities.color.TextureImpl;
import model.qualities.dimensions.Size;
import model.qualities.dimensions.Size2D;
import model.qualities.positions.Position;
import model.qualities.positions.Position2D;

public class AnimationImpl implements Animation {

  private List<Shape> shapes;

  /**
   * Initializes ShapeImpl, given at least one Motion.
   *
   * @param s the shape, or shapes, this animation contains.
   * @throws IllegalArgumentException if the animation contains no shapes.
   */
  public AnimationImpl(Shape... s) {
    if (s.length == 0) {
      throw new IllegalArgumentException("Animation contains no shapes");
    }
    shapes = Arrays.asList(s);
  }


  @Override
  public void addShape(String name, String type, int startingTick, int endingTick) {
    Shapes shapeType = null;
    for (Shapes s : Shapes.values()) {
      if (s.name().equals(type)) {
        shapeType = s;
      }
    }
    if (shapeType == null || startingTick < 0 || endingTick >= startingTick) {
      throw new IllegalArgumentException("Shape not valid--it's bounds are impossible, or its "
          + "type is nonexistent.");
    }

    /*Position pos = new Position2D(0, 0);
    Size siz = new Size2D(0, 0);
    Texture text = new TextureImpl(0, 0, 0, 0);
    Keyframe key = new KeyframeImpl(pos, siz, text);
    Motion mot = new MotionImpl(startingTick, endingTick, shapes.get(shapes.size()), key, key);
    shapes.add(new ShapeImpl(shapeType, name, mot));*/
    shapes.add(new ShapeImpl(shapeType, name));
  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {

  }

  @Override
  public void addRotationless2DMotion(String shapeName, int startingTick, double x0, double y0,
      double h0,
      double w0, double r0, double g0, double b0, int endingTick, double x1, double y1, double h1,
      double w1, double r1, double g1, double b1) {

  }

  @Override
  public String toFile() {
    StringBuilder built = new StringBuilder("");
    for (Shape s : shapes) {
      built.append("shape ").append(s.getShape()).append("\n").append(s.toFile());
    }
    return built.toString();
  }
}
