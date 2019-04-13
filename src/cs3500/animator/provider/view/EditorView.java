package cs3500.animator.provider.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.Motion;
import cs3500.animator.provider.model.ShapeType;

/**
 * The view that works like a Visual View but allows interactivity.
 */
public class EditorView extends JFrame implements IEditorView {

  private AnimationModel model;

  private AnimationPanel animationPanel;
  private ControlPanel controlPanel;
  protected EditingPanel editingPanel;

  /**
   * The constructor for EditorView.
   * @param model The model.
   */
  public EditorView(AnimationModel model) {
    this();
    this.model = model;
  }

  public EditorView() {
    super();
  }

  /**
   * Must setModel before start.
   */
  @Override
  public void start() {
    this.setLayout(new GridBagLayout());


    GridBagConstraints c = new GridBagConstraints();
    //Animation Panel with scroll bars
    JScrollPane animationScrollPanel;
    animationScrollPanel = new JScrollPane(animationPanel);
    animationScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    animationScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    //    animationScrollPanel.setPreferredSize(getPreferredSize());
    animationScrollPanel.setMinimumSize(animationPanel.getPreferredSize());
    c.gridx = 0;
    c.gridy = 0;
    c.gridheight = 2;
    c.fill = GridBagConstraints.BOTH;
    this.add(animationScrollPanel, c);

    //Control Panel
    c.gridx = 1;
    c.gridy = 0;
    c.gridheight = 1;
    this.add(controlPanel, c);

    //Editing Panel
    c.gridx = 1;
    c.gridy = 1;
    this.add(editingPanel, c);

    this.pack();
    this.setMinimumSize(this.getSize()); //The window won't go smaller than default
    this.setVisible(true);
    animationPanel.startDrawing();

  }

  @Override
  public void setModel(AnimationModel model) {
    this.model = model;
    animationPanel = new AnimationPanel(model);
    controlPanel = new ControlPanel(this);
    editingPanel = new EditingPanel(model);
  }

  @Override
  public void setTempo(int tempo) {
    animationPanel.setTempo(tempo);
  }

  @Override
  public void setListener(ActionListener actionListener) {
    editingPanel.shapeCB.addActionListener(actionListener);
    editingPanel.keyFrameCB.addActionListener(actionListener);
    editingPanel.insertKeyFrameBtn.addActionListener(actionListener);
    editingPanel.saveBtn.addActionListener(actionListener);
    editingPanel.createShapeBtn.addActionListener(actionListener);
    editingPanel.deleteKeyFrameBtn.addActionListener(actionListener);
    editingPanel.deleteShapeBtn.addActionListener(actionListener);
  }

  @Override
  public void setEnableDeleteShape(Boolean enable) {
    editingPanel.setEnableDeleteShape(enable);
  }

  @Override
  public void setEnableDeleteKeyFrame(Boolean enable) {
    editingPanel.setEnableDeleteKeyFrame(enable);
  }

  @Override
  public void updateShapeCB(Collection<String> items) {
    editingPanel.shapeNames.clear();
    editingPanel.shapeNames.addAll(items);
    if (editingPanel.shapeCB.getItemCount() > 0) {
      editingPanel.shapeCB.setSelectedIndex(0);
    } else {
      editingPanel.shapeCB.setSelectedIndex(-1);
    }
  }

  @Override
  public void updateKeyFrameCB(Collection<String> items) {
    editingPanel.keyFrameNames.clear();
    editingPanel.keyFrameNames.addAll(items);
    if (editingPanel.keyFrameCB.getItemCount() > 0) {
      editingPanel.keyFrameCB.setSelectedIndex(0);
    } else {
      editingPanel.keyFrameCB.setSelectedIndex(-1);
    }
  }

  @Override
  public void setLooping(boolean isLooping) {
    animationPanel.setLooping(isLooping);
  }

  @Override
  public void startOrStop(boolean run) {
    if (run) {
      animationPanel.startDrawing();
    } else {
      animationPanel.stopDrawing();
    }
  }

  @Override
  public void restartAnimation() {
    animationPanel.restartDrawing();
  }

  @Override
  public void updateValues(IMotion keyframe) {
    editingPanel.updateTextFields(keyframe);
  }

  @Override
  public IMotion getInputValues() {
    IMotion keyframe = new Motion(0, Integer.parseInt(
            (String) Objects.requireNonNull(editingPanel.keyFrameCB.getSelectedItem())),
            Integer.parseInt(editingPanel.xTextField.getText()),
            Integer.parseInt(editingPanel.yTextField.getText()),
            Integer.parseInt(editingPanel.widthTextField.getText()),
            Integer.parseInt(editingPanel.heightTextField.getText()),
            new Color(Integer.parseInt(editingPanel.rTextField.getText()),
                    Integer.parseInt(editingPanel.gTextField.getText()),
                    Integer.parseInt(editingPanel.bTextField.getText())));
    return keyframe;

  }

  @Override
  public int getSelectedKeyFrameIndex() {
    return editingPanel.keyFrameCB.getSelectedIndex();
  }

  @Override
  public ShapeType getSelectedNewShapeType() {
    return (ShapeType)editingPanel.newShapeTypeCB.getSelectedItem();
  }

  @Override
  public String getNewShapeNameInput() {
    return editingPanel.newShapeNameTextField.getText();
  }

  @Override
  public int getInsertFrameTickInput() {
    return Integer.parseInt(editingPanel.newKeyFrameTickTextField.getText());
  }

  @Override
  public void updateSelectedShape() {
    if (editingPanel.shapeCB.getItemCount() > 0) {
      animationPanel.setSelectedShape(
              model.getShapes().get(editingPanel.shapeCB.getSelectedIndex()));
    } else {
      animationPanel.setSelectedShape(null);
    }
  }
}
