package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This interface represents a method used for writing to files, or to System.out.
 */
public interface AppendableWriter {

  /**
   * A method used for writing to files, or to System.out.
   *
   * @param location The location to write to.
   * @param content The content to write.
   */
  static void writeToDestination(Appendable location, String content) {
    try {
      location.append(content);
      if (location instanceof FileWriter) {
        ((FileWriter) location).close();
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException caught.");
    }
  }
}

