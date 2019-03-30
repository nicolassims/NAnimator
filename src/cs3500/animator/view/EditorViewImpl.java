package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.AnimationReader;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents a view that contains a VisualView, and can edit it.
 */
public class EditorViewImpl extends JFrame implements EditorView, TimeBasedView, ActionListener,
    ListSelectionListener, View {

  private float ticksPerSecond;
  private int currentTick;
  private boolean isPlaying;
  private boolean isLooping = false;
  //Visual Stuff
  private JPanel mainPanel;

  private JLabel radioDisplay;
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  private JRadioButton[] objectRadioButtons;
  private JRadioButton[] keyframesRadioButtons;
  private ButtonGroup keyframeGroup;


  //Model Stuff
  private Animation model;
  private Shape currentObject;
  private String projectURL = "";
  private JPanel fileManagementPanel;
  private JPanel controlPreviewPanel;
  private JPanel objectPanel;
  private JPanel framePanel;
  private JPanel controlKeyframeEditingPanel;

  /**
   * Creates an instance of EditorViewImpl, and sets the instance's view to be a brand new,
   * untouched, VisualViewImpl.
   */
  public EditorViewImpl() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void start() {
    this.isPlaying = true;
  }

  @Override
  public void pause() {
    this.isPlaying = false;
  }

  @Override
  public void resume() {
    this.isPlaying = true;
  }

  @Override
  public void restart() {
    this.currentTick = 0;
    this.isPlaying = true;
  }

  @Override
  public void toggleLooping() {
    this.isLooping = !this.isLooping;
  }

  @Override
  public void setTicksPerSecond(float i) {
    this.ticksPerSecond = i;
  }


  /**
   * Sets up the file panel.
   */
  private void setFileManagementPanel() {
    fileManagementPanel = new JPanel();
    fileManagementPanel.setBorder(BorderFactory.createTitledBorder("file management"));
    fileManagementPanel.setLayout(new BoxLayout(fileManagementPanel, BoxLayout.PAGE_AXIS));

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    fileManagementPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel(this.projectURL);
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    fileManagementPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);
  }

  /**
   * Refreshes the scene.
   */
  private void refresh() {
    mainPanel.removeAll();
    setFileManagementPanel();
    setPreviewControls();
    setKeyframeControls();
    mainPanel.add(fileManagementPanel);
    mainPanel.add(controlPreviewPanel);
    mainPanel.add(objectPanel);
    try {
      mainPanel.add(this.framePanel);
      mainPanel.add(this.controlKeyframeEditingPanel);
    } catch (NullPointerException e) {
      //nothing should happen
    }

    setVisible(true);
  }

  /**
   * Initializes a panel.
   */
  private void setPreviewControls() {
    //add three buttons to navigate to the preview
    controlPreviewPanel = new JPanel();
    controlPreviewPanel.setLayout(new FlowLayout());
    controlPreviewPanel.setBorder(BorderFactory.createTitledBorder("Preview Controls"));

    JButton prevTick;
    JButton nextTick;
    JButton preview;
    JButton setTick;

    preview = new JButton("run a preview");
    preview.setActionCommand("preview");
    preview.addActionListener(this);

    nextTick = new JButton("next tick");
    nextTick.addActionListener(this);
    nextTick.setActionCommand("nextTick");

    prevTick = new JButton("previous tick");
    prevTick.setActionCommand("previousTick");
    prevTick.addActionListener(this);

    setTick = new JButton("set tick");
    setTick.setActionCommand("setTick");
    setTick.addActionListener(this);

    controlPreviewPanel.add(setTick);
    controlPreviewPanel.add(nextTick);
    controlPreviewPanel.add(prevTick);
    controlPreviewPanel.add(preview);
  }

  /**
   * Initializes a panel.
   */
  private void setKeyframeControls() {
    //add three buttons to navigate to the preview
    controlKeyframeEditingPanel = new JPanel();
    controlKeyframeEditingPanel.setLayout(new FlowLayout());
    controlKeyframeEditingPanel
        .setBorder(BorderFactory.createTitledBorder("KeyframeEditing Controls"));

    JButton addFrame;
    JButton removeFrame;
    JButton editFrame;
    JButton previewKeyframe;

    editFrame = new JButton("Edit Frame");
    editFrame.setActionCommand("editFrame");
    editFrame.addActionListener(this);

    removeFrame = new JButton("Remove Frame");
    removeFrame.addActionListener(this);
    removeFrame.setActionCommand("removeFrame");

    addFrame = new JButton("Add Frame");
    addFrame.setActionCommand("addFrame");
    addFrame.addActionListener(this);

    previewKeyframe = new JButton("Preview Frame");
    previewKeyframe.setActionCommand("setTick");
    previewKeyframe.addActionListener(this);

    controlKeyframeEditingPanel.add(previewKeyframe);
    controlKeyframeEditingPanel.add(removeFrame);
    controlKeyframeEditingPanel.add(addFrame);
    controlKeyframeEditingPanel.add(editFrame);
  }

  /**
   * Initializes a panel.
   */
  private void setObjects() {
    //radio buttons
    try {
      this.mainPanel.remove(this.objectPanel);
    } catch (NullPointerException e) {
      //nothing should happen here
    }
    objectPanel = new JPanel();
    objectPanel.setBorder(BorderFactory.createTitledBorder("Declared Objects"));
    objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] objectRadioButtons = new JRadioButton[this.model.getShapes().size()];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();

    for (int i = 0; i < model.getShapes().size(); i++) {
      Shape object = this.model.getShapes().get(this.model.getShapeNames().get(i));
      objectRadioButtons[i] = new JRadioButton(object.getShape() + " " + object.getName());
      objectRadioButtons[i].setSelected(false);
      objectRadioButtons[i].setActionCommand("RB:" + model.getShapeNames().get(i));
      objectRadioButtons[i].addActionListener(this);
      rGroup1.add(objectRadioButtons[i]);
      objectPanel.add(objectRadioButtons[i]);
    }
    radioDisplay = new JLabel("Which one did the user select?");
    objectPanel.add(radioDisplay);
  }

  /**
   * Initializes a panel.
   */
  private void setKeyFrames() {
    //radio buttons
    try {
      this.mainPanel.remove(this.framePanel);
    } catch (NullPointerException e) {
      //nothing should happen here
    }
    framePanel = new JPanel();
    framePanel.setSize(300, 300);
    framePanel.setBorder(BorderFactory.createTitledBorder(
        "Declared Frames for " + this.currentObject.getName() + " (" + (this.currentObject
            .getMotions().size()) + "): "));
    framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.PAGE_AXIS));
    keyframesRadioButtons = new JRadioButton[this.model.getShapes().size()];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    keyframeGroup = new ButtonGroup();

    addFirstKeyFrame();
    for (int i = 1; i < this.currentObject.getMotions().size(); i++) {
      Motion motion = currentObject.getMotions().get(i);
      System.out.println("motion " + i + " = " + motion.toFile());
      keyframesRadioButtons[i] = new JRadioButton(motion.getEndFrame().toFile());
      keyframesRadioButtons[i].setSelected(false);
      keyframesRadioButtons[i]
          .setActionCommand("RBFRAME:" + this.currentObject.getMotions().get(i).getEndFrame());
      keyframesRadioButtons[i].addActionListener(this);
      keyframeGroup.add(keyframesRadioButtons[i]);
      framePanel.add(keyframesRadioButtons[i]);
    }
    radioDisplay = new JLabel("Which one did the user select?");
    framePanel.add(radioDisplay);
  }

  private void addFirstKeyFrame() {
    if (currentObject.getMotions().size() > 0) {
      Motion motion = currentObject.getMotions().get(0);
      keyframesRadioButtons[0] = new JRadioButton(motion.getStartFrame().toFile());
      keyframesRadioButtons[0].setSelected(false);
      keyframesRadioButtons[0]
          .setActionCommand("RBFRAME:" + this.currentObject.getMotions().get(0).getEndFrame());
      keyframesRadioButtons[0].addActionListener(this);
      keyframeGroup.add(keyframesRadioButtons[0]);
      framePanel.add(keyframesRadioButtons[0]);
    }
  }

  /**
   * Initializes a panel.
   */
  private void updatePreview() {
    this.setTitle(
        "Nicolas & Luis Easy Animator! tick(" + currentTick + "/" + model.totalDuration() + ") of "
            + this.projectURL);
    VisualView editPanel = new VisualViewImpl();
    editPanel.setTitle("Preview at tick: " + currentTick);
    editPanel.peekAtTick(this.model, this.currentTick, "");
  }

  /**
   * Displays an error.
   */
  private void displayError(String e) {
    JOptionPane.showMessageDialog(EditorViewImpl.this, e,
        "ERROR", JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Opens ands runs a preview window.
   */
  private void runPreview() {

    VisualView preview = new VisualViewImpl();
    preview.setTicksPerSecond(this.ticksPerSecond);
    preview.displayView(this.model);
  }

  @Override
  public void actionPerformed(ActionEvent action) {
    if (action.getActionCommand().startsWith("RB:")) {
      String selectedKey = action.getActionCommand().substring(3);
      this.currentObject = this.model.getShapes().get(selectedKey);
      setKeyFrames();
      refresh();
    }
    switch (action.getActionCommand()) {
      case "nextTick":
        this.currentTick++;
        updatePreview();
        break;
      case "previousTick":
        this.currentTick--;
        updatePreview();
        break;
      case "setTick":
        try {
          this.currentTick = Integer
              .parseInt(JOptionPane.showInputDialog("Select a tick to preview:"));
        } catch (InputMismatchException e) {
          displayError(e.getMessage());
        }
        updatePreview();
        break;
      case "preview":
        try {
          this.ticksPerSecond = Integer
              .parseInt(JOptionPane.showInputDialog("Select a ticksPerSecond "));
          runPreview();
        } catch (InputMismatchException e) {
          displayError(e.getMessage());
        }
        break;
      case "Color chooser":
        Color col = JColorChooser.showDialog(EditorViewImpl.this, "Choose a color",
            colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(col);
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(EditorViewImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
          this.projectURL = f.getAbsolutePath();
          loadNewFile();
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(EditorViewImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Message":
        JOptionPane.showMessageDialog(EditorViewImpl.this, "This is a demo message", "Message",
            JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(EditorViewImpl.this, "You are about to be deleted.",
            "Last Chance", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(EditorViewImpl.this, "You have been deleted.", "Too late",
            JOptionPane.ERROR_MESSAGE);
        JOptionPane
            .showMessageDialog(EditorViewImpl.this, "Please start again.", "What to do next",
                JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Input":
        break;
      case "Option": {
        String[] options = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "seis", "siete", "ocho",
            "nueve", "dies"};
        int retvalue = JOptionPane
            .showOptionDialog(EditorViewImpl.this, "Please choose number", "Options",
                JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);
      }
      break;
      default:
        throw new IllegalArgumentException("Unsupported commandtype.");
    }
  }

  /**
   * Loads a new file.
   */
  public void loadNewFile() {
    try {
      new AnimationReader();
      this.model = AnimationReader
          .parseFile(new FileReader(new File(this.projectURL).getAbsolutePath()),
              new AnimationImpl.Builder());
      this.refresh();
    } catch (FileNotFoundException e) {
      this.displayError(e.getMessage());
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't know which list called this callback, because we're using it
    // for two lists.  In practice, you should use separate listeners
    JOptionPane.showMessageDialog(EditorViewImpl.this,
        "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    // Regardless, the event information tells us which index was selected
    JOptionPane.showMessageDialog(EditorViewImpl.this,
        "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
    // This gets us the string value that's currently selected
  }

  @Override
  public void displayView(Animation model) {
    setTitle("Animator Editor");
    setSize(800, 800);
    this.model = model;
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    setFileManagementPanel();
    setPreviewControls();
    setObjects();
    refresh();
    this.setVisible(true);
  }
}
