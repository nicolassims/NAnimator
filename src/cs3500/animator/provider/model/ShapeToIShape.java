package cs3500.animator.provider.model;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.MotionImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.model.Shapes;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import java.util.ArrayList;

/**
 * Turns Nicolas & Forrest's implementation of a Shape into an implementer of the provided IShape
 * interface.
 */
public class ShapeToIShape implements IShape {

  private Shape shape;
  private int t;
  private double x;
  private double y;
  private double w;
  private double h;
  private double r;
  private double g;
  private double b;

  /**
   * Simply takes a Shape, and sets this class' field to be that Shape.
   *
   * @param shape The Shape that will be treated like an IShape.
   */
  public ShapeToIShape(Shape shape) {
    this.shape = shape;
    if (this.shape.getMotions().size() > 0) {
      updateFields();
    }
  }

  @Override
  public void addMotion(IMotion motion) {
    shape.addMotion(new MotionImpl(motion.getBeginTime(), motion.getEndTime(), null,
        new KeyframeImpl(motion.getBeginTime(),
            new Position2D(motion.getNewX(), motion.getNewY()),
            new Size2D(motion.getNewHeight(), motion.getNewLength()),
            new TextureImpl(motion.getColor().getR(), motion.getColor().getG(),
                motion.getColor().getB(), 1)),
        new KeyframeImpl(motion.getEndTime(),
            new Position2D(motion.getNewX(), motion.getNewY()),
            new Size2D(motion.getNewHeight(), motion.getNewLength()),
            new TextureImpl(motion.getColor().getR(), motion.getColor().getG(),
                motion.getColor().getB(), 1))));
    updateFields();
  }

  @Override
  public void addMotions(IMotion... motions) {
    for (IMotion motion : motions) {
      addMotion(motion);
    }
    updateFields();
  }

  @Override
  public String getID() {
    return shape.getName();
  }

  @Override
  public ArrayList<IMotion> getMotions() {
    ArrayList<IMotion> iMotions = new ArrayList<>();
    for (cs3500.animator.model.Motion motion : shape.getMotions()) {
      iMotions.add(new Motion(motion));
    }
    return iMotions;
  }

  @Override
  public IMotion getMotion(int time) {
    for (cs3500.animator.model.Motion motion : shape.getMotions()) {
      if (motion.getFirstTick() <= time && time <= motion.getEndFrame().getTick()) {
        return new Motion(motion);
      }
    }
    /* The Javadoc didn't specify that this should throw an exception if there is no appropriate
       IMotion, so I'm just returning null.
     */
    return null;
  }

  @Override
  public String shapeTypeToString() {
    return shape.getShape();
  }

  @Override
  public String describe() {
    return shape.toFile();
  }

  @Override
  public void deleteKeyFrame(int keyFrameIndex) {
    Animation anim = CreateTempBuilder();
    anim.getBuilder().deleteKeyFrame(shape.getName(), shape.getFirstTick());
    this.shape = anim.getShapes().get(shape.getName());
    updateFields();
  }

  @Override
  public void editKeyFrame(int index, IMotion keyFrame) {
    Animation anim = CreateTempBuilder();
    anim.getBuilder().setKeyFrame(shape.getName(), t, keyFrame.getNewX(),
        keyFrame.getNewY(), keyFrame.getNewLength(), keyFrame.getNewHeight(),
        keyFrame.getColor().getR(), keyFrame.getColor().getG(), keyFrame.getColor().getB());
    this.shape = anim.getShapes().get(shape.getName());
    updateFields();
  }

  @Override
  public IMotion insertKeyFrame(int tick) {
    Animation anim = CreateTempBuilder();
    anim.getBuilder().addKeyframe(shape.getName(), tick);
    this.shape = anim.getShapes().get(shape.getName());
    updateFields();
    for (cs3500.animator.model.Motion motion : this.shape.getMotions()) {
      if (motion.getFirstTick() == tick || motion.getEndFrame().getTick() == tick) {
        return new Motion(motion);
      }
    }
    /* The Javadoc didn't specify that this should throw an exception if there is no appropriate
       IMotion, so I'm just returning null.
     */
    return null;
  }

  @Override
  public IShape getFrame(double tick) {
    int inttick = ((int) tick);
    this.x = ((int) shape.getPositionAt(inttick).getX());
    this.y = ((int) shape.getPositionAt(inttick).getY());
    this.w = ((int) shape.getSizeAt(inttick).getWidth());
    this.h = ((int) shape.getSizeAt(inttick).getHeight());
    this.r = ((int) shape.getColorAt(inttick).getRed());
    this.g = ((int) shape.getColorAt(inttick).getGreen());
    this.b = ((int) shape.getColorAt(inttick).getBlue());
    return this;
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.valueOf(this.shape.getShape().toUpperCase());
  }

  @Override
  public int getX() {
    return ((int) this.x);
  }

  @Override
  public int getY() {
    return ((int) this.y);
  }

  @Override
  public int getWidth() {
    return ((int) this.w);
  }

  @Override
  public int getHeight() {
    return ((int) this.h);
  }

  @Override
  public Color getColor() {
    return new Color(((int) this.r), ((int) this.g), ((int) this.b));
  }

  /**
   * Creates a temporary builder to enable changes to individual Shapes, since our original code
   * only supported keyframe-altering functions on the Animation level.
   *
   * @returns the animation with the shape, and the shape's motions, added to it.
   */
  private Animation CreateTempBuilder() {
    Animation anim = new AnimationImpl();
    anim.addShape(shape.getName(), shape.getShape());
    for (cs3500.animator.model.Motion motion : shape.getMotions()) {
      anim.getShapes().get(shape.getName()).addMotion(motion);
    }
    return anim;
  }

  /**
   * Updates the shape's fields to be accurate to the new first shape, just in case its first
   * motion has been changed.
   */
  private void updateFields() {
    this.t = shape.getFirstTick();
    this.x = shape.getFirstX();
    this.y = shape.getFirstY();
    this.w = shape.getFirstWidth();
    this.h = shape.getFirstHeight();
    this.r = shape.getFirstRed();
    this.g = shape.getFirstBlue();
    this.b = shape.getFirstGreen();
  }
}