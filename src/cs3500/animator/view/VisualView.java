package cs3500.animator.view;

/**
 * This interface represents some special methods for the visualView.
 */
public interface VisualView extends View {
  /**
   * Sets the current tick the model is at.
   */
  void setCurrentTick(int currentTick);

  /**
   * Refreshes the view after setting a new current tick.
   */
  void refresh();

  /**
   * Gets the number of ticks per second the animation is running at right now.
   * @return the number of ticks per second the animation is running at right now.
   */
  float getTicksPerSecond();
}
