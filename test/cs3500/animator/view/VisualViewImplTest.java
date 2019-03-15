package cs3500.animator.view;

import org.junit.Test;

public class VisualViewImplTest extends AbstractViewTest {

  @Test
  public void displayView() {
    view = new VisualViewImpl();
    view.displayView(exampleModel);
  }
}