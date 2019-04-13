package cs3500.animator.provider.view;

import javax.swing.JButton;

import javax.swing.JPanel;

import javax.swing.JTextField;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

/**
 * Represents the panel for pausing, playing, changing speed, looping, and so on.
 */
class ControlPanel extends JPanel {

  private IEditorView view;
  private JButton s = new JButton("Pause");
  private JButton l = new JButton("Disable Looping");
  private JTextField f = new JTextField("20",4);

  /**
   * Constructor for ControlPanel.
   *
   * @param view the EditorView is used to have its speed manipulated.
   */
  ControlPanel(EditorView view) {
    JButton r = new JButton("Restart");
    JButton t = new JButton("Enter Speed");
    this.view = view;
    this.setLayout(new FlowLayout());
    s.setActionCommand("Pause");
    s.addActionListener(new AL());
    l.setActionCommand("Disable Looping");
    l.addActionListener(new AL());
    r.setActionCommand("Restart");
    r.addActionListener(new AL());
    t.setActionCommand("Enter");
    t.addActionListener(new AL());
    this.add(s);
    this.add(l);
    this.add(r);
    this.add(f);
    this.add(t);
    this.setVisible(true);
  }

  /**
   * A nested class to serve as the ActionListener for the ControlPanel.
   *
   */
  private class AL implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Play":
          s.setText("Pause");
          s.setActionCommand("Pause");
          view.startOrStop(true);
          break;
        case "Pause":
          s.setText("Play");
          s.setActionCommand("Play");
          view.startOrStop(false);
          break;
        case "Enable Looping":
          l.setText("Disable looping");
          l.setActionCommand("Disable Looping");
          view.setLooping(true);
          break;
        case "Disable Looping":
          l.setText("Enable looping");
          l.setActionCommand("Enable Looping");
          view.setLooping(false);
          break;
        case "Restart":
          view.restartAnimation();
          break;
        case "Enter":
          String text = f.getText();
          int speed = parse(text);
          f.setText("" + speed);
          view.setTempo(speed);
          break;
        default:
          throw new IllegalArgumentException("Invalid");
      }
    }
  }

  /**
   * Converts an inputted string into an int.
   *
   * @param s The String to be converted.
   */
  private int parse(String s) {
    int i = 1;
    if (s.substring(0, 1).equals("-")) {
      i = -1;
      s = s.substring(1, s.length());
    }
    try {
      i = i * Integer.parseInt(s);
    } catch (Exception e) {
      i = i * 20;
    }
    return i;
  }
}

