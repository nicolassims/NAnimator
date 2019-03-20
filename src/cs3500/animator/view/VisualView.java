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
}
