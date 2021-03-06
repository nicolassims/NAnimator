package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class contains the tests for the AnimationImpl.
 */
public class AnimationImplTest {

  protected Animation exampleModel;

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
   * Tests that totalDuration returns a correct value in an Animation with one shape.
   */
  @Test
  public void totalDuration() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    assertEquals(10, modelWithOneShape.totalDuration());
  }

  /**
   * Tests that totalDuration returns a correct value in an Animation with multiple shapes.
   */
  @Test
  public void totalDuration2() {
    Animation modelWithTwoShapes = new AnimationImpl();
    modelWithTwoShapes.addShape("R", "rectangle");
    modelWithTwoShapes.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
    modelWithTwoShapes.addShape("E", "ellipse");
    modelWithTwoShapes.addRotationless2DMotion("E",
        1, 200, 200, 50, 100, 255, 0, 0,
        12, 200, 200, 50, 100, 255, 0, 0);
    assertEquals(12, modelWithTwoShapes.totalDuration());
  }

  @Test
  public void testGetTextureAtBeginningOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("C");
    assertEquals("0 0 255", ellipse.getColorAt(50).toFile());
  }

  @Test
  public void testGetTextureAtEndingOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("C");
    assertEquals("0 170 85", ellipse.getColorAt(70).toFile());
  }

  @Test
  public void testGetTextureMiddleOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("C");
    assertEquals("0 89 165", ellipse.getColorAt(60).toFile());
  }

  @Test
  public void testGetTextureAtFirstTick() {
    Shape ellipse = exampleModel.getShapes().get("C");
    assertEquals("0 0 255", ellipse.getColorAt(6).toFile());
  }

  @Test
  public void testGetPositionAtBeginningOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("200 200", ellipse.getPositionAt(10).toFile());
  }

  @Test
  public void testGetPositionAtEndingOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("300 300", ellipse.getPositionAt(50).toFile());
  }

  @Test
  public void testGetPositionMiddleOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("251 251", ellipse.getPositionAt(30).toFile());
  }

  @Test
  public void testGetPositionAtFirstTick() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("200 200", ellipse.getPositionAt(1).toFile());
  }

  @Test
  public void testGetSizeAtBeginningOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("50 100", ellipse.getSizeAt(51).toFile());
  }

  @Test
  public void testGetSizeAtEndingOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("25 100", ellipse.getSizeAt(70).toFile());
  }

  @Test
  public void testGetSizeMiddleOfMotion() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("37 100", ellipse.getSizeAt(60).toFile());
  }

  @Test
  public void testGetSizeAtFirstTick() {
    Shape ellipse = exampleModel.getShapes().get("R");
    assertEquals("50 100", ellipse.getSizeAt(1).toFile());
  }
}