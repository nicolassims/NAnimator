package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.AnimationImplTest;
import org.junit.Test;

/**
 * This class contains the tests for the new keyframe methods--setkeyframe, addkeyframe, and delete
 * keyframe.
 */
public class NewKeyframeMethodTests extends AnimationImplTest {

  /**
   * A test to determine if the first keyframe can be set properly.
   */
  @Test
  public void setFirstKeyframe() {
    exampleModel.getBuilder().setKeyFrame("R", 1, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 255 255 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if the last keyframe can be set properly.
   */
  @Test
  public void setLastKeyframe() {
    exampleModel.getBuilder().setKeyFrame("R", 100, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 50 100 255 255 255\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if two simultaneous--and equal--keyframes can be set to be something else.
   */
  @Test
  public void setTwoDifferentAtOnce() {
    exampleModel.getBuilder().setKeyFrame("R", 10, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 255 255\n"
        + "motion R 10 200 200 50 100 255 255 255 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }


  /**
   * A test to make sure that a keyframe can't be set in a nonexistent shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setNonShape() {
    exampleModel.getBuilder().setKeyFrame("B", 10, 200, 200, 50, 100, 255, 255, 255);
  }

  /**
   * A test to make sure that a nonexistent keyframe can't be set.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setNonTime() {
    exampleModel.getBuilder().setKeyFrame("R", 11, 200, 200, 50, 100, 255, 255, 255);
  }

  /**
   * A test to determine if a keyframe can be added to a shape with no motions.
   */
  @Test
  public void createFirstKeyframe() {
    Animation model = new AnimationImpl();
    model.addShape("oingo", "ellipse");
    assertEquals("shape oingo ellipse", model.toFile());
    model.getBuilder().addKeyframe("oingo", 0, 200, 200, 50, 100, 255, 255, 255);
    assertEquals("shape oingo ellipse\n"
        + "motion oingo 0 200 200 50 100 255 255 255 0 200 200 50 100 255 255 255", model.toFile());
  }

  /**
   * A test to determine if a keyframe can be added before all others.
   */
  @Test
  public void addFirstKeyframe() {
    exampleModel.getBuilder().addKeyframe("R", 0, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 0 200 200 50 100 255 255 255 1 200 200 50 100 255 0 0\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if a keyframe can be added after all others.
   */
  @Test
  public void addLastKeyframe() {
    exampleModel.getBuilder().addKeyframe("R", 120, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "motion R 100 200 200 25 100 255 0 0 120 200 200 50 100 255 255 255\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if a keyframe can be added in the middle of an existing motion.
   */
  @Test
  public void addKeyframeInMiddleOfExistingMotion() {
    exampleModel.getBuilder().addKeyframe("R", 25, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 25 200 200 50 100 255 255 255\n"
        + "motion R 25 200 200 50 100 255 255 255 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if a keyframe can be added between two existing motions.
   */
  @Test
  public void addKeyframeBetweenExistingMotions() {
    exampleModel.getBuilder().addKeyframe("R", 50, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 200 200 50 100 255 255 255\n"
        + "motion R 50 200 200 50 100 255 255 255 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }


  /**
   * A test to make sure that a keyframe can't be set in a nonexistent shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNonShape() {
    exampleModel.getBuilder().addKeyframe("B", 10, 200, 200, 50, 100, 255, 255, 255);
  }

  /**
   * A test to determine if the first keyframe in a shape can be deleted.
   */
  @Test
  public void deleteFirstKeyframe() {
    exampleModel.getBuilder().deleteKeyFrame("R", 1);

    assertEquals("shape R rectangle\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if the last keyframe in a shape can be deleted.
   */
  @Test
  public void deleteLastKeyframe() {
    exampleModel.getBuilder().deleteKeyFrame("R", 100);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if a keyframe in the middle of the shape's motions can be deleted.
   */
  @Test
  public void deleteKeyframeInMiddleOfExistingMotion() {
    exampleModel.getBuilder().deleteKeyFrame("R", 50);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to determine if a keyframe can be added between two existing motions.
   */
  @Test
  public void deleteKeyframeBetweenExistingMotions() {
    exampleModel.getBuilder().addKeyframe("R", 50, 200, 200, 50, 100, 255, 255, 255);

    assertEquals("shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 50 200 200 50 100 255 255 255\n"
        + "motion R 50 200 200 50 100 255 255 255 51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", exampleModel.toFile());
  }

  /**
   * A test to make sure that a keyframe can't be deleted from a nonexistent shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void deleteNonShape() {
    exampleModel.getBuilder().deleteKeyFrame("B", 10);
  }

  /**
   * A test to make sure that a keyframe can't be deleted from a nonexistent time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void deleteNonTime() {
    exampleModel.getBuilder().deleteKeyFrame("R", 44);
  }
}