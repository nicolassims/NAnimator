package cs3500.animator.provider.view;

import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.model.Shapes;
import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;
import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.Motion;
import cs3500.animator.provider.model.ShapeToIShape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * An ActionListener to be recievevd by the EditView and interpret the pressing of the buttons that
 * the user might, uh, press.
 */
public class EditController implements ActionListener {

  private AnimationModel animation;
  private EditorView editor;
  private EditingPanel editingPanel;
  private List<IShape> shapeList;
  private IShape activeIShape;

  EditController(AnimationModel animation, EditingPanel editingPanel, EditorView editor) {
    this.animation = animation;
    this.editor = editor;
    this.editingPanel = editingPanel;
    this.shapeList = animation.getShapes();
    this.activeIShape = this.shapeList.get(0);
    updateEverything();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "shapeCB":
        if (this.editingPanel.shapeCB.getSelectedIndex() != -1) {
          this.activeIShape = this.shapeList.get(this.editingPanel.shapeCB.getSelectedIndex());
          this.editingPanel.updateTextFields(
              this.activeIShape.getMotion(this.editingPanel.keyFrameCB.getSelectedIndex() + 2));
        }
        break;
      case "keyFrameCB":
        if (this.editingPanel.keyFrameCB.getSelectedIndex() != -1) {
          this.editingPanel.updateTextFields(
              this.activeIShape.getMotion(this.editingPanel.keyFrameCB.getSelectedIndex() + 2));
        }
        break;
      case "saveBtn":
        this.activeIShape.editKeyFrame(this.editingPanel.keyFrameCB.getSelectedIndex(),
            this.editor.getInputValues());//Editor does not show new and proper values
        updateEverything();
        break;
      case "createShapeBtn":
        try {
          Shapes shapetype;
          switch (this.editingPanel.newShapeTypeCB
              .getItemAt(this.editingPanel.newShapeTypeCB.getSelectedIndex()).toString()
              .toUpperCase()) {
            case "RECT":
              shapetype = Shapes.RECTANGLE;
              break;
            case "ELLIPSE":
              shapetype = Shapes.ELLIPSE;
              break;
            default:
              throw new IllegalArgumentException("Unsupported shape.");
          }
          this.animation.addShape(new ShapeToIShape(
              new ShapeImpl(shapetype, this.editingPanel.newShapeNameTextField.getText())));
          this.animation.getShape(this.editingPanel.newShapeNameTextField.getText()).addMotion(
              new Motion(new KeyframeImpl(1,
                  new Position2D(Integer.valueOf(this.editingPanel.xTextField.getText()),
                      Integer.valueOf(this.editingPanel.yTextField.getText())),
                  new Size2D(Integer.valueOf(this.editingPanel.widthTextField.getText()),
                      Integer.valueOf(this.editingPanel.heightTextField.getText())),
                  new TextureImpl(Integer.valueOf(this.editingPanel.rTextField.getText()),
                      Integer.valueOf(this.editingPanel.gTextField.getText()),
                      Integer.valueOf(this.editingPanel.bTextField.getText()), 1))));
          this.shapeList = animation.getShapes();
          updateEverything();
        } catch (NumberFormatException n) {
          throw new IllegalArgumentException(
              "One of the number fields contatins an invalid variable.");
        }
        break;
      case "insertKeyFrameBtn":
        this.animation.addMotion(this.editingPanel.shapeCB.getSelectedItem().toString(),
            new Motion(new KeyframeImpl(
                Integer.valueOf(this.editingPanel.newKeyFrameTickTextField.getText()),
                new Position2D(Integer.valueOf(this.editingPanel.xTextField.getText()),
                    Integer.valueOf(this.editingPanel.yTextField.getText())),
                new Size2D(Integer.valueOf(this.editingPanel.widthTextField.getText()),
                    Integer.valueOf(this.editingPanel.heightTextField.getText())),
                new TextureImpl(Integer.valueOf(this.editingPanel.rTextField.getText()),
                    Integer.valueOf(this.editingPanel.gTextField.getText()),
                    Integer.valueOf(this.editingPanel.bTextField.getText()), 1))));
        updateEverything();
        break;
      case "deleteShapeBtn":
        this.animation.deleteShape(this.editingPanel.shapeCB.getSelectedItem().toString());
        this.shapeList.remove(this.editingPanel.shapeCB.getSelectedIndex());
        updateEverything();
        break;
      case "deleteKeyFrameBtn":
        this.animation.deleteKeyFrame(this.editingPanel.shapeCB.getSelectedItem().toString(),
            Integer.parseInt(this.editingPanel.keyFrameCB.getSelectedItem().toString()));
        updateEverything();
        break;
      default:
        throw new IllegalArgumentException("Invalid");
    }
  }

  /**
   * Make sure the elements on the window reflect the reality of the animation.
   */
  private void updateEverything() {
    if (this.editingPanel.shapeCB.getItemCount() != this.shapeList.size()) {
      this.editingPanel.shapeCB.removeAllItems();
      for (IShape shape : this.shapeList) {
        this.editingPanel.shapeCB.addItem(shape.getID());
      }
    }
    if (this.activeIShape.getMotions().size() != this.editingPanel.keyFrameCB.getItemCount()) {
      this.editingPanel.keyFrameCB.removeAllItems();
      for (IMotion motion : this.activeIShape.getMotions()) {
        this.editingPanel.keyFrameCB.addItem(Integer.toString(motion.getBeginTime()));
      }
    }
    this.editingPanel.updateTextFields(
        this.activeIShape.getMotion(this.editingPanel.keyFrameCB.getSelectedIndex() + 2));
  }
}