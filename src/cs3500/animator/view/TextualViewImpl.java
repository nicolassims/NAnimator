package cs3500.animator.view;

import cs3500.animator.model.Animation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents the formatted text that can be used to create an Animation.
 */
public class TextualViewImpl implements View {

  private int x = 0;
  private int y = 0;
  private int w = 700;
  private int h = 500;
  private String outputDestination;
  private BufferedWriter writer;

  @Override
  public void setTicksPerSecond(float i) {
    //Implemented but empty for future compatibility with a controller.
  }

  @Override
  public void displayView(Animation model) {
    if (outputDestination.equals("System.out")) {
      System.out.println("canvas " + x + " " + y + " " + w + " " + h + "\n" + model.toFile());
    } else {
      try {
        writer = new BufferedWriter(new FileWriter("resources/" + this.outputDestination));
        writer.write("canvas " + x + " " + y + " " + w + " " + h + "\n" + model.toFile());
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (writer != null) {
            writer.close();
          } else {
            System.out.println("Buffer has not been initialized!");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void setOutputDestination(String outArg) {
    this.outputDestination = outArg;
  }

  @Override
  public void setCurrentTick(int currentTick) {
    //Implemented but empty for future compatibility with a controller.
  }

  @Override
  public void refresh() {
    //Implemented but empty for future compatibility with a controller.
  }
}
