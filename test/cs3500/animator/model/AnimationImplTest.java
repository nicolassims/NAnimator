package cs3500.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the tests for the AnimationImpl.
 */
public class AnimationImplTest {

  Animation exampleModel;

  @Before
  public void setUp() throws Exception {
    exampleModel = new AnimationImpl();
    exampleModel.addShape("R", "rectangle");
    exampleModel.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    exampleModel.addRotationless2DMotion("R",
        10, 200, 200, 50, 100, 255, 0, 0,
        50, 300, 300, 50, 100, 255, 0, 0);
    exampleModel.addRotationless2DMotion("R",
        50, 300, 300, 50, 100, 255, 0, 0,
        51, 300, 300, 50, 100, 255, 0, 0);
    exampleModel.addRotationless2DMotion("R",
        51, 300, 300, 50, 100, 255, 0, 0,
        70, 300, 300, 25, 100, 255, 0, 0);
    exampleModel.addRotationless2DMotion("R",
        70, 300, 300, 25, 100, 255, 0, 0,
        100, 200, 200, 25, 100, 255, 0, 0);

    exampleModel.addShape("C", "ellipse");
    exampleModel.addRotationless2DMotion("C",
        6, 440, 70, 120, 60, 0, 0, 255,
        20, 440, 70, 120, 60, 0, 0, 255);
    exampleModel.addRotationless2DMotion("C",
        20, 440, 70, 120, 60, 0, 0, 255,
        50, 440, 250, 120, 60, 0, 0, 255);
    exampleModel.addRotationless2DMotion("C",
        50, 440, 250, 120, 60, 0, 0, 255,
        70, 440, 370, 120, 60, 0, 170, 85);
    exampleModel.addRotationless2DMotion("C",
        70, 440, 370, 120, 60, 0, 170, 85,
        80, 440, 370, 120, 60, 0, 255, 0);
    exampleModel.addRotationless2DMotion("C",
        80, 440, 370, 120, 60, 0, 255, 0,
        100, 440, 370, 120, 60, 0, 255, 0);
  }

  /**
   * Tests that add shape works correctly.
   */
  @Test
  public void addShape() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    assertEquals("shape R rectangle", modelWithOneShape.toFile());
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given string for the shape's
   * name is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExceptionForNullShapeName() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape(null, "rectangle");
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given string for the shape's
   * type is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExceptionForNullShapeType() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", null);
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given shape's type does not
   * match any of the currently supported shapes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExceptionForUnsupportedShapeType() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "dodecahedron");
  }

  /**
   * Tests that add motion works correctly.
   */
  @Test
  public void addMotion() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    assertEquals("shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0",
        modelWithOneShape.toFile());
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if a shape with the given name does
   * not exist yet.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForNonexistentShape() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("F",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if the start tick of a motion is less
   * than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForTicksLessThanZero() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        -1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if the second keyframe of a motion
   * starting tick is not greater than the one of the previous keyframe.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForInvalidMotionByKeyframeOrder() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        1, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if an attempt to add a new motion
   * overlaps with an existent motion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForOverlappingMotions() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    modelWithOneShape.addRotationless2DMotion("R",
        9, 200, 200, 50, 100, 255, 0, 0,
        13, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if an attempt to add a new motion that
   * creates a gap on the shape's animation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForGaps() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    modelWithOneShape.addRotationless2DMotion("R",
        11, 200, 200, 50, 100, 255, 0, 0,
        15, 200, 200, 50, 100, 255, 0, 0);
  }


  /**
   * Tests that the motion can completely replicate the following example animation:
   * https://course.ccs.neu.edu/cs3500/smalldemo.gif.
   */
  @Test
  public void toFile() {
    assertEquals("shape R rectangle\n"
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
   * Tests that totalDuration returns zero when no shapes have been added.
   */
  @Test
  public void totalDurationEmptyCase() {
    Animation modelWithNoShape = new AnimationImpl();
    assertEquals(0,
        modelWithNoShape.totalDuration());
  }

  /**
   * Tests that totalDuration returns zero when no shapes have been added.
   */
  @Test
  public void totalDuration() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    assertEquals(10,
        modelWithOneShape.totalDuration());
  }

}