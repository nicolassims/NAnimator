package cs3500.animator.view;

/**
 * A class capable of instantiating different kinds of views, all compatible to an animation model.
 */
public interface ViewFactory {

  /**
   * Given a string representing a type of view, returns a View if the view exists. Otherwise,
   * throws an IllegalArgumentException.
   */
  View getView(String viewType) throws IllegalArgumentException;
}
