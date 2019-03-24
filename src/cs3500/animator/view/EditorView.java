package cs3500.animator.view;

/**
 * This interface represents a view that contains a VisualView, and can edit it.
 */
public interface EditorView extends View {

  /**
   * Begins the animation contained within the EditorView from its start.
   */
  void start();

  /**
   * Pauses an animation that's already been started.
   */
  void pause();

  /**
   * Starts an animation from the same point it was paused.
   */
  void resume();

  /**
   * Starts the animation over from the beginning.
   */
  void restart();

  /**
   * Turn looping of the animation on or off.
   */
  void toggleLooping();
}
