package cs3500.animator;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.AnimationReader;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactoryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class visualizes a single animation.
 */
public final class EasyAnimator {

  /**
   * This method takes parameters to visualize an animation.
   * @param args The list of parameters to base the animation's visualization on.
   */
  public static void main(String[] args) {
    String inArg = "";
    String viewArg = "";
    float speedArg = 1;
    String outArg = "System.out";

    for (int i = 0; i < args.length; i += 2) {
      String command = args[i];
      String value = args[i + 1];

      switch (command) {
        case "-in":
          inArg = value;
          break;
        case "-out":
          outArg = value;
          break;
        case "-view":
          viewArg = value;
          break;
        case "-speed":
          try {
            speedArg = Float.parseFloat(value);
          } catch (NumberFormatException e) {
            JFrame frame = new JFrame("Error Message");
            JOptionPane.showMessageDialog(frame, "Not a valid speed.");
            throw new IllegalArgumentException("Not a valid speed!");
          }
          break;
        default:
          JFrame frame = new JFrame("Error Message");
          JOptionPane.showMessageDialog(frame, "Unsupported command.");
          throw new IllegalArgumentException("Unsupported command!");
      }
    }

    if (inArg.equals("")) {
      throw new IllegalArgumentException("-in not provided");
    }
    if (viewArg.equals("")) {
      throw new IllegalArgumentException("-view not provided");
    }

    try {
      new AnimationReader();
      Animation model = AnimationReader
          .parseFile(new FileReader(new File(inArg).getAbsolutePath()),
              new AnimationImpl.Builder());

      View view = new ViewFactoryImpl().getView(viewArg);
      view.setTicksPerSecond(speedArg);
      view.setOutputDestination(outArg);
      view.displayView(model);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found: " + inArg);
    }
  }
}