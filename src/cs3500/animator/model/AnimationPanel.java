package cs3500.animator.model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
        //never forget to call super.paintComponent!
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

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
        g2d.translate(0, this.getPreferredSize().getHeight());
        g2d.scale(1, -1);


        //draw the turtle
        //an easy way to draw the turtle would be
        //to draw it in its default position, and then
        //rotate it by heading and translating it to
        //its actual position
        g2d.setColor(Color.BLUE);
        g2d.fillOval(-2, -2, 4, 4);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-1, -1, 2, 2);
        g2d.setColor(Color.RED);
        g2d.fillOval(-8, -4, 8, 8);

        //reset the transform to what it was!
        g2d.setTransform(originalTransform);
    }


}
