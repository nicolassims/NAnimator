package cs3500.animator.model;

import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains the tests for the MotionImpl.
 */
public class MotionImplTest {

    Keyframe key1;
    Keyframe key2;

    /**
     * Sets up the MotionImpltests.
     */
    @Before
    public void setUp() throws Exception {
        key1 = new KeyframeImpl(0, new Position2D(0, 0),
                new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
        key2 = new KeyframeImpl(1, new Position2D(0, 0),
                new Size2D(0, 0), new TextureImpl(0, 0, 0, 0));
    }

    /**
     * Tests that MotionImpl throws an IllegalArgumentException if its starttick is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void motionExceptionNegativeStartick() {
        Motion mot = new MotionImpl(-1, 1,
                new ShapeImpl(Shapes.ellipse, "C"), key1, key2);
    }

    /**
     * Tests that MotionImpl throws an IllegalArgumentException if starttick is less than endtick.
     */
    @Test(expected = IllegalArgumentException.class)
    public void motionExceptionBackwardsTicks() {
        Motion mot = new MotionImpl(2, 1,
                new ShapeImpl(Shapes.ellipse, "C"), key1, key2);
    }

    /**
     * Tests that MotionImpl throws an IllegalArgumentException if parent is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void motionExceptionNullPosition() {
        Motion mot = new MotionImpl(0, 1, null, key1, key2);
    }

    /**
     * Tests that keyframeImpl throws an IllegalArgumentException if startFrame is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void motionExceptionNullStartframe() {
        Motion mot = new MotionImpl(0, 1,
                new ShapeImpl(Shapes.ellipse, "C"), null, key2);
    }

    /**
     * Tests that keyframeImpl throws an IllegalArgumentException if endFrame is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void motionExceptionNullEndframe() {
        Motion mot = new MotionImpl(0, 1,
                new ShapeImpl(Shapes.ellipse, "C"), key1, null);
    }
}
