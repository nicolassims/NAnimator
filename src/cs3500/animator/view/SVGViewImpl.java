package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;

import java.util.List;
import java.util.Map;

public class SVGViewImpl implements View {
  int ticksPerSecond = 60;

  @Override
  public void displayView(Animation model) {
    String endtag = "";
    StringBuilder viewString = new StringBuilder(
        "<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    Map<String, Shape> shapes = model.getShapes();
    for (Shape shape : shapes.values()) {
      if (shape.getShape().equals("rectangle")) {
        endtag = "</rect>";
        viewString.append("<rect").append(" id=\"").append(shape.getName()).append("\" x=\"")
            .append(shape.getFirstX()).append("\" y=\"").append(shape.getFirstY()).append("\"")
            .append(" width=\"").append(shape.getFirstWidth()).append("\" height=\"");
      } else if (shape.getShape().equals("ellipse")) {
        endtag = "</ellipse>";
        viewString.append("<ellipse").append(" id=\"").append(shape.getName()).append("\" cx=\"")
            .append(shape.getFirstX()).append("\" cy=\"").append(shape.getFirstY()).append("\"")
            .append(" rx=\"").append(shape.getFirstWidth()).append("\" ry=\"");
      } else {
        throw new IllegalArgumentException("This shape is not a rectangle or an ellipse.");
      }
      viewString.append(shape.getFirstHeight()).append("\"").append(" fill=\"rgb(")
          .append(shape.getFirstColors()).append(")\" visibility=\"")
          .append(shape.getFirstTick() == 0 ? "visible" : "hidden").append("\" >\n");

      List<Motion> motions = shape.getMotions();
      for (Motion motion : motions) {
        viewString.append("<animate attributeType=\"xml\" begin=\"" + motion.getStartFrame().getTick() * ticksPerSecond + "\""
            + " dur=\"" + motion.getEndFrame().getTick() * ticksPerSecond + "\""); //add attributeName, from, to, fill
      }

    //<animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cx" from="500" to="600" fill="remove" />






      viewString.append(endtag);
    }
  }
}
