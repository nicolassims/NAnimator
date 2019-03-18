package cs3500.animator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * It's a Java Swing Panel Capable of drawing animations.
 */
public class AnimationPanel extends JPanel {


    public AnimationPanel() {
        super();

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
        /*
        for (int i = 0; i < this.model.getObjectKeys().size(); i++) {
            String currentKey = model.getObjectKeys().get(i);
            AnimatableObject currentObject = model.getObjectMap().get(currentKey);
            Color color = currentObject.getColor().getAsJavaAwtColor();
            int x = (int) currentObject.getPosition().getX();
            int y = (int) currentObject.getPosition().getY();
            int w = (int) currentObject.getDimensions().getWidth();
            int h = (int) currentObject.getDimensions().getHeight();
            g2d.setColor(color);
            if (currentObject.isVisible()) {
                if (currentObject instanceof Rectangle) {
                    g2d.fillRect(x, y, w, h);
                } else if (currentObject instanceof Ellipse) {
                    g2d.fillOval(x, y, w, h);
                }
            }
        }
        */

        //reset the transform to what it was!
        g2d.setTransform(originalTransform);
    }


}
