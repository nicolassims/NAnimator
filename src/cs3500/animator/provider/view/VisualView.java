package cs3500.animator.provider.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.provider.model.AnimationModel;

/**
 * This is an implementation of the IView interface that uses Java Swing to draw the animation.
 */
public class VisualView extends JFrame implements IView {
  private AnimationPanel animationPanel;
  private AnimationModel model;

  /**
   * Initialize the view with a model.
   *
   * @param model the model for the view
   */
  public VisualView(AnimationModel model) {
    this();
    this.model = model;
  }

  /**
   * Initialize the view.
   */
  public VisualView() {
    super();
  }

  @Override
  public void setModel(AnimationModel model) {
    this.model = model;
    animationPanel = new AnimationPanel(model);
  }

  @Override
  public void start() {
    this.setTitle("Animator");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    //Animation Panel with scroll bars
    JScrollPane animationScrollPanel;
    animationScrollPanel = new JScrollPane(animationPanel);
    animationScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    animationScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(animationScrollPanel, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
    animationPanel.startDrawing();

  }

  @Override
  public void setTempo(int tempo) {
    animationPanel.setTempo(tempo);
  }


}

