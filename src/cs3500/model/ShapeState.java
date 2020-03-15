package cs3500.model;

import java.awt.Color;
import java.util.Objects;

/**
 * ShapeState represents a shape at a single moment. A list of these make up the lifespan of a
 * shape.
 */
public class ShapeState {

  private final int tick;
  private final Position p;
  private final Dimension d;
  private final Color c;

  /**
   * Constructor for ShapeState.
   *
   * @param tick Tick
   * @param p    Position
   * @param d    Dimension
   * @param c    Color
   */
  public ShapeState(int tick, Position p, Dimension d, Color c) {
    if (tick < 0) {
      throw new IllegalArgumentException("Invalid Tick");
    }
    this.tick = tick;
    this.p = new Position(p);
    this.d = new Dimension(d);
    this.c = new Color(c.getRGB());
  }

  @Override
  public String toString() {
    return tick + " " + p.toString() +
        " " + d.toString() + c.getRed() +
        " " + c.getGreen() + " " + c.getBlue();
  }

  /**
   * Returns position.
   *
   * @return p
   */
  public Position getP() {
    return this.p;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ShapeState)) {
      return false;
    }
    ShapeState temp = (ShapeState) obj;
    return (temp.tick == this.tick && temp.getP().equals(this.p)
        && temp.d.equals(this.d) && temp.c.equals(this.c));
  }
}
