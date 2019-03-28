package cs3500.animator.view;

import static cs3500.animator.view.AppendableWriter.writeToDestination;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;

/**
 * This class represents the formatted text that can be used to create an Animation.
 */
public class TextualViewImpl implements TextBasedView {

  private Appendable outputDestination;

  @Override
  public void setOutputDestination(Appendable outArg) {
    this.outputDestination = outArg;
  }

  @Override
  public void displayView(Animation model) {
    int x = model.getX();
    int y = model.getY();
    int w = model.getCanvasWidth();
    int h = model.getCanvasHeight();
    StringBuilder built = new StringBuilder("");
    built.append("canvas ").append(x).append(" ").append(y).append(" ").append(w).append(" ")
        .append(h).append("\n");
    for (Shape s : model.getShapes().values()) {
      built.append("shape ").append(s.getName()).append(" ").append(s.getShape()).append("\n");
      appendMotions(built, s);

    }
    writeToDestination(this.outputDestination, built.toString()
        .substring(0, built.toString().length() - 1).replaceAll("\\.0", ""));
  }

  /**
   * Appends the Shape's motions to the StringBuilder passed in.
   *
   * @param built The StringBuilder to have motions appended to it.
   * @param s The Shape whose Motions will be appended as Strings.
   */
  private void appendMotions(StringBuilder built, Shape s) {
    for (Motion m : s.getMotions()) {
      built.append("motion ").append(s.getName()).append(" ").append(m.getStartFrame().getTick())
          .append(" ").append(m.getStartFrame().getFirstX()).append(" ")
          .append(m.getStartFrame().getFirstY()).append(" ")
          .append(m.getStartFrame().getFirstWidth()).append(" ")
          .append(m.getStartFrame().getFirstHeight()).append(" ")
          .append(m.getStartFrame().getFirstColors().replaceAll(",", " ")).append(" ")
          .append(m.getEndFrame().getTick()).append(" ")
          .append(m.getEndFrame().getFirstX()).append(" ").append(m.getEndFrame().getFirstY())
          .append(" ").append(m.getEndFrame().getFirstWidth()).append(" ")
          .append(m.getEndFrame().getFirstHeight()).append(" ")
          .append(m.getEndFrame().getFirstColors().replaceAll(",", " ")).append("\n");
    }
  }
}