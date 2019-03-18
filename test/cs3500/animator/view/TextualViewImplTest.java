package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import cs3500.animator.EasyAnimator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

public class TextualViewImplTest extends AbstractViewTest {

  /**
   * A test to determine if the animator can properly run a textual view.
   */
  @Test
  public void textView() {
    String string = "-in big-bang-big-crunch.txt -view text -out bang.txt";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bang.txt"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bangExample.txt"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  /**
   * A test to determine if the animator can properly run a text view when speed is involved.
   * Also asserting that speed has no effect on a textual view.
   */
  @Test
  public void textViewWithSpeed() {
    String string = "-in big-bang-big-crunch.txt -view text -out bang.txt -speed 20";
    EasyAnimator.main(string.split(" "));
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bang.txt"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    StringBuilder contentBuilder2 = new StringBuilder();
    try (Stream<String> stream = Files
        .lines(Paths.get("resources/bangExample.txt"), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder2.append(s).append("\n"));
    } catch (IOException e) {
      assertEquals(true, false);
    }
    assertEquals(contentBuilder2.toString(), contentBuilder.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonExistentFile() {
    String string = "-in oingoboingo.txt -view text";
    EasyAnimator.main(string.split(" "));
  }
}