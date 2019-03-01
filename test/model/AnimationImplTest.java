package model;

import model.qualities.color.TextureImpl;
import model.qualities.dimensions.Size2D;
import model.qualities.positions.Position2D;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AnimationImplTest {

  Animation exampleModel;

  @Before
  public void setUp() throws Exception {
    exampleModel = new AnimationImpl();
    exampleModel.addShape("R", "rectangle");

    Keyframe keyframe1 = new KeyframeImpl(0, new Position2D(200, 200), new Size2D(50, 100),
        new TextureImpl(255, 0, 0, 255));
    Keyframe keyframe2 = new KeyframeImpl(100, new Position2D(200, 200), new Size2D(50, 100),
        new TextureImpl(255, 0, 0, 255));
    exampleModel.addMotion("R", keyframe1, keyframe2);

    exampleModel.addRotationless2DMotion("R",
        1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add shape works correctly
   */
  @Test
  public void addShape() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    assertEquals("shape R rectangle", modelWithOneShape.toFile());
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given string for the shape's
   * name is null
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExceptionForNullShapeName() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape(null, "rectangle");
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given string for the shape's
   * type is null
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExceptionForNullShapeType() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", null);
  }

  /**
   * Tests that add shape throws an IllegalArgumentException if the given shape's type does not
   * match any of the currently supported shapes
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
    modelWithOneShape.addRotationless2DMotion("F",
        -1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
  }

  /**
   * Tests that add motion throws an IllegalArgumentException if the start tick of a motion is less
   * than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExceptionForInvalidMotionByKeyframeOrder() {
    Animation modelWithOneShape = new AnimationImpl();
    modelWithOneShape.addShape("R", "rectangle");
    modelWithOneShape.addRotationless2DMotion("F",
        1, 200, 200, 50, 100, 255, 0, 0,
        1, 200, 200, 50, 100, 255, 0, 0);
  }


}