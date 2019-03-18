package cs3500.animator.view;

import cs3500.animator.model.Animation;

/**
 * This class represents the formatted text that can be used to create an Animation.
 */
public class TextualViewImpl implements View {
    private int x = 0;
    private int y = 0;
    private int w = 700;
    private int h = 500;

    @Override
    public void setTicksPerSecond(float i) { }

    @Override
    public void setDimensions(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void displayView(Animation model) {
        System.out.println("canvas " + x + " " + y + " " + w + " " + h + "\n" + model.toFile());
    }
}
