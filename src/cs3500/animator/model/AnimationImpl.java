package cs3500.animator.model;

import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This class represents a collection of Shapes that could be displayed during one animation.
 */
public class AnimationImpl implements Animation {

  private Map<String, Shape> shapes;
  private List<String> shapeNames;
  private Stack<Integer> lastKeyframeTickOnWholeAnimation;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Initializes ShapeImpl.
   */
  public AnimationImpl() {
    shapes = new HashMap<>();
    this.lastKeyframeTickOnWholeAnimation = new Stack<>();
    this.lastKeyframeTickOnWholeAnimation.push(0);
    this.shapeNames = new ArrayList<>();
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
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

    Shape shape = new ShapeImpl(shapeType, name);
    shapes.put(name, shape);
    shapeNames.add(shape.getName());
  }

  @Override
  public void removeShape(String name) {
    shapes.remove(name);
  }

  @Override
  public void addMotion(String shapeName, Keyframe start, Keyframe end) {
    if (end.getTick() > this.lastKeyframeTickOnWholeAnimation.peek()) {
      this.lastKeyframeTickOnWholeAnimation.push(end.getTick());
    }

    if (end.getTick() < start.getTick()) {
      throw new IllegalArgumentException("End keyframe is before/concurrent with start keyframe");
    }
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("The given shape does not exist or has not been declared");
    }
    int currentShapeDuration = shapes.get(shapeName).totalDuration();
    if (start.getTick() < currentShapeDuration || end.getTick() < currentShapeDuration) {
      throw new IllegalArgumentException("There cannot be overlap of motions.");
    }
    if (currentShapeDuration != 0 && currentShapeDuration != start.getTick()) {
      throw new IllegalArgumentException(
          "There cannot be gaps between motions. The next motion to be added should start at "
              + currentShapeDuration);
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

  @Override
  public int totalDuration() {
    return this.lastKeyframeTickOnWholeAnimation.peek();
  }

  @Override
  public Map<String, Shape> getShapes() {
    return shapes;
  }

  @Override
  public AnimationBuilder<Animation> getBuilder() {
    return new Builder(this);
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public void setShapeVisibility(String shapeName, boolean visibility)
      throws IllegalArgumentException {
    if (this.shapes.containsKey(shapeName)) {
      this.shapes.get(shapeName).setVisibility(visibility);
    }
  }

  @Override
  public int getCanvasWidth() {
    return this.width;
  }

  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  @Override
  public List<String> getShapeNames() {
    return this.shapeNames;
  }

  /**
   * This is a Builder class capable of building an Animation.
   */
  public static final class Builder implements AnimationBuilder<Animation> {

    Animation animation;

    /**
     * Creates a builder with no animation. Seems pointless.
     */
    public Builder() {
      this(new AnimationImpl());
    }

    /**
     * Creates a builder based on a pre-existing animation.
     *
     * @param anime The pre-existing animation to be built.
     */
    public Builder(Animation anime) {
      this.animation = anime;
    }

    @Override
    public Animation build() {
      return animation;
    }

    @Override
    public AnimationBuilder<Animation> setBounds(int x, int y, int width, int height) {
      this.animation.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> declareShape(String name, String type) {
      this.animation.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      this.animation
          .addRotationless2DMotion(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2,
              b2);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      if (this.animation.getShapeNames().contains(name)) {
        Shape thisShape = this.animation.getShapes().get(name);
        List<Motion> motions = thisShape.getMotions();
        Keyframe newKeyframe = new KeyframeImpl(t, new Position2D(x, y), new Size2D(w, h),
            new TextureImpl(r, g, b, 1));
          /* If the shape has no motions */
        if (motions.size() == 0) {
          motions.add(new MotionImpl(0, 0, thisShape, newKeyframe, newKeyframe));
          /* If t is before the first motion in the shape. */
        } else if (t < motions.get(0).getStartFrame().getTick()) {
          motions.add(0, new MotionImpl(t, motions.get(0).getStartFrame().getTick(),
              thisShape, newKeyframe, motions.get(0).getStartFrame()));
          /* If t is after the last motion of the shape. */
        } else if (t > motions.get(motions.size() - 1).getEndFrame().getTick()) {
          motions.add(new MotionImpl(motions.get(motions.size() - 1).getEndFrame().getTick(), t,
              thisShape, motions.get(motions.size() - 1).getEndFrame(), newKeyframe));
        } else {
          for (int i = 0; i < motions.size(); i++) {
            /* If a startFrame already exists at t, overwrite it.*/
            if (motions.get(i).getStartFrame().getTick() == t) {
              motions.get(i).setStartFrame(x, y, w, h, r, g, b);
            /* If an endFrame already exists at t, overwrite it.*/
            } else if (motions.get(i).getEndFrame().getTick() == t) {
              motions.get(i).setEndFrame(x, y, w, h, r, g, b);
            /* If t is between two other motions.*/
            } else if (i != motions.size() - 1 && motions.get(i).getEndFrame().getTick() < t
                && motions.get(i + 1).getStartFrame().getTick() > t) {
              motions.add(i + 1, new MotionImpl(motions.get(i).getEndFrame().getTick(),
                  t, thisShape, motions.get(i).getEndFrame(),
                  newKeyframe));
              motions.add(i + 2, new MotionImpl(t, motions.get(i + 2).getStartFrame().getTick(),
                  thisShape, newKeyframe,
                  motions.get(i + 2).getStartFrame()));
            /* If t is within an existing motion */
            } else if (motions.get(i).getStartFrame().getTick() < t
                && motions.get(i).getEndFrame().getTick() > t) {
              Keyframe saveEndframe = motions.get(i).getEndFrame();
              motions.set(i, new MotionImpl(motions.get(i).getStartFrame().getTick(), t,
                  thisShape, motions.get(i).getStartFrame(), newKeyframe));
              motions.add(i + 1, new MotionImpl(t, saveEndframe.getTick(),
                  thisShape, newKeyframe, saveEndframe));
            }
          }
        }
      } else {
        throw new IllegalArgumentException(name + " is not a valid shape name.");
      }
      return this;
    }

    @Override
    public AnimationBuilder<Animation> deleteKeyFrame(String name, int t) {
      if (this.animation.getShapeNames().contains(name)) {
        List<Motion> motions = this.animation.getShapes().get(name).getMotions();
        Boolean frameFound = false;
        for (int i = 0; i < motions.size(); i++) {
          if (motions.get(i).getStartFrame().getTick() == t) {
            frameFound = true;
            motions.get(i).setStartFrame(motions.get(i == 0 ? i : i - 1).getEndFrame());
          } else if (motions.get(i).getEndFrame().getTick() == t) {
            frameFound = true;
            if (i != motions.size() - 1) {
              motions.get(i).setEndFrame(motions.get(i).getStartFrame());
            } else {
              /*If the last keyframe of the shape is removed, than the total length of this motion
               * is 0, and, as such, is not worth keeping.
               */
              motions.remove(i);
              break;
            }
          }
          /* If this isn't the first motion, but the last motion has the same startFrame and
           * endFrame as this motion's startFrame, make this motion's startFrame the same as the
           * last motion's startFrame. Then delete the last motion. This ensures every list of
           * motions has no zero-duration motions with no change from one point to the other.
           * However, this only applies to affected motions. If the shapes were set up to have
           * "dead" motions, running deleteKeyFrame won't fix that.
           */
          if (i != 0 && motions.get(i).getStartFrame().toFile()
              .equals(motions.get(i - 1).getStartFrame().toFile()) &&
              motions.get(i - 1).getStartFrame().toFile()
                  .equals(motions.get(i - 1).getEndFrame().toFile())) {
            motions.remove(i - 1);
          }
        }
        if (!frameFound) {
          throw new IllegalArgumentException("There is not a frame located at " + t + ".");
        }
      } else {
        throw new IllegalArgumentException(name + " is not a valid shape name.");
      }
      return this;
    }

    @Override
    public AnimationBuilder<Animation> setKeyFrame(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      if (this.animation.getShapeNames().contains(name)) {
        Boolean frameFound = false;
        for (Motion motion : this.animation.getShapes().get(name).getMotions()) {
          if (motion.getStartFrame().getTick() == t) {
            motion.setStartFrame(x, y, w, h, r, g, b);
            frameFound = true;
          } else if (motion.getEndFrame().getTick() == t) {
            motion.setEndFrame(x, y, w, h, r, g, b);
            frameFound = true;
          }
        }
        if (!frameFound) {
          throw new IllegalArgumentException("There is not a frame located at " + t + ".");
        }
      } else {
        throw new IllegalArgumentException(name + " is not a valid shape name.");
      }
      return this;
    }
  }
}