package cs3500.animator.model;

import cs3500.animator.model.qualities.color.Texture;
import cs3500.animator.model.qualities.dimensions.Size;
import cs3500.animator.model.qualities.positions.Position;

/**
 * This class represents a collection of qualities that some shape could display at one tick.
 */
public class KeyframeImpl implements Keyframe {
  private int tick;
  private Position p;
  private Size s;
  private Texture t;

  /**
   * Initializes a KeyframeImpl, given a Position, Size, and Texture.
   *
   * @param p The position of the shape for one tick.
   * @param s The size of the shape for one tick.
   * @param t The color of the shape for one tick.
   * @throws IllegalArgumentException if position, size, or color are null.
   */
  public KeyframeImpl(int tick, Position p, Size s, Texture t) {
    if (tick < 0 || p == null || s == null || t == null) {
      throw new IllegalArgumentException("Position, size, or color is null, or tick is negative.");
    }
    this.tick = tick;
    this.p = p;
    this.s = s;
    this.t = t;
  }

  @Override
  public String toFile() {
    return this.tick + " " + p.toFile() + " " + s.toFile() + " " + t.toFile();
  }

  @Override
  public int getTick() {
    return tick;
  }

  @Override
  public double getFirstX() {
    return p.getX();
  }

  @Override
  public double getFirstY() {
    return p.getY();
  }

  @Override
  public double getFirstWidth() {
    return s.getWidth();
  }

  @Override
  public double getFirstHeight() {
    return s.getHeight();
  }

  @Override
  public String getFirstColors() {
    return String.valueOf(t.getRed()) + "," + String.valueOf(t.getBlue()) + "," + String.valueOf(t.getGreen());
  }
}
