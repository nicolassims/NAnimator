package cs3500.animator.model.qualities.color;

import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the tests for the TextureImpl.
 */
public class TextureImplTest {

  private Texture myColor;

  /**
   * Instantiates the test subjects.
   */
  @Before
  public void setUp() throws Exception {
    myColor = new TextureImpl(25, 83, 98, 234);
  }

  /**
   * Tests whether it correctly creates a Java AWT Color that represents this Texture.
   */
  @Test
  public void getAsJavaAwtColor() {
    assertEquals(new Color(25, 83, 98, 234), myColor.getAsJavaAwtColor());
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getRed() {
    assertEquals(25, myColor.getRed(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getGreen() {
    assertEquals(83, myColor.getGreen(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getBlue() {
    assertEquals(98, myColor.getBlue(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getAlpha() {
    assertEquals(234, myColor.getAlpha(), 0.0001);
  }

  /**
   * Tests if colors are added correctly.
   */
  @Test
  public void addTogether() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 125.0, green: 233.0, blue: 298.0, alpha: 484.0)]",
        myColor.addTogether(otherColor).getQualities());
  }

  /**
   * Tests if the to file method works correctly.
   */
  @Test
  public void toFile() {
    assertEquals("25 83 98",
        myColor.toFile());
  }

  /**
   * Tests if the get qualities method works correctly.
   */
  @Test
  public void getQualities() {
    assertEquals("[Texture:Texture (red: 25.0, green: 83.0, blue: 98.0, alpha: 234.0)]",
        myColor.getQualities());
  }

  /**
   * Tests if colors are subtracted correctly.
   */
  @Test
  public void getDifference() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 75.0, green: 67.0, blue: 102.0, alpha: 16.0)]",
        myColor.getDifference(otherColor).getQualities());
  }

  /**
   * Tests if the colors are scaled down correctly.
   */
  @Test
  public void divideBy() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 20.0, green: 30.0, blue: 40.0, alpha: 50.0)]",
        otherColor.divideBy(5).getQualities());
  }

  /**
   * Tests if the colors are scaled up correctly.
   */
  @Test
  public void multiplyBy() {
    assertEquals("[Texture:Texture (red: 125.0, green: 415.0, blue: 490.0, alpha: 1170.0)]",
        myColor.multiplyBy(5).getQualities());
  }

  /**
   * Tests if two different qualities throw an exception when trying to add them together.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addTogetherTwoDifferent() {
    myColor.addTogether(new Position2D(0, 0));
  }

  /**
   * Tests if two different qualities throw an exception when trying to get their difference.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getDifferenceTwoDifferent() {
    myColor.getDifference(new Size2D(0, 0));
  }
}