package cs3500.animator.provider.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.ShapeType;

/**
 * A AnimationPanel for painting shapes.
 */
class AnimationPanel extends JPanel {

  private double currentTick = 1;
  private final int delay = 40; //40ms
  private double tempo = 1.0;
  private boolean looping = true;
  private IShape selectedShape = null;

  private Timer timer;
  AnimationModel model;

  /**
   * The constructor for AnimationPanel.
   *
   * @param model the model to be used for the animation.
   */
  AnimationPanel(AnimationModel model) {
    this.model = model;

    timer = new Timer(delay, e -> {
      this.repaint();
      if (looping) {
        currentTick += (1.0 * tempo) / (1000.0 / delay);
        currentTick %= ((double)model.getLastTick() + 1);
      } else {
        double newTick = currentTick + 1.0 / (1000.0 / delay) * tempo;
        if (newTick <= model.getLastTick()) {
          currentTick = newTick;
        }
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int offsetX = 0;
    int offsetY = 0;
    for (IShape s : model.getFrame(currentTick)) {
      Graphics2D g2d = (Graphics2D) g;
      Color color = new Color(s.getColor().getR(), s.getColor().getG(), s.getColor().getB());
      g2d.setColor(color);
      g2d.setStroke(new BasicStroke(4));


      if (s.getShapeType() == ShapeType.RECTANGLE) {
        g2d.fillRect(s.getX() + offsetX, s.getY() + offsetY, s.getWidth(), s.getHeight());
        if (s == selectedShape) {
          g2d.setColor(Color.DARK_GRAY);
          g2d.drawRect(s.getX() + offsetX, s.getY() + offsetY, s.getWidth(), s.getHeight());
        }
      } else if (s.getShapeType() == ShapeType.ELLIPSE) {
        g2d.fillOval(s.getX() + offsetX, s.getY() + offsetY, s.getWidth(), s.getHeight());
        if (s == selectedShape) {
          g2d.setColor(Color.DARK_GRAY);
          g2d.drawOval(s.getX() + offsetX, s.getY() + offsetY, s.getWidth(), s.getHeight());
        }
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(model.getBoundX() + model.getBoundWidth(),
            model.getBoundY() + model.getBoundHeight());
  }

  /**
   * Set the tempo.
   * @param tempo the speed (tick per second)
   */
  void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * Start or resume drawing.
   */
  void startDrawing() {
    timer.start();
  }

  /**
   * Pause drawing.
   */
  void stopDrawing() {
    timer.stop();
  }

  /**
   * Restart drawing.
   */
  void restartDrawing() {
    currentTick = 1;
    timer.start();
  }

  /**
   * Modifies looping field, to determine if animation loops.
   */
  void setLooping(boolean looping) {
    this.looping = looping;
  }

  /**
   * Modifies currentTick, the curent time of the animation.
   * @param tick the given tick.
   */
  void setCurrentTick(int tick) {
    this.currentTick = tick;
  }

  /**
   * Setter for the selected shape.
   * @param s selected shape
   */
  void setSelectedShape(IShape s) {
    this.selectedShape = s;
  }
}