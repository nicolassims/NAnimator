package cs3500.animator.view;

/**
 * This interface represents a model whose output is printed somewhere, whether that's a file or the
 * command line.
 */
public interface TextBasedView extends View {

  /**
   * Sets the destination for the model output in case the view is meant to save it or display it on
   * the command line.
   */
  void setOutputDestination(Appendable outArg);
}