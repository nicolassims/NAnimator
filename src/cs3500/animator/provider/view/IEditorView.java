package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationModel;
import java.awt.event.ActionListener;
import java.util.Collection;

import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.ShapeType;

/**
 * The editor view provides visualization of the animation and editor panel to control the
 * animation. The user can edit, create, delete shapes / key frames.
 */
public interface IEditorView extends IView {

  //FIX THIS
  AnimationModel getModel();

  /**
   * Set the listener for any actions.
   * @param actionListener actionListener for actions
   */
  void setListener(ActionListener actionListener);

  /**
   * Set the flag of enabling deletion of shape.
   * @param enable true = enable, false = disable
   */
  void setEnableDeleteShape(Boolean enable);

  /**
   * Set the flag of enabling deletion of key frame.
   * @param enable true = enable, false = disable
   */
  void setEnableDeleteKeyFrame(Boolean enable);

  /**
   * Update the shape combo box.
   * @param item item to be added
   */
  void updateShapeCB(Collection<String> item);

  /**
   * Update the key frame combo box.
   * @param item item to be added
   */
  void updateKeyFrameCB(Collection<String> item);

  /**
   * Make the animation return to tick 1 when it is finished (loops).
   * @param isLooping loops if true
   */
  void setLooping(boolean isLooping);

  /**
   * Starts or stops the animation movement.
   * @param run The animation plays if true, is paused if false.
   */
  void startOrStop(boolean run);

  /**
   * Restarts the animation.
   */
  void restartAnimation();

  /**
   * To update the text fields.
   * @param keyframe The IMotion to set the values based on.
   */
  void updateValues(IMotion keyframe);

  /**
   * Gets the values typed into the text fields.
   */
  IMotion getInputValues();

  /**
   * Returns an int representing the index of the selected keyframe.
   */
  int getSelectedKeyFrameIndex();

  /**
   * Returns an int representing the type of the selected shape.
   */
  ShapeType getSelectedNewShapeType();

  /**
   * Gets the typed input to represent the shape to be created.
   */
  String getNewShapeNameInput();

  /**
   * Returns an int representing the typed input for the new frame.
   */
  int getInsertFrameTickInput();

  /**
   * Update the selected shape for visual cue.
   */
  void updateSelectedShape();

  //FIX THIS
  ControlPanel getControlPanel();
}
