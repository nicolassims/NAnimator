package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.AnimationReader;
import cs3500.animator.model.Shape;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;

/**
 * This class represents a view that contains a VisualView, and can edit it.
 */
public class EditorViewImpl extends JFrame implements EditorView, TimeBasedView, ActionListener, ItemListener,
        ListSelectionListener, KeyListener, View {
    private VisualView view;
    private float ticksPerSecond;
    private int currentTick;
    private boolean isPlaying;
    private boolean isLooping = false;
    //Visual Stuff
    private JPanel mainPanel;
    private JScrollPane mainScrollPane;

    private JLabel radioDisplay;
    private JLabel colorChooserDisplay;
    private JLabel fileOpenDisplay;
    private JLabel fileSaveDisplay;

    private VisualView preview;


    //Model Stuff
    private Animation model;
    private int speed;
    private Shape currentObject;
    private String projectURL = "";
    private JPanel fileManagementPanel;
    private JPanel objectStatusPanel;
    private JPanel controlPreviewPanel;
    private JPanel objectPanel;

    /**
     * Creates an instance of EditorViewImpl, and sets the instance's view to be a brand new,
     * untouched, VisualViewImpl.
     */
    public EditorViewImpl() {
        this(new VisualViewImpl());
    }

    /**
     * Creates an instance of EditorViewImpl, and sets the instance's view to be equal to the
     * passed-in VisualView variable.
     *
     * @param view The VisualView that will become the EditorViewImpl's view to be edited.
     */
    public EditorViewImpl(VisualView view) {
        this.view = view;
        this.ticksPerSecond = view.getTicksPerSecond();
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

    private void refresh() {
        mainPanel.removeAll();
        setFileManagementPanel();
        setObjectStatusPanel();
        setPreviewControls();
        setObjects();
        updatePreview();
        mainPanel.add(fileManagementPanel);
        mainPanel.add(controlPreviewPanel);
        mainPanel.add(objectPanel);
        mainPanel.add(objectStatusPanel);
    }

    /**
     * Initializes a panel.
     */
    private void setObjectStatusPanel() {

        //dialog boxes
        objectStatusPanel = new JPanel();
        objectStatusPanel.setBorder(BorderFactory.createTitledBorder("selected object attributes"));
        objectStatusPanel.setLayout(new BoxLayout(objectStatusPanel, BoxLayout.PAGE_AXIS));

        try {
            //color chooser
            JPanel colorChooserPanel = new JPanel();
            colorChooserPanel.setLayout(new FlowLayout());
            objectStatusPanel.add(colorChooserPanel);

            JLabel colorLabel = new JLabel(
                    this.currentObject.getName() + " color: " + this.currentObject.getColorAt(this.currentObject.getFirstTick())
                            .getQualities());
            colorChooserPanel.add(colorLabel);

            JButton colorChooserButton = new JButton("Edit color");
            colorChooserButton.setActionCommand("Color chooser");
            colorChooserButton.addActionListener(this);
            colorChooserPanel.add(colorChooserButton);
            colorChooserDisplay = new JLabel("      ");
            colorChooserDisplay.setOpaque(true); //so that background color shows up
            colorChooserDisplay.setBackground(Color.WHITE);
            colorChooserPanel.add(colorChooserDisplay);
        } catch (NullPointerException e) {
            JLabel label = new JLabel("There is no object selected");
            objectStatusPanel.add(label);
        }
    }

    /**
     * Initializes a panel.
     */
    private void setPreviewControls() {
        //add three buttons to navigate to the preview
        controlPreviewPanel = new JPanel();
        controlPreviewPanel.setLayout(new FlowLayout());
        controlPreviewPanel.setBorder(BorderFactory.createTitledBorder("Preview Controls"));

        JButton prevTick, nextTick, preview, setTick;

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
    private void setObjects() {
        //radio buttons
        objectPanel = new JPanel();
        objectPanel.setBorder(BorderFactory.createTitledBorder("Declared Objects"));
        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.PAGE_AXIS));
        JRadioButton[] radioButtons = new JRadioButton[this.model.getShapes().size()];

        //buttons groups are used to combine radio buttons. Only one radio
        // button in each group can be selected.
        ButtonGroup rGroup1 = new ButtonGroup();

        for (int i = 0; i < model.getShapes().size(); i++) {
            Shape object = this.model.getShapes().get(this.model.getShapeNames().get(i));
            radioButtons[i] = new JRadioButton(object.getShape() + " " + object.getName());
            radioButtons[i].setSelected(false);
            radioButtons[i].setActionCommand("RB:" + model.getShapeNames().get(i));
            radioButtons[i].addActionListener(this);
            rGroup1.add(radioButtons[i]);
            objectPanel.add(radioButtons[i]);
        }
        radioDisplay = new JLabel("Which one did the user select?");
        objectPanel.add(radioDisplay);
    }

    /**
     * Initializes a panel.
     */
    private void updatePreview() {
        // preview.setModel(this.model.atTick(currentTick));
        preview.setTitle("Preview at tick: " + currentTick);
        try {
            // preview.setSelectedObject(this.currentObject.getName());
        } catch (NullPointerException e) {

        }
        preview.refresh();
        preview.makeVisible();
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
        this.preview.displayView(this.model);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().startsWith("RB:")) {
            String selectedKey = arg0.getActionCommand().substring(3);
            this.currentObject = this.model.getShapes().get(selectedKey);
            updatePreview();
            this.mainPanel.remove(this.objectStatusPanel);
            setObjectStatusPanel();
            this.mainPanel.add(this.objectStatusPanel);
        }
        switch (arg0.getActionCommand()) {
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
                    this.speed = Integer
                            .parseInt(JOptionPane.showInputDialog("Select a speed"));
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
                    System.out.println(this.projectURL);
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
        }
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub
        String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

        switch (who) {

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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void displayView(Animation model) {
        ((TimeBasedView) view).setTicksPerSecond(this.ticksPerSecond);
        System.out.println(view.getTicksPerSecond());
        view.displayView(model);
        setTitle("Animator Editor");
        setSize(400, 400);
        this.model = model;
        mainPanel = new JPanel();
        //for elements to be arranged vertically within this panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.addKeyListener(this);
        //scroll bars around this main panel
        mainScrollPane = new JScrollPane(mainPanel);
        add(mainScrollPane);

        preview = new VisualViewImpl();
        preview.setTitle("WELCOME! this is your first Preview of the animation at tick: " + currentTick);
        preview.displayView(model);

        setFileManagementPanel();
        setObjectStatusPanel();
        setPreviewControls();
        setObjects();
        refresh();
    }
}
