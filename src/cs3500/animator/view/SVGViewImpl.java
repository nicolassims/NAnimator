package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class represents the formatted text used to create an SVG File.
 */
public class SVGViewImpl implements View {

  private float ticksPerSecond = 1;
  private int x = 0;
  private int y = 0;
  private int w = 700;
  private int h = 500;
  private String outputDestination = "System.out";
  private BufferedWriter writer;

  @Override
  public void setTicksPerSecond(float i) {
    ticksPerSecond = i;
  }

  @Override
  public void setDimensions(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public void displayView(Animation model) {
    String starttag;
    String endtag;
    String xname = "x";
    String yname = "y";
    String widthname = "width";
    String heightname = "height";
    StringBuilder viewString = new StringBuilder();
    viewString.append("<svg viewBox=\"").append(x).append(" ").append(y).append(" ").append(w)
        .append(" ").append(h).append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
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
        if (!startsVisible) {
          viewString.append("<animate attributeType=\"xml\" begin=\"")
              .append(motion.getFirstTick() / ticksPerSecond).append("s\" dur=\"")
              .append(0.01).append(
              "s\" attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n");
          startsVisible = true;
        }
        //boolean allSame = true;
        for (int j = 3; j < 9; j++) {
          if (!motionArray[j].equals(motionArray[j + 8])) {
            //allSame = false;
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
                    : "rgb(" + motionArray[7] + "," + motionArray[8] + "," + motionArray[9] + ")"))
                .append("\" to=\"")
                .append((j <= 6 ? motionArray[j + 8]
                    : "rgb(" + motionArray[15] + "," + motionArray[16] + "," + motionArray[17]
                        + ")"))
                .append("\" fill=\"freeze\" />\n");
          }
        }
      }
      viewString.append(endtag);
    }
    viewString.append("</svg>");

    if (outputDestination.equals("System.out")) {
      System.out.println(viewString.toString());
    } else {
      try {
        writer = new BufferedWriter(new FileWriter("resources/" + this.outputDestination));
        writer.write(viewString.toString());
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

  }

  @Override
  public void refresh() {

  }
}