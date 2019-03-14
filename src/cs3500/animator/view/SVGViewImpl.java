package cs3500.animator.view;

import java.util.Map;
import model.Animation;
import model.Shape;

public class SVGViewImpl implements View {

  @Override
  public void displayView(Animation model) {
    StringBuilder viewString = new StringBuilder("<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    Map<String, Shape> shapes = model.getShapes();
    for (Shape shape : shapes.values()) {
      if (shape.getShape().equals("rectangle")) {
        viewString.append("<rect");
      } else if (shape.getShape().equals("ellipse")) {
        viewString.append("<ellipse");
      } else {
        throw new IllegalArgumentException("This shape is not a rectangle or an ellipse.");
      }
      viewString.append(" id=\"" + shape.getName() +"\" x=\"" + shape.getFirstX() + "\" y=\"" + shape.getFirstY() + "\"");
      viewString.append(" width=\"" + shape.getFirstWidth() + "\" height=\"" + shape.getFirstHeight() + "\"");
      viewString.append(" fill=\"rgb(" + shape.getFirstColors() + ")\" visibility=\"visible\""); //alter this if not visible on first frame
    }
    //<rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible" >


    /*String[] modelString = model.toString().split(",");
    for (int i = 0; i < modelString.length; i++) {
      if (modelString[i].equals("shape")) {
        if (modelString[i].equals("rectangle")) {
          viewString.append()
        } else if (modelString[i].equals("ellipse")) {

        } else {
          throw new IllegalArgumentException("this shape is not a rectangle or an ellipse.");
        }
      }
    }*/
  }
}
