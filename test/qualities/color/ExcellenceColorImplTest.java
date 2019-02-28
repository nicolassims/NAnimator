package qualities.color;

import java.awt.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TextureImplTest {

  Color mycolor;

  @Before
  public void setup() {
    mycolor = new TextureImpl(25, 83, 98, 234);
  }

  @Test
  public void getAsJavaAwtColor() {
    assertEquals(new Color(25, 83, 98, 234), mycolor.getAsJavaAwtColor());
  }

  @Test
  public void getRed() {
    assertEquals(25, mycolor.getRed(), 0.0001);
  }

  @Test
  public void getGreen() {
    assertEquals(83, mycolor.getGreen(), 0.0001);
  }

  @Test
  public void getBlue() {
    assertEquals(98, mycolor.getBlue(), 0.0001);
  }

  @Test
  public void getAlpha() {
    assertEquals(234, mycolor.getAlpha(), 0.0001);
  }

  @Test
  public void addTogether() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 125.0, green: 233.0, blue: 298.0, alpha: 484.0)]",
        mycolor.addTogether(otherColor).getQualities());
  }

  @Test
  public void getQualities() {
    assertEquals("[Texture:Texture (red: 25.0, green: 83.0, blue: 98.0, alpha: 234.0)]",
        mycolor.getQualities());
  }

  @Test
  public void getDifference() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 75.0, green: 67.0, blue: 102.0, alpha: 16.0)]",
        mycolor.getDifference(otherColor).getQualities());
  }

  @Test
  public void divideBy() {
    Texture otherColor =
        new TextureImpl(100, 150, 200, 250);
    assertEquals("[Texture:Texture (red: 20.0, green: 30.0, blue: 40.0, alpha: 50.0)]",
        otherColor.divideBy(5).getQualities());
  }

  @Test
  public void multiplyBy() {
    assertEquals("[Texture:Texture (red: 125.0, green: 415.0, blue: 490.0, alpha: 1170.0)]",
        mycolor.multiplyBy(5).getQualities());
  }
}