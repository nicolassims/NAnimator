package model;

import model.qualities.color.Color;
import model.qualities.dimensions.Size;
import model.qualities.positions.Position;

public class KeyframeImpl implements Keyframe {
  private Position p;
  private Size s;
  private Color c;

  public KeyframeImpl(Position p, Size s, Color c) {
    if (p == null || s == null || c == null) {
      throw new IllegalArgumentException("Position, size, or color is null");
    }
    this.p = p;
    this.s = s;
    this.c = c;
  }

  @Override
  public String toFile() {
    return p.getX() + " " + p.getY() + " " + s.getWidth() + " " +  s.getHeight() + " " + c.getRed()
        + " " + c.getGreen() + " " + c.getBlue();
  }
}
