package cs3500.animator.view;

import model.Animation;

public class SVGViewImpl implements View {

  @Override
  public void displayView(Animation model) {
    StringBuilder anim = new StringBuilder();
    anim.append("<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");

  }
}
