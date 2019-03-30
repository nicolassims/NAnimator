package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Shape;
import cs3500.animator.model.qualities.color.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * It's a Java Swing Panel Capable of drawing animations.
 */
public class AnimationPanelImpl extends JPanel implements AnimationPanel {

  private Animation model;
  private int currentTick;

  /**
   * Constructs an AnimationPanelImpl based around a provided model.
   *
   * @param model The model the AnimationPanelImpl is based around.
   */
  public AnimationPanelImpl(Animation model) {
    super();
    this.model = model;
    this.setBackground(Color.WHITE);
  }

  /**
   * Override the paintComponent method of the JPanel. Do NOT override paint!
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.RED);

    AffineTransform originalTransform = g2d.getTransform();

    for (int i = 0; i < model.getShapeNames().size(); i++) {
      Shape s = model.getShapes().get(model.getShapeNames().get(i));
      List<Integer> keyframes = new ArrayList<>();
      for (int j = 0; j < s.getMotions().size(); j++) {
        keyframes.add(s.getMotions().get(j).getStartFrame().getTick());
        keyframes.add(s.getMotions().get(j).getEndFrame().getTick());
      }

      if (this.currentTick >= s.getFirstTick() && this.currentTick <= s.totalDuration()) {

        Texture t = s.getColorAt(currentTick);
        Color color = new Color((int) t.getRed(), (int) t.getGreen(), (int) t.getBlue());
        int x = (int) s.getPositionAt(currentTick).getX();
        int y = (int) s.getPositionAt(currentTick).getY();
        int w = (int) s.getSizeAt(currentTick).getWidth();
        int h = (int) s.getSizeAt(currentTick).getHeight();
        if (keyframes.contains(this.currentTick)) {
          System.out.println(s.getName() + " visible at tick " + this.currentTick + " "
              + s.getPositionAt(currentTick).toFile() + " "
              + s.getSizeAt(currentTick).toFile() + " "
              + s.getColorAt(currentTick).toFile());
        }

        g2d.setColor(color);
        if (s.getShape().equalsIgnoreCase("rectangle")) {
          g2d.fillRect(x, y, w, h);
        } else if (s.getShape().equalsIgnoreCase("ellipse")) {
          g2d.fillOval(x, y, w, h);
        }
      }
    }

    //reset the transform to what it was!
    g2d.setTransform(originalTransform);
  }


  @Override
  public void setCurrentTick(int currentTick) {
    this.currentTick = currentTick;
  }

  @Override
  public void setModel(Animation model) {
    this.model = model;
  }
}
