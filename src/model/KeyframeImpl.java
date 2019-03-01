package model;

import model.qualities.color.Texture;
import model.qualities.dimensions.Size;
import model.qualities.positions.Position;

/**
 * This class represents a collection of qualities that some shape could display at one tick.
 */
public class KeyframeImpl implements Keyframe {
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
  public KeyframeImpl(Position p, Size s, Texture t) {
    if (p == null || s == null || t == null) {
      throw new IllegalArgumentException("Position, size, or color is null");
    }
    this.p = p;
    this.s = s;
    this.t = t;
  }

  @Override
  public String toFile() {
    return p.toFile() + " " + s.toFile() + " " + t.toFile();
  }
}
