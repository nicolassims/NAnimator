package cs3500.animator.view;

import cs3500.animator.EasyAnimator;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class contains the tests for the SVGViewImpl, as well as one general test to determine that
 * an animator given a nonexistent view will throw an exception.
 */
public class SVGViewImplTest {

  /**
   * A test to determine if the animator can properly run an svg view.
   */
  @Test
  public void SVGView() {
    String string = "-in resources/toh-3.txt -view svg -out resources/toh-3.svg";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/toh-3.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      fail("Threw an exception");
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/tohExample-3.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      fail("Threw an exception");
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  /**
   * A test to determine if the animator can properly run an svg view when speed is involved.
   */
  @Test
  public void SVGViewWithSpeed() {
    String string = "-view svg -in resources/toh-8.txt -out resources/new-toh-at-20.svg -speed 20";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/new-toh-at-20.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      fail("Threw an exception");
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/toh-at-20.svg"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      fail("Threw an exception");
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  /**
   * A test to determine that an animator given a non-existent view will throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentView() {
    String string = "-in toh-3.txt -view pop";
    EasyAnimator.main(string.split(" "));
  }

  /**
   * A test to determine that an svg view given a non-existent file will throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentFile() {
    String string = "-in oingoboingo.txt -view svg";
    EasyAnimator.main(string.split(" "));
  }
}