package cs3500.animator.view;

import cs3500.animator.model.Animation;

/**
 * This interface represents some special methods for the visualView.
 */
public interface VisualView extends TimeBasedView {

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
   *
   * @return the number of ticks per second the animation is running at right now.
   */
  float getTicksPerSecond();

  /**
   * Sets a title.
   */
  void setTitle(String s);

  /**
   * Makes the view visible.
   */
  void makeVisible();

  /**
   * Displays the model at the given tick without running the animation and showing the highlighted
   * shape if any.
   */
  void peekAtTick(Animation model, int tick, String selectedShapeKey);
}
