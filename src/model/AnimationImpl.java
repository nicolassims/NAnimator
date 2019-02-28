package model;

import java.util.Arrays;
import java.util.List;

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

  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {

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
