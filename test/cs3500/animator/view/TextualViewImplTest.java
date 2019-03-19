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
 * This class contains the tests for the TextualViewImpl.
 */
public class TextualViewImplTest {

    /**
     * A test to determine if the animator can properly run a textual view.
     */
    @Test
    public void textView() {
        String string = "-in toh-3.txt -view text -out new-toh-3.txt";
        EasyAnimator.main(string.split(" "));
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files
                .lines(Paths.get("resources/new-toh-3.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            fail("Threw an exception");
        }
        StringBuilder contentBuilder2 = new StringBuilder();
        try (Stream<String> stream = Files
                .lines(Paths.get("resources/tohExample-3.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder2.append(s).append("\n"));
        } catch (IOException e) {
            fail("Threw an exception");
        }
        assertEquals(contentBuilder2.toString(), contentBuilder.toString());
    }

    /**
     * A test to determine if the animator can properly run a text view when speed is involved. Also
     * asserting that speed has no effect on a textual view.
     */
    @Test
    public void textViewWithSpeed() {
        String string = "-in toh-3.txt -view text -out new-toh-3.txt -speed 20";
        EasyAnimator.main(string.split(" "));
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files
                .lines(Paths.get("resources/new-toh-3.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            fail("Threw an exception");
        }
        StringBuilder contentBuilder2 = new StringBuilder();
        try (Stream<String> stream = Files
                .lines(Paths.get("resources/tohExample-3.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder2.append(s).append("\n"));
        } catch (IOException e) {
            fail("Threw an exception");
        }
        assertEquals(contentBuilder2.toString(), contentBuilder.toString());
    }

    /**
     * A test to determine that a textual view given a non-existent file will throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nonExistentFile() {
        String string = "-in oingoboingo.txt -view text";
        EasyAnimator.main(string.split(" "));
    }
}