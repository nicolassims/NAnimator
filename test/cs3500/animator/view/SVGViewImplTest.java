package cs3500.animator.view;

import org.junit.Test;

public class SVGViewImplTest extends AbstractViewTest {

  @Test
  public void displayView() {
    view = new SVGViewImpl();
    view.displayView(exampleModel);
  }
}