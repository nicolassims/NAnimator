package qualities.dimensions;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Size2DTest {

  Dimensions dimensions;

  @Before
  public void setUp() throws Exception {
    dimensions = new Size2D(3, 4);
  }

  @Test
  public void getWidth() {
    assertEquals(3, this.dimensions.getWidth(), 0.0001);
  }

  @Test
  public void getHeight() {
    assertEquals(4, this.dimensions.getHeight(), 0.0001);
  }

  @Test
  public void getQualities() {
    assertEquals("[Dimensions:Size2D (width: 3.0, height: 4.0)]",
        this.dimensions.getQualities());
  }

  @Test
  public void addTogether() {
    Dimensions dimensions1 = (Dimensions) new Size2D(1, 12)
        .addTogether(new Size2D(4, 4));
    assertEquals("[Dimensions:Size2D (width: 5.0, height: 16.0)]",
        dimensions1.getQualities());
  }

  @Test
  public void getDifference() {
    Dimensions dimensions1 = (Dimensions) new Size2D(1, 12)
        .getDifference(new Size2D(4, 4));
    assertEquals("[Dimensions:Size2D (width: 3.0, height: -8.0)]",
        dimensions1.getQualities());
  }

  @Test
  public void divideBy() {
    Dimensions dimensions1 = (Dimensions) new Size2D(1, 12)
        .divideBy(5);
    assertEquals("[Dimensions:Size2D (width: 0.2, height: 2.4)]",
        dimensions1.getQualities());
  }

  @Test
  public void multiplyBy() {
    Dimensions dimensions1 = (Dimensions) new Size2D(1, 12)
        .multiplyBy(5);
    assertEquals("[Dimensions:Size2D (width: 5.0, height: 60.0)]",
        dimensions1.getQualities());
  }
}