package cs3500.animator.view;

import static cs3500.animator.view.FileWriter.writeToDestination;

import cs3500.animator.model.Animation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents the formatted text that can be used to create an Animation.
 */
public class TextualViewImpl implements View {
  private String outputDestination;

  @Override
  public void displayView(Animation model) {
    int x = model.getX();
    int y = model.getY();
    int w = model.getCanvasWidth();
    int h = model.getCanvasHeight();
    writeToDestination(this.outputDestination,
        "canvas " + x + " " + y + " " + w + " " + h + "\n" + model.toFile());
  }

  @Override
  public void setOutputDestination(String outArg) {
    this.outputDestination = outArg;
  }

  @Override
  public void setTicksPerSecond(float i) {
    //Implemented but empty for future compatibility with a controller.
  }
}
