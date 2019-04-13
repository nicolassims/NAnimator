package cs3500.animator.provider.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.ShapeType;

/**
 * A animationPanel for editing, adding, deleting shapes & keyframes, etc.
 */
class EditingPanel extends JPanel {

  AnimationModel model;

  //components
  JButton saveBtn;
  JButton createShapeBtn;
  JButton deleteShapeBtn;
  JButton deleteKeyFrameBtn;
  JButton insertKeyFrameBtn;
  JComboBox shapeCB;
  JComboBox keyFrameCB;
  JComboBox newShapeTypeCB;
  JLabel shapeLabel;
  JLabel keyFrameLabel;
  JLabel xLabel;
  JLabel yLabel;
  JLabel widthLabel;
  JLabel heightLabel;
  JLabel rLabel;
  JLabel gLabel;
  JLabel bLabel;
  JFormattedTextField xTextField;
  JFormattedTextField yTextField;
  JFormattedTextField widthTextField;
  JFormattedTextField heightTextField;
  JFormattedTextField rTextField;
  JFormattedTextField gTextField;
  JFormattedTextField bTextField;
  JTextField newShapeNameTextField;
  JFormattedTextField newKeyFrameTickTextField;

  //text
  static final String SAVE = "Save";
  static final String CREATE_SHAPE = "Create a new shape";
  static final String DELETE_SHAPE = "Delete current shape";
  static final String DELETE_KEY_FRAME = "Delete current key frame";
  static final String INSERT_KEY_FRAME = "Insert a key frame at: ";
  static final String X = "x: ";
  static final String Y = "y: ";
  static final String WIDTH = "Width: ";
  static final String HEIGHT = "Height: ";
  static final String COLOR_R = "Color R: ";
  static final String COLOR_G = "Color G: ";
  static final String COLOR_B = "Color B: ";
  static final String SHAPE = "Shape name: ";
  static final String KEYFRAME = "Key frame: (tick)";

  Vector<String> shapeNames = new Vector<>();
  Vector<String> keyFrameNames = new Vector<>();


  /**
   * The constructor for EditingPanel.
   *
   * @param model the model to be used for the animation.
   */
  EditingPanel(AnimationModel model) {
    this.model = model;


    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //buttons
    saveBtn = new JButton(SAVE);
    createShapeBtn = new JButton(CREATE_SHAPE);
    deleteShapeBtn = new JButton(DELETE_SHAPE);
    deleteKeyFrameBtn = new JButton(DELETE_KEY_FRAME);
    insertKeyFrameBtn = new JButton(INSERT_KEY_FRAME);
    saveBtn.setActionCommand("saveBtn");
    createShapeBtn.setActionCommand("createShapeBtn");
    deleteShapeBtn.setActionCommand("deleteShapeBtn");
    deleteKeyFrameBtn.setActionCommand("deleteKeyFrameBtn");
    insertKeyFrameBtn.setActionCommand("insertKeyFrameBtn");



    //combo boxes
    shapeCB = new JComboBox(shapeNames);
    keyFrameCB = new JComboBox(keyFrameNames);
    newShapeTypeCB = new JComboBox(ShapeType.values());
    shapeCB.setActionCommand("shapeCB");
    keyFrameCB.setActionCommand("keyFrameCB");

    //labels
    shapeLabel = new JLabel(SHAPE);
    keyFrameLabel = new JLabel(KEYFRAME);
    xLabel = new JLabel(X);
    yLabel = new JLabel(Y);
    widthLabel = new JLabel(WIDTH);
    heightLabel = new JLabel(HEIGHT);
    rLabel = new JLabel(COLOR_R);
    gLabel = new JLabel(COLOR_G);
    bLabel = new JLabel(COLOR_B);

    //text fields
    NumberFormat format = new DecimalFormat("#");
    xTextField = new JFormattedTextField(format);
    yTextField = new JFormattedTextField(format);
    widthTextField = new JFormattedTextField(format);
    heightTextField = new JFormattedTextField(format);
    newKeyFrameTickTextField = new JFormattedTextField(format);
    format.setMinimumIntegerDigits(0);
    format.setMaximumIntegerDigits(255);
    rTextField = new JFormattedTextField(format);
    gTextField = new JFormattedTextField(format);
    bTextField = new JFormattedTextField(format);
    newShapeNameTextField = new JTextField("new shape 1");

    //add all items
    this.add(shapeLabel);
    this.add(shapeCB);
    this.add(keyFrameLabel);
    this.add(keyFrameCB);
    this.add(xLabel);
    this.add(xTextField);
    this.add(yLabel);
    this.add(yTextField);
    this.add(widthLabel);
    this.add(widthTextField);
    this.add(heightLabel);
    this.add(heightTextField);
    this.add(rLabel);
    this.add(rTextField);
    this.add(gLabel);
    this.add(gTextField);
    this.add(bLabel);
    this.add(bTextField);
    this.add(saveBtn);
    this.add(createShapeBtn);
    this.add(newShapeTypeCB);
    this.add(newShapeNameTextField);
    this.add(insertKeyFrameBtn);
    this.add(newKeyFrameTickTextField);
    this.add(deleteShapeBtn);
    this.add(deleteKeyFrameBtn);




    this.setVisible(true);
  }

  /**
   * Changes the text in the text fields to match the given keyframe.
   *
   * @param keyframe The given keyframe to get the information from.
   */
  void updateTextFields(IMotion keyframe) {
    if (keyframe == null) {
      xTextField.setText("");
      yTextField.setText("");
      widthTextField.setText("");
      heightTextField.setText("");
      rTextField.setText("");
      gTextField.setText("");
      bTextField.setText("");
    } else {
      xTextField.setText(Integer.toString(keyframe.getNewX()));
      yTextField.setText(Integer.toString(keyframe.getNewY()));
      widthTextField.setText(Integer.toString(keyframe.getNewLength()));
      heightTextField.setText(Integer.toString(keyframe.getNewHeight()));
      rTextField.setText(Integer.toString(keyframe.getColor().getR()));
      gTextField.setText(Integer.toString(keyframe.getColor().getG()));
      bTextField.setText(Integer.toString(keyframe.getColor().getB()));
    }
  }

  //setters

  /**
   * Makes possible to delete shape.
   *
   * @param enableDeleteShape Possible to delete if true.
   */
  public void setEnableDeleteShape(Boolean enableDeleteShape) {
    deleteShapeBtn.setEnabled(enableDeleteShape);
  }

  /**
   * Makes possible to delete keyFrame.
   *
   * @param enableDeleteKeyFrame Possible to delete if true.
   */
  public void setEnableDeleteKeyFrame(Boolean enableDeleteKeyFrame) {
    deleteKeyFrameBtn.setEnabled(enableDeleteKeyFrame);
  }
}
