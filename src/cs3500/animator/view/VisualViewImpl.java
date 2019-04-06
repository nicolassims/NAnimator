package cs3500.animator.view;

import cs3500.animator.model.Animation;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 * This view is capable to interpret and draw an animation model on a JavaFx window.
 */
public class VisualViewImpl extends JFrame implements VisualView, TimeBasedView {

  private float ticksPerSecond = 1;
  private AnimationPanel animationPanelImpl;
  private Animation model;

  @Override
  public void displayView(Animation model) {
    this.model = model;
    setUp(model);
    class Refresh extends TimerTask {

      private int currentTick = 0;

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

  private void setUp(Animation model) {
    this.setTitle("Nicolas & Forrest Easy Animator!");
    this.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    animationPanelImpl = new AnimationPanelImpl(model);
    animationPanelImpl
        .setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    this.add((Component) animationPanelImpl, BorderLayout.CENTER);
    this.pack();
    this.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    if (!this.isVisible()) {
      setVisible(true);
    }
  }

  @Override
  public void setCurrentTick(int currentTick) {
    animationPanelImpl.setCurrentTick(currentTick);
    this.setTitle(
        "Nicolas & Forrest Easy Animator! tick(" + currentTick + "/" + model.totalDuration() + ")");
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public float getTicksPerSecond() {
    return ticksPerSecond;
  }

  @Override
  public void setTicksPerSecond(float i) {
    ticksPerSecond = i;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void peekAtTick(Animation model, int tick, String selectedShapeKey) {
    this.model = model;
    setUp(model);
    setCurrentTick(tick);
    refresh();
  }
}
