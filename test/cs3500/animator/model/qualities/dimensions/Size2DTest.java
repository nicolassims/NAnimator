package cs3500.animator.model.qualities.dimensions;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.positions.Position2D;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains the tests for the Size2D.
 */
public class Size2DTest {

  private Size dimensions;

  /**
   * Instantiates the test subjects.
   */
  @Before
  public void setUp() throws Exception {
    dimensions = new Size2D(3, 4);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getWidth() {
    assertEquals(3, this.dimensions.getWidth(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getHeight() {
    assertEquals(4, this.dimensions.getHeight(), 0.0001);
  }

  /**
   * Tests if the get qualities method works correctly.
   */
  @Test
  public void getQualities() {
    assertEquals("[Size:Size2D (width: 3.0, height: 4.0)]",
        this.dimensions.getQualities());
  }

  /**
   * Tests if the to file method works correctly.
   */
  @Test
  public void toFile() {
    assertEquals("3 4",
        this.dimensions.toFile());
  }

  /**
   * Tests if sizes are added correctly.
   */
  @Test
  public void addTogether() {
    Size dimensions1 = (Size) new Size2D(1, 12)
        .addTogether(new Size2D(4, 4));
    assertEquals("[Size:Size2D (width: 5.0, height: 16.0)]",
        dimensions1.getQualities());
  }

  /**
   * Tests if sizes are subtracted correctly.
   */
  @Test
  public void getDifference() {
    Size dimensions1 = (Size) new Size2D(1, 12)
        .getDifference(new Size2D(4, 4));
    assertEquals("[Size:Size2D (width: 3.0, height: -8.0)]",
        dimensions1.getQualities());
  }

  /**
   * Tests if the sizes are scaled down correctly.
   */
  @Test
  public void divideBy() {
    Size dimensions1 = (Size) new Size2D(1, 12)
        .divideBy(5);
    assertEquals("[Size:Size2D (width: 0.2, height: 2.4)]",
        dimensions1.getQualities());
  }

  /**
   * Tests if the sizes are scaled up correctly.
   */
  @Test
  public void multiplyBy() {
    Size dimensions1 = (Size) new Size2D(1, 12)
        .multiplyBy(5);
    assertEquals("[Size:Size2D (width: 5.0, height: 60.0)]",
        dimensions1.getQualities());
  }

  /**
   * Tests if two different qualities throw an exception when trying to add them together.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addTogetherTwoDifferent() {
    dimensions.addTogether(new Position2D(0, 0));
  }

  /**
   * Tests if two different qualities throw an exception when trying to get their difference.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getDifferenceTwoDifferent() {
    dimensions.getDifference(new TextureImpl(0, 0, 0, 0));
  }
}