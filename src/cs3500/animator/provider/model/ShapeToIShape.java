package cs3500.animator.provider.model;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.MotionImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import java.util.ArrayList;

public class ShapeToIShape implements IShape {

  private Shape shape;

  ShapeToIShape(Shape shape) {
    this.shape = shape;
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
  }

  @Override
  public void addMotions(IMotion... motions) {
    for (IMotion motion : motions) {
      addMotion(motion);
    }
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
  }//FIXED THIS

  @Override
  public void editKeyFrame(int index, IMotion keyFrame) {

  }

  @Override
  public IMotion insertKeyFrame(int tick) {
    return null;
  }

  @Override
  public IShape getFrame(double tick) {
    return null;
  }

  @Override
  public ShapeType getShapeType() {
    return null;
  }

  @Override
  public int getX() {
    return 0;
  }

  @Override
  public int getY() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public Color getColor() {
    return null;
  }

  private Animation CreateTempBuilder() {
    Animation anim = new AnimationImpl();
    anim.addShape(shape.getName(), shape.getShape());
    for (cs3500.animator.model.Motion motion : shape.getMotions()) {
      anim.getShapes().get(shape.getName()).addMotion(motion);
    }
    return anim;
  }
}
