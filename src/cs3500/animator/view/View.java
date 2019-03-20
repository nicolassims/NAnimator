package cs3500.animator.view;

import cs3500.animator.model.Animation;

/**
 * This interface represents all supported types of views.
 */
public interface View {

  /**
   * Sets the number of unitless "ticks" that happen per second. Default is 60.
   */
  void setTicksPerSecond(float i);

  /**
   * Displays the model through some visual form.
   */
  void displayView(Animation model);

  /**
   * Sets the destination for the model output in case the view is meant to save it or display it on
   * the command line.
   */
  void setOutputDestination(String outArg);
}
