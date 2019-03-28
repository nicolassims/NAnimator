package cs3500.animator.view;

import static cs3500.animator.view.AppendableWriter.writeToDestination;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Shape;

/**
 * This class represents the formatted text that can be used to create an Animation.
 */
public class TextualViewImpl implements TextBasedView {

  private Appendable outputDestination;

  @Override
  public void displayView(Animation model) {
    int x = model.getX();
    int y = model.getY();
    int w = model.getCanvasWidth();
    int h = model.getCanvasHeight();
    StringBuilder built = new StringBuilder("");
    for (Shape s : model.getShapes().values()) {
      built.append("shape ").append(s.getName()).append(" ").append(s.getShape()).append("\n")
          .append(s.toFile());
    }
    writeToDestination(this.outputDestination,
        "canvas " + x + " " + y + " " + w + " " + h + "\n" + built.toString()
            .substring(0, built.toString().length() - 1));
  }

  @Override
  public void setOutputDestination(Appendable outArg) {
    this.outputDestination = outArg;
  }
}