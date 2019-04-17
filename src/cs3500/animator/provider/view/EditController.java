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
import java.util.ArrayList;
import java.util.List;

public class EditController implements ActionListener {

  private EditorView editor;
  private EditingPanel editingPanel;
  private List<IShape> shapeList;
  private List<cs3500.animator.provider.model.Motion> motions;
  private IShape activeIShape;
  private IMotion activeKeyframe;

  EditController(AnimationModel animation, EditingPanel editingPanel, EditorView editor) {
    this.editor = editor;
    this.editingPanel = editingPanel;
    this.shapeList = animation.getShapes();
    this.activeIShape = this.shapeList.get(0);
    this.activeKeyframe = this.activeIShape.getMotion(0);
    this.motions = new ArrayList<>();
    updateEverything();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "shapeCB":
        this.activeIShape = this.shapeList.get(this.editingPanel.shapeCB.getSelectedIndex());
        break;
      case "keyFrameCB":
        this.activeKeyframe = this.activeIShape.getMotions()
            .get(this.editingPanel.shapeCB.getSelectedIndex());
        break;
      case "saveBtn":
        this.activeIShape.editKeyFrame(this.editingPanel.keyFrameCB.getSelectedIndex(),
            this.editor.getInputValues());//Editor does not show new and proper values
        break;
      case "createShapeBtn":
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
        System.out.println(this.editingPanel.newShapeNameTextField.getText());
        this.shapeList.add(new ShapeToIShape(
            new ShapeImpl(shapetype, this.editingPanel.newShapeNameTextField.getText())));
        this.editingPanel.shapeCB.addItem(this.editingPanel.newShapeNameTextField.getText());
        break;
      case "insertKeyFrameBtn":
        System.out.println("Inserted keyframe.");
        break;
      case "deleteShapeBtn":
        System.out.println("Deleted shape.");
        break;
      case "deleteKeyFrameBtn":
        System.out.println("Deleted keyframe.");
        break;
      default:
        throw new IllegalArgumentException("Invalid");
    }
    updateEverything();
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
    if (this.editingPanel.keyFrameCB.getItemCount() != this.shapeList
        .get(this.editingPanel.shapeCB.getSelectedIndex()).getMotions().size()) {
      this.editingPanel.keyFrameCB.removeAllItems();
      for (IMotion motion : this.shapeList
          .get(this.editingPanel.shapeCB.getSelectedIndex()).getMotions()) {
        this.editingPanel.keyFrameCB.addItem(Integer.toString(motion.getBeginTime()));
      }
    }
    IShape activeShape = this.activeIShape.getFrame(Double.valueOf(
        this.editingPanel.keyFrameCB.getItemAt(this.editingPanel.keyFrameCB.getSelectedIndex())
            .toString()));
    IMotion keyframe = new Motion(new KeyframeImpl(Integer.valueOf(
        this.editingPanel.keyFrameCB.getItemAt(this.editingPanel.keyFrameCB.getSelectedIndex())
            .toString()),
        new Position2D(activeShape.getX(), activeShape.getY()),
        new Size2D(activeShape.getWidth(), activeShape.getHeight()),
        new TextureImpl(activeShape.getColor().getR(), activeShape.getColor().getG(),
            activeShape.getColor().getB(), 1)));
    this.editingPanel.updateTextFields(keyframe);
  }
}