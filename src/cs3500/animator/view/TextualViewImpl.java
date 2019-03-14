package cs3500.animator.view;

import cs3500.animator.model.Animation;

public class TextualViewImpl implements View {
  private int ticksPerSecond = 60;

  @Override
  public void setTicksPerSecond(int i) {
    ticksPerSecond = i;
  }

  @Override
  public void displayView(Animation model) {

  }
}
