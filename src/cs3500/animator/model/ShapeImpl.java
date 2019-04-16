package cs3500.animator.model;

import cs3500.animator.model.qualities.Quality;
import cs3500.animator.model.qualities.color.Texture;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position;
import cs3500.animator.model.qualities.positions.Position2D;
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
    return shapeType.name().toLowerCase();
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
  public double getFirstRed() {
    return motions.get(0).getStartFrame().getTexture().getRed();
  }

  @Override
  public double getFirstGreen() {
    return motions.get(0).getStartFrame().getTexture().getGreen();
  }

  @Override
  public double getFirstBlue() {
    return motions.get(0).getStartFrame().getTexture().getBlue();
  }

  @Override
  public int getFirstTick() {
    return motions.get(0).getFirstTick();
  }

  @Override
  public List<Motion> getMotions() {
    return motions;
  }

  @Override
  public Texture getColorAt(int currentTick) {
    Motion currentMotion = this.getSurroundingMotion(currentTick);
    Texture texture0 = currentMotion.getStartFrame().getTexture();
    Texture texture1 = currentMotion.getEndFrame().getTexture();
    return (Texture) interpolateQualities(currentTick, texture0, texture1, currentMotion);
  }

  @Override
  public Position getPositionAt(int currentTick) {
    Motion currentMotion = this.getSurroundingMotion(currentTick);
    Position position0 = currentMotion.getStartFrame().getPosition();
    Position position1 = currentMotion.getEndFrame().getPosition();
    return (Position) interpolateQualities(currentTick, position0, position1, currentMotion);
  }

  @Override
  public Size getSizeAt(int currentTick) {
    Motion currentMotion = this.getSurroundingMotion(currentTick);
    Size size0 = currentMotion.getStartFrame().getSize();
    Size size1 = currentMotion.getEndFrame().getSize();
    return (Size) interpolateQualities(currentTick, size0, size1, currentMotion);
  }

  /**
   * Given a tick it searches for a motion which starting and ending keyframe surround the given
   * tick.
   */
  private Motion getSurroundingMotion(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("There are no negative ticks");
    }

    for (Motion m : motions) {
      if (m.getStartFrame().getTick() <= tick
          && m.getEndFrame().getTick() >= tick) {
        return m;
      }
    }
    /*return new MotionImpl(tick, tick, this,
        new KeyframeImpl(tick, new Position2D(0, 0), new Size2D(0, 0),
            new TextureImpl(0, 0, 0, 1)),
        new KeyframeImpl(tick, new Position2D(0, 0), new Size2D(0, 0),
            new TextureImpl(0, 0, 0, 1)));*/
    throw new IllegalArgumentException(this.name + " does not exist at tick " + tick);
  }

  /**
   * Helper that interpolates between two qualities.
   */
  private Quality interpolateQualities(int currentTick, Quality start, Quality end,
      Motion currentMotion) {
    int numberOfTicks =
        (currentMotion.getEndFrame().getTick() - currentMotion.getStartFrame().getTick()) + 1;
    Quality rateOfChange = start.getDifference(end).divideBy(numberOfTicks);
    return start.addTogether(
        rateOfChange.multiplyBy(currentTick - currentMotion.getStartFrame().getTick() + 1));
  }
}