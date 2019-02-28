package model;

import model.qualities.color.Texture;
import model.qualities.dimensions.Size;
import model.qualities.positions.Position;

public class KeyframeImpl implements Keyframe {
  private Position p;
  private Size s;
  private Texture t;

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
    return p.getX() + " " + p.getY() + " "
        + s.getWidth() + " " +  s.getHeight() + " "
        + t.getRed() + " " + t.getGreen() + " " + t.getBlue();
  }
}
