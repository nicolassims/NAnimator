package cs3500.animator.view;

import cs3500.animator.model.Animation;

public interface View {

    /**
     * Sets the number of unitless "ticks" that happen per second. Default is 60.
     */
    void setTicksPerSecond(int i);

    /**
     * Displays the model through some visual form.
     */
    void displayView(Animation model);
}
