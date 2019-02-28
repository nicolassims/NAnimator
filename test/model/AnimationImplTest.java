package model;

import static org.junit.Assert.assertEquals;

import model.qualities.color.TextureImpl;
import model.qualities.dimensions.Size2D;
import model.qualities.positions.Position2D;
import org.junit.Before;
import org.junit.Test;

public class AnimationImplTest {

  Animation exampleModel;

  @Before
  public void setUp() throws Exception {
    exampleModel = new AnimationImpl();
    exampleModel.addShape("R", "rectangle");

    Keyframe keyframe1 = new KeyframeImpl(new Position2D(200, 200), new Size2D(50, 100),
        new TextureImpl(255, 0, 0, 255));
    Keyframe keyframe2 = new KeyframeImpl(new Position2D(200, 200), new Size2D(50, 100),
        new TextureImpl(255, 0, 0, 255));
    exampleModel.addMotion("R", keyframe1, keyframe2);

    exampleModel.addRotationless2DMotion("R", 1, 200, 200, 50, 100, 255, 0, 0,
        10, 200, 200, 50, 100, 255, 0, 0);
  }

  @Test
  public void addShape() {
  }

  @Test
  public void addMotion() {
  }

  @Test
  public void toFile() {
    assertEquals("shape R rectangle\n", exampleModel.toFile());
  }
}