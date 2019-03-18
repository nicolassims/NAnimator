package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VisualViewImpl extends JFrame implements View {
    private int ticksPerSecond = 60;
    private JButton commandButton, quitButton;
    private JPanel buttonPanel;
    private AnimationPanel animationPanel;
    private JTextField input;
    private JLabel display;
    private int x = 0;
    private int y = 0;
    private int w = 700;
    private int h = 500;

    public VisualViewImpl() {
        super();
        this.setTitle("Turtles!");
        this.setSize(w, h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //use a borderlayout with drawing panel in center and button panel in south
        this.setLayout(new BorderLayout());
        animationPanel = new AnimationPanel();
        animationPanel.setPreferredSize(new Dimension(w, h));
        this.add(animationPanel, BorderLayout.CENTER);

        //button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        //input textfield
        input = new JTextField(15);
        buttonPanel.add(input);

        //buttons
        commandButton = new JButton("Execute");
        buttonPanel.add(commandButton);

        //quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        buttonPanel.add(quitButton);

        this.pack();

        //set the view visible
        this.setVisible(true);

    }

    @Override
    public void setTicksPerSecond(int i) {
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

    }


}
