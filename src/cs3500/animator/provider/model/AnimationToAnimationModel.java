package cs3500.animator.provider.model;

import cs3500.animator.model.Animation;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import java.util.ArrayList;

/**
 * Turns Nicolas & Forrest's implementation of an Animation into an implementer of the provided
 * AnimationModel interface.
 */
public class AnimationToAnimationModel implements AnimationModel {
  private Animation animation;

  /**
   * Simply takes an Animation, and sets this class' field to be that Animation.
   * @param animation The Animation that will be treated like an AnimationModel.
   */
  public AnimationToAnimationModel(Animation animation) {
    this.animation = animation;
  }

  @Override
  public String describe() {
    return animation.toFile();
  }

  @Override
  public void addShape(IShape shape) {
    animation.addShape(shape.getID(), shape.shapeTypeToString());
  }

  @Override
  public IShape getShape(String id) {//receiving shape, need to return IShape
    return new ShapeToIShape(animation.getShapes().get(id));
  }

  @Override
  public ArrayList<IMotion> getAllMotions() {
    throw new UnsupportedOperationException("This type of logic is not compatible with the system "
        + "we're using. In our system, Motions are stored as part of a shape, not an animation. "
        + "We could return the sum of each Shape's lists, but this method isn't even used, so "
        + "we'll pass.");
  }

  @Override
  public ArrayList<IShape> getShapes() {
    ArrayList<IShape> returnable = new ArrayList<>();
    for (Shape shape : animation.getShapes().values()) {
      returnable.add(new ShapeToIShape(shape));
    }
    return returnable;
  }

  @Override
  public ArrayList<IShape> getFrame(double tick) {
    throw new UnsupportedOperationException("This type of logic is not compatible with the system "
        + "we're using. We can't return a Shape's state at a single moment in time. We could"
        + "return the Keyframe for that moment, but that seems overly messy at this time.");
  }

  @Override
  public void addMotion(String id, IMotion motion) {
    animation.addMotion(id,
        new KeyframeImpl(motion.getBeginTime(), new Position2D(motion.getNewX(), motion.getNewY()),
            new Size2D(motion.getNewLength(), motion.getNewHeight()),
            new TextureImpl(motion.getColor().getR(), motion.getColor().getG(),
                motion.getColor().getB(), 1)),
        new KeyframeImpl(motion.getEndTime(), new Position2D(motion.getNewX(), motion.getNewY()),
            new Size2D(motion.getNewLength(), motion.getNewHeight()),
            new TextureImpl(motion.getColor().getR(), motion.getColor().getG(),
                motion.getColor().getB(), 1)));
  }

  @Override
  public int getBoundX() {
    return animation.getX();
  }

  @Override
  public int getBoundY() {
    return animation.getY();
  }

  @Override
  public int getBoundWidth() {
    return animation.getCanvasWidth();
  }

  @Override
  public int getBoundHeight() {
    return animation.getCanvasHeight();
  }

  @Override
  public void deleteKeyFrame(String shapeID, int keyFrameIndex) {
    animation.getBuilder().deleteKeyFrame(shapeID, keyFrameIndex);
  }

  @Override
  public void editKeyFrame(String shapeID, int index, IMotion keyFrame) {
    animation.getBuilder().setKeyFrame(shapeID, index, keyFrame.getNewX(), keyFrame.getNewY(),
        keyFrame.getNewLength(), keyFrame.getNewHeight(), keyFrame.getColor().getR(),
        keyFrame.getColor().getG(), keyFrame.getColor().getB());
  }

  @Override
  public IMotion insertKeyFrame(String shapeID, int tick) {
    animation.getBuilder().addKeyframe(shapeID, tick);
    for (cs3500.animator.model.Motion motion : animation.getShapes().get(shapeID).getMotions()) {
      if (motion.getFirstTick() <= tick && tick <= motion.getEndFrame().getTick()) {
        return new Motion(motion);
      }
    }
    return null;
  }

  @Override
  public void deleteShape(String id) {
    animation.removeShape(id);
  }

  @Override
  public int getLastTick() {
    return animation.totalDuration();
  }
}