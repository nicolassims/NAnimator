package cs3500.animator.provider.view;

import cs3500.animator.model.Animation;
import cs3500.animator.provider.model.AnimationToAnimationModel;
import cs3500.animator.view.View;

public class EditorViewImplementingView extends EditorView implements View {
  EditorView editorView;

  @Override
  public void displayView(Animation model) {//receives Animation
    //editorView.setModel(); takes AnimationModel
      editorView.setModel(new AnimationToAnimationModel(model));
      editorView.start();
  }
}
