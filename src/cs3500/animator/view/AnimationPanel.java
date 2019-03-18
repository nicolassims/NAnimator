package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * It's a Java Swing Panel Capable of drawing animations.
 */
public class AnimationPanel extends JPanel {

    Animation model;
    int currentTick;

    public AnimationPanel(Animation model) {
        super();
        this.model = model;
        this.setBackground(Color.WHITE);
    }

    /**
     * Override the paintComponent method of the JPanel
     * Do NOT override paint!
     *
     * @param g
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);

    /*
    the origin of the panel is top left. In order
    to make the origin bottom left, we must "flip" the
    y coordinates so that y = height - y

    We do that by using an affine transform. The flip
    can be specified as scaling y by -1 and then
    translating by height.
     */

        AffineTransform originalTransform = g2d.getTransform();

        //the order of transforms is bottom-to-top
        //so as a result of the two lines below,
        //each y will first be scaled, and then translated

        for (Shape s : model.getShapes().values()) {
            Color color = s.getColorAt(currentTick).getAsJavaAwtColor();
            int x = (int) s.getPositionAt(currentTick).getX();
            int y = (int) s.getPositionAt(currentTick).getY();
            int w = (int) s.getDimensionsAt(currentTick).getWidth();
            int h = (int) s.getDimensionsAt(currentTick).getHeight();
            g2d.setColor(color);
            if (s.isVisible()) {
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


    public void setCurrentTick(int currentTick) {
        this.currentTick = currentTick;
    }
}
