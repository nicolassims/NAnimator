package model;

import model.qualities.color.TextureImpl;
import model.qualities.dimensions.Size2D;
import model.qualities.positions.Position2D;
import org.junit.Test;

/**
 * This class contains the tests for the KeyframeImpl.
 */
public class KeyframeImplTest {

  /**
   * Tests that keyframeImpl throws an IllegalArgumentException if its texture is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void keyframeExceptionNullTexture() {
    Keyframe key = new KeyframeImpl(0, new Position2D(0, 0), new Size2D(0, 0), null);
  }

  /**
   * Tests that keyframeImpl throws an IllegalArgumentException if its texture is size is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void keyframeExceptionNullSize() {
    Keyframe key = new KeyframeImpl(0, new Position2D(0, 0), null,
        new TextureImpl(0, 0, 0, 0));
  }

  /**
   * Tests that keyframeImpl throws an IllegalArgumentException if its position is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void keyframeExceptionNullPosition() {
    Keyframe key = new KeyframeImpl(0, null, new Size2D(0, 0),
        new TextureImpl(0, 0, 0, 0));
  }

  /**
   * Tests that keyframeImpl throws an IllegalArgumentException if its position is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void keyframeExceptionNegativeTick() {
    Keyframe key = new KeyframeImpl(-1, new Position2D(0, 0), new Size2D(0, 0),
        new TextureImpl(0, 0, 0, 0));
  }
}
