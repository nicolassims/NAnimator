package cs3500.animator.view;

import cs3500.animator.model.Animation;

/**
 * This class represents a view that contains a VisualView, and can edit it.
 */
public class EditorViewImpl implements EditorView, TimeBasedView {
  private VisualView view;
  private float ticksPerSecond;
  private int currentTick;
  private boolean isPlaying;
  private boolean isLooping = false;

  /**
   * Creates an instance of EditorViewImpl, and sets the instance's view to be a brand new,
   * untouched, VisualViewImpl.
   */
  public EditorViewImpl() {
    this(new VisualViewImpl());
  }

  /**
   * Creates an instance of EditorViewImpl, and sets the instance's view to be equal to the
   * passed-in VisualView variable.
   *
   * @param view The VisualView that will become the EditorViewImpl's view to be edited.
   */
  public EditorViewImpl(VisualView view) {
    this.view = view;
    this.ticksPerSecond = view.getTicksPerSecond();
  }

  @Override
  public void start() {
    this.isPlaying = true;
  }

  @Override
  public void pause() {
    this.isPlaying = false;
  }

  @Override
  public void resume() {
    this.isPlaying = true;
  }

  @Override
  public void restart() {
    this.currentTick = 0;
    this.isPlaying = true;
  }

  @Override
  public void toggleLooping() {
    this.isLooping = !this.isLooping;
  }

  @Override
  public void setTicksPerSecond(float i) {
    this.ticksPerSecond = i;
  }

  @Override
  public void displayView(Animation model) {
    ((TimeBasedView) view).setTicksPerSecond(this.ticksPerSecond);
    System.out.println(view.getTicksPerSecond());
    view.displayView(model);
  }
}
