package qualities.positions;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Position2DTest {

  Position position;
  Position otherPosition;


  @Before
  public void setup() {
    position = new Position2D(2, 1);
    otherPosition = new Position2D(1, 1);
  }

  @Test
  public void getX() {
    assertEquals(2, this.position.getX(), 0.0001);
  }

  @Test
  public void getY() {
    assertEquals(1, this.position.getY(), 0.0001);
  }

  @Test
  public void getZ() {
    assertEquals(0, this.position.getZ(), 0.0001);
  }

  @Test
  public void getQualities() {
    assertEquals("[Position:Position2D (x: 2.0, y: 1.0)]", this.position.getQualities());
  }

  @Test
  public void addTogether() {
    Position addedPosition = (Position) position.addTogether(otherPosition);
    assertEquals("[Position:Position2D (x: 3.0, y: 2.0)]", addedPosition.getQualities());
  }

  @Test
  public void getDifference() {
    Position subtractedPosition = (Position) position.getDifference(otherPosition);
    assertEquals("[Position:Position2D (x: -1.0, y: 0.0)]",
        subtractedPosition.getQualities());
  }

  @Test
  public void divideBy() {
    Position subtractedPosition = (Position) position.divideBy(2);
    assertEquals("[Position:Position2D (x: 1.0, y: 0.5)]",
        subtractedPosition.getQualities());
  }

  @Test
  public void multiplyBy() {
    Position subtractedPosition = (Position) position.multiplyBy(2);
    assertEquals("[Position:Position2D (x: 4.0, y: 2.0)]",
        subtractedPosition.getQualities());
  }
}