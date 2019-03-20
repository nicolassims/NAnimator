package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This interface represents a method used for writing to files, or to System.out.
 */
public interface FileWriter {

  /**
   * A method used for writing to files, or to System.out.
   * @param location The location to write to.
   * @param content The content to write.
   */
  static void writeToDestination(String location, String content) {
    if (location.equals("System.out")) {
      System.out.println(content);
    } else {
      try {
        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(location));
        writer.write(content);
        writer.close();
      } catch (IOException e) {
        System.out.println("Buffer has not been initialized!");
      }
    }
  }
}
