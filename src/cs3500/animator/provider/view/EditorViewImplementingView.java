package cs3500.animator.provider.view;

import cs3500.animator.model.Animation;
import cs3500.animator.provider.model.AnimationToAnimationModel;
import cs3500.animator.view.View;


/**
 * Turns the provided EditorView implementation into one that implements Nicolas and Forrest's View
 * interface.
 */
public class EditorViewImplementingView extends EditorView implements View {

  private EditorView editorView;

  /**
   * Simply takes in an EditorView and sets the class' field to be that View.
   *
   * @param editorView The EditorView this class adds View-Implementation to.
   */
  public EditorViewImplementingView(EditorView editorView) {
    this.editorView = editorView;
  }

  @Override
  public void displayView(Animation model) {
    editorView.setModel(new AnimationToAnimationModel(model));
    editorView.setTempo(20);
    editorView.setListener(
        new EditController(new AnimationToAnimationModel(model), editorView.editingPanel,
            editorView));
    editorView.start();
  }
}
