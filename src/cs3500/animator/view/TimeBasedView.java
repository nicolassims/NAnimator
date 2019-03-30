package cs3500.animator.view;

/**
 * This interface represents a model whose output is not measured in unitless ticks, but is measured
 * in ticks per second.
 */
public interface TimeBasedView extends View {

  /**
   * Sets the number of unitless "ticks" that happen per second. Default is 60.
   */
  void setTicksPerSecond(float i);
}