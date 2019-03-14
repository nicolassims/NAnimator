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
    String endtag;
    String xname = "x";
    String yname = "y";
    String widthname = "width";
    String heightname = "height";
    StringBuilder viewString = new StringBuilder(
        "<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    Map<String, Shape> shapes = model.getShapes();

    for (Shape shape : shapes.values()) {
      if (shape.getShape().equals("rectangle")) {
        endtag = "</rect>\n";
      } else if (shape.getShape().equals("ellipse")) {
        endtag = "</ellipse>\n";
        xname = "cx";
        yname = "cy";
        widthname = "rx";
        heightname = "ry";
      } else {
        throw new IllegalArgumentException("This shape is not a rectangle or an ellipse.");
      }
      viewString.append("<rect").append(" id=\"").append(shape.getName()).append("\" ")
          .append(xname).append("=\"")
          .append(shape.getFirstX()).append("\" ").append(yname).append("=\"")
          .append(shape.getFirstY()).append("\"").append(" ").append(widthname).append("=\"")
          .append(shape.getFirstWidth()).append("\" ")
          .append(heightname).append("=\"").append(shape.getFirstHeight()).append("\"")
          .append(" fill=\"rgb(")
          .append(shape.getFirstColors()).append(")\" visibility=\"")
          .append(shape.getFirstTick() == 0 ? "visible" : "hidden").append("\" >\n");

      List<Motion> motions = shape.getMotions();
      for (Motion motion : motions) {
        String[] motionArray = motion.toFile().split(" ");
        String attributeName;
        for (int i = 3; i < 9; i++) {
          if (!motionArray[i].equals(motionArray[i + 8])) {
            if (i == 3) {
              attributeName = xname;
            } else if (i == 4) {
              attributeName = yname;
            } else if (i == 5) {
              attributeName = widthname;
            } else if (i == 6) {
              attributeName = heightname;
            } else if (i == 7) {
              attributeName = xname;
            } else {
              attributeName = "fill";
            }
            viewString.append("<animate attributeType=\"xml\" begin=\"")
                .append(motion.getStartFrame().getTick() * ticksPerSecond).append("ms\"")
                .append(" dur=\"").append(motion.getEndFrame().getTick() * ticksPerSecond)
                .append("ms\" attributeName=\"").append(attributeName).append("\" from=\"")
                .append(motionArray[i]).append("\" to=\"").append(motionArray[i + 8])
                .append("\" fill=\"remove\" />\n");
          }
        }
      }
      //       name time x   y  w  h  r   g b time x   y  w  h  r   g  b
      //motion S0   0    100 75 20 15 255 0 0 0    100 75 20 15 255 0  0
      //     0 1    2    3   4  5  6  7   8 9 10   11  12 13 14 15  16 17
      //3 > 11
      //9 > 17

      //<animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cx" from="500" to="600" fill="remove" />
      viewString.append(endtag);
    }
    System.out.println(viewString);
  }
}
