package cs3500.animator.view;

public interface TextBasedView extends View {

  /**
   * Sets the destination for the model output in case the view is meant to save it or display it on
   * the command line.
   */
  void setOutputDestination(String outArg);
}