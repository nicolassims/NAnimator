package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;

import java.util.List;
import java.util.Map;

public class SVGViewImpl implements View {

  private int ticksPerSecond = 60;

  @Override
  public void setTicksPerSecond(int i) {
    ticksPerSecond = i;
  }

  @Override
  public void displayView(Animation model) {
    String starttag;
    String endtag;
    String xname = "x";
    String yname = "y";
    String widthname = "width";
    String heightname = "height";
    StringBuilder viewString = new StringBuilder(
        "<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    Map<String, Shape> shapes = model.getShapes();

    for (Shape shape : shapes.values()) {
      Boolean startsVisible = shape.getFirstTick() == 0;
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
      List<Motion> motions = shape.getMotions();
      for (Motion motion : motions) {
        String[] motionArray = motion.toFile().split(" ");
        String attributeName;
        for (int j = 3; j < 9; j++) {
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
            if (!startsVisible) {
              viewString.append("<animate attributeType=\"xml\" begin=\"")
                  .append(motion.getFirstTick() * ticksPerSecond).append("ms\" dur=\"")
                  .append((shape.totalDuration() - motion.getFirstTick()) * ticksPerSecond).append(
                  "ms\" attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n");
              startsVisible = true;
            }
            viewString.append("<animate attributeType=\"xml\" begin=\"")
                .append(motion.getStartFrame().getTick() * ticksPerSecond).append("ms\"")
                .append(" dur=\"")
                .append((motion.getEndFrame().getTick() - motion.getFirstTick()) * ticksPerSecond)
                .append("ms\" attributeName=\"").append(attributeName).append("\" from=\"")
                .append((j <= 6 ? motionArray[j]
                    : "rgb(" + motionArray[7] + "," + motionArray[8] + "," + motionArray[9] + ")"))
                .append("\" to=\"")
                .append((j <= 6 ? motionArray[j + 8]
                    : "rgb(" + motionArray[15] + "," + motionArray[16] + "," + motionArray[17]
                        + ")"))
                .append("\" fill=\"remove\" />\n");
          }
        }
      }
      viewString.append(endtag);
    }
    viewString.append("</svg>");
    System.out.println(viewString);
  }
}

///FIX DURATIONS: Why is total duration saying that the durations of both shapes are 6000?