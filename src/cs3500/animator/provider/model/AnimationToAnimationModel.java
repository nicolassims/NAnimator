package cs3500.animator.provider.model;

import cs3500.animator.model.Animation;
import java.util.ArrayList;

public class AnimationToAnimationModel implements AnimationModel {

  Animation animation;

  AnimationToAnimationModel(Animation animation) {
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
    return animation.getShapes().get(id);
  }

  @Override
  public ArrayList<IMotion> getAllMotions() {
    return null;
  }

  @Override
  public ArrayList<IShape> getShapes() {
    return null;
  }

  @Override
  public ArrayList<IShape> getFrame(double tick) {
    return null;
  }

  @Override
  public void addMotion(String id, IMotion motion) {

  }

  @Override
  public int getBoundX() {
    return 0;
  }

  @Override
  public int getBoundY() {
    return 0;
  }

  @Override
  public int getBoundWidth() {
    return 0;
  }

  @Override
  public int getBoundHeight() {
    return 0;
  }

  @Override
  public void deleteKeyFrame(String shapeID, int keyFrameIndex) {

  }

  @Override
  public void editKeyFrame(String shapeID, int index, IMotion keyFrame) {

  }

  @Override
  public IMotion insertKeyFrame(String shapeID, int tick) {
    return null;
  }

  @Override
  public void deleteShape(String id) {

  }

  @Override
  public int getLastTick() {
    return 0;
  }
}
