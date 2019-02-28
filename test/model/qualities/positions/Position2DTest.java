package model.qualities.positions;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Position2DTest {

  Position position;
  Position otherPosition;


  /**
   * Instantiates the test subjects.
   */
  @Before
  public void setup() {
    position = new Position2D(2, 1);
    otherPosition = new Position2D(1, 1);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getX() {
    assertEquals(2, this.position.getX(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getY() {
    assertEquals(1, this.position.getY(), 0.0001);
  }

  /**
   * Tests the getter.
   */
  @Test
  public void getZ() {
    assertEquals(0, this.position.getZ(), 0.0001);
  }

  /**
   * Tests if the get qualities method works correctly.
   */
  @Test
  public void getQualities() {
    assertEquals("[Position:Position2D (x: 2.0, y: 1.0)]", this.position.getQualities());
  }

  /**
   * Tests if positions are added correctly.
   */
  @Test
  public void addTogether() {
    Position addedPosition = (Position) position.addTogether(otherPosition);
    assertEquals("[Position:Position2D (x: 3.0, y: 2.0)]", addedPosition.getQualities());
  }

  /**
   * Tests if positions are subtracted correctly.
   */
  @Test
  public void getDifference() {
    Position subtractedPosition = (Position) position.getDifference(otherPosition);
    assertEquals("[Position:Position2D (x: -1.0, y: 0.0)]",
        subtractedPosition.getQualities());
  }

  /**
   * Tests if the positions are scaled down correctly.
   */
  @Test
  public void divideBy() {
    Position subtractedPosition = (Position) position.divideBy(2);
    assertEquals("[Position:Position2D (x: 1.0, y: 0.5)]",
        subtractedPosition.getQualities());
  }

  /**
   * Tests if the positions are scaled up correctly.
   */
  @Test
  public void multiplyBy() {
    Position subtractedPosition = (Position) position.multiplyBy(2);
    assertEquals("[Position:Position2D (x: 4.0, y: 2.0)]",
        subtractedPosition.getQualities());
  }
}