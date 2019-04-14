package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import org.junit.Test;

/**
 * This class contains the tests for the ShapeImpl.
 */
public class ShapeImplTest {

  @Test
  public void totalDuration() {
    Shape shappy = new ShapeImpl(Shapes.ELLIPSE, "C");
    Keyframe key1 = new KeyframeImpl(0, new Position2D(0, 0),
        new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
    Keyframe key2 = new KeyframeImpl(1, new Position2D(0, 0),
        new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
    Keyframe key3 = new KeyframeImpl(1, new Position2D(0, 0),
        new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
    Keyframe key4 = new KeyframeImpl(5, new Position2D(0, 0),
        new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
    shappy.addMotion(new MotionImpl(0, 1,
        new ShapeImpl(Shapes.ELLIPSE, "C"), key1, key2));
    shappy.addMotion(new MotionImpl(1, 5,
        new ShapeImpl(Shapes.ELLIPSE, "C"), key3, key4));

    assertEquals(5, shappy.totalDuration());
  }
}