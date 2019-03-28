package cs3500.animator.view;

import static cs3500.animator.view.AppendableWriter.writeToDestination;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;

import java.util.List;

/**
 * This class represents the formatted text used to create an SVG File.
 */
public class SVGViewImpl implements TextBasedView, TimeBasedView {

  private float ticksPerSecond = 1;
  private Appendable outputDestination;
  private String xname;
  private String yname;
  private String widthname;
  private String heightname;
  private Boolean startsVisible;

  @Override
  public void setTicksPerSecond(float i) {
    ticksPerSecond = i;
  }

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
    StringBuilder viewString = new StringBuilder();
    viewString.append("<svg viewBox=\"").append(x).append(" ").append(y).append(" ").append(w)
        .append(" ").append(h)
        .append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");

    appendShapes(viewString, model);
    viewString.append("</svg>");
    writeToDestination(this.outputDestination, viewString.toString());
  }

  /**
   * Appends the Animation's shapes to the StringBuilder passed in.
   *
   * @param viewString The StringBuilder to have shapes appended to it.
   * @param model The Animation whose Shapes will be appended as Strings.
   */
  private void appendShapes(StringBuilder viewString, Animation model) {
    String starttag;
    String endtag;
    for (int i = 0; i < model.getShapeNames().size(); i++) {
      Shape shape = model.getShapes().get(model.getShapeNames().get(i));
      xname = "x";
      yname = "y";
      widthname = "width";
      heightname = "height";
      startsVisible = shape.getFirstTick() == 0;
      if (shape.getShape().equals("rectangle")) {
        starttag = "<rect";
        endtag = "</rect>\n";
      } else if (shape.getShape().equals("ellipse")) {
        starttag = "<ellipse";
        endtag = "</ellipse>\n";
        xname = "cx";
        yname = "cy";
        widthname = "rx";
        heightname = "ry";
      } else {
        throw new IllegalArgumentException("This shape is not a rectangle or an ellipse.");
      }
      viewString.append(starttag).append(" id=\"").append(shape.getName()).append("\" ")
          .append(xname).append("=\"")
          .append(shape.getFirstX()).append("\" ").append(yname).append("=\"")
          .append(shape.getFirstY()).append("\"").append(" ").append(widthname).append("=\"")
          .append(shape.getFirstWidth()).append("\" ")
          .append(heightname).append("=\"").append(shape.getFirstHeight()).append("\"")
          .append(" fill=\"rgb(")
          .append(shape.getFirstColors()).append(")\" visibility=\"")
          .append(startsVisible ? "visible" : "hidden").append("\" >\n");

      appendMotions(viewString, shape);

      viewString.append(endtag);
    }
  }

  /**
   * Appends the shape's motions to the StringBuilder passed in.
   *
   * @param viewString The StringBuilder to have motions appended to it.
   * @param shape The shape whose motions will be appended as Strings.
   */
  private void appendMotions(StringBuilder viewString, Shape shape) {
    List<Motion> motions = shape.getMotions();
    for (Motion motion : motions) {
      String[] motionArray = motion.toFile().split(" ");
      String attributeName;
      if (!startsVisible) {
        viewString.append("<animate attributeType=\"xml\" begin=\"")
            .append(motion.getFirstTick() / ticksPerSecond).append("s\" dur=\"")
            .append(0.01).append(
            "s\" attributeName=\"visibility\" "
                + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n");
        startsVisible = true;
      }
      for (int j = 3; j < 10; j++) {
        if (!motionArray[j].equals(motionArray[j + 8])) {
          if (j == 3) {
            attributeName = xname;
          } else if (j == 4) {
            attributeName = yname;
          } else if (j == 5) {
            attributeName = widthname;
          } else if (j == 6) {
            attributeName = heightname;
          } else {
            attributeName = "fill";
          }

          viewString.append("<animate attributeType=\"xml\" begin=\"")
              .append(motion.getStartFrame().getTick() / ticksPerSecond).append("s\"")
              .append(" dur=\"")
              .append((motion.getEndFrame().getTick() - motion.getFirstTick()) / ticksPerSecond)
              .append("s\" attributeName=\"").append(attributeName).append("\" from=\"")
              .append((j <= 6 ? motionArray[j]
                  : "rgb(" + motionArray[7] + "," + motionArray[8] + "," + motionArray[9]
                      + ")"))
              .append("\" to=\"")
              .append((j <= 6 ? motionArray[j + 8]
                  : "rgb(" + motionArray[15] + "," + motionArray[16] + "," + motionArray[17]
                      + ")"))
              .append("\" fill=\"freeze\" />\n");
        }
      }
    }
  }
}