package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import cs3500.animator.EasyAnimator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * This class tests the methods of the SVGViewImpl. It also contains a test to determine that an
 * instance of main given a nonexistent view will throw an exception.
 */
public class SVGViewImplTest {

  /**
   * A test to determine if the animator can properly run an svg view.
   */
  @Test
  public void SVGView() {
    String string = "-in big-bang-big-crunch.txt -view svg -out bang.svg";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bang.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bangExample.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  /**
   * A test to determine if the animator can properly run an svg view when speed is involved.
   */
  @Test
  public void SVGViewWithSpeed() {
    String string = "-view svg -in toh-12.txt -out new-toh-at-20.svg -speed 20";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/new-toh-at-20.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/toh-at-20.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  /**
   * Test to determine that an instance of main given a nonexistent view will throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentView() {
    String string = "-in big-bang-big-crunch.txt -view pop";
    EasyAnimator.main(string.split(" "));
  }

  /**
   * Test to determine that textual view given a nonexistent file will throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentFile() {
    String string = "-in oingoboingo.txt -view svg";
    EasyAnimator.main(string.split(" "));
  }
}