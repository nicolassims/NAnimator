package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationModel;

/**
 * The view interface.
 * allows you to set tempo, model, or start the procedure relevant to the type of view.
 * The types of views are: visual, textual, and CSV.
 *
 */
public interface IView {

  /**
   * Changes or instantiates the tempo.
   *
   * @param tempo The speed of the animation (like a scale with which to manipulate all times).
   */
  void setTempo(int tempo);

  /**
   * Appends output to the appendable based on the contents
   * of the model and the speed (tempo).
   */
  void start();

  /**
   * Changes or instantiates the model.
   *
   * @param model The AnimationModel model to use to create the output.
   */
  void setModel(AnimationModel model);

}
