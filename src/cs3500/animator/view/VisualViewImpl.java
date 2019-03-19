package cs3500.animator.view;

import cs3500.animator.model.Animation;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This view is capable to interpret and draw an animation model on a JavaFx window.
 */
public class VisualViewImpl extends JFrame implements View {

  private float ticksPerSecond = 1;
  private JButton commandButton, quitButton;
  private JPanel buttonPanel;
  private AnimationPanel animationPanel;
  private JTextField input;
  private JLabel display;
  private int x = 0;
  private int y = 0;
  private int w = 700;
  private int h = 500;
  private String outputDestination;


  @Override
  public void setTicksPerSecond(float i) {
    ticksPerSecond = i;
  }

  @Override
  public void setDimensions(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public void displayView(Animation model) {
    this.setTitle("Nick & Luis Easy Animator!");
    this.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel(model);
    animationPanel.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    this.add(animationPanel, BorderLayout.CENTER);
    this.pack();

    setVisible(true);

    class Refresh extends TimerTask {

      int currentTick = 0;

      public void run() {
        if (currentTick <= model.totalDuration()) {
          currentTick++;
          setCurrentTick(currentTick);
          refresh();
        } else {
          currentTick = 0;
        }
      }
    }

    // And From your main() method or any other method
    java.util.Timer timer = new Timer();
    timer.schedule(new Refresh(), 0, (long) (1000 / this.ticksPerSecond));
  }

  @Override
  public void setOutputDestination(String outArg) {
    this.outputDestination = outArg;
  }

  @Override
  public void setCurrentTick(int currentTick) {
    animationPanel.setCurrentTick(currentTick);
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}
