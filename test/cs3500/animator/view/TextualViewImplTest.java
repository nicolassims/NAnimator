package cs3500.animator.view;

import org.junit.Test;

public class TextualViewImplTest extends AbstractViewTest {

  @Test
  public void displayView() {
    view = new TextualViewImpl();
    view.displayView(exampleModel);
  }
}