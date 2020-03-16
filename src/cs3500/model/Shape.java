package cs3500.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Shape represents an element within our animation model.
 */
public class Shape {

  private final Map<Integer, ShapeState> log = new HashMap<>();
  private final String name;
  private final ShapeType s;
  private final int resolution = 100;
  private int recentTick;

  /**
   * Constructor for shape class.
   *
   * @param name Name of string
   */
  public Shape(String name, ShapeType s) {
    this.name = name;
    this.s = s;
    this.recentTick = 0;
  }

  /**
   * setNewState adds another ShapeState to a shape's history.
   *
   * @param tick The tick time of the new SS
   * @param p    The position of the new SS
   * @param d    The dimensions of the new SS
   * @param c    The color of the new SS
   */
  public void setNewState(int tick, Position p, Dimension d, Color c) {
    if (tick * 100 < recentTick) {
      throw new IllegalArgumentException("Invalid tick");
    }
    if (tick == 0) {
      log.put(tick, new ShapeState(tick, s, p, d, c));
      return;
    }
    tick *= 100;
    Position old_p = log.get(recentTick).getP();
    Dimension old_d = log.get(recentTick).getD();
    Color old_c = log.get(recentTick).getC();
    for (int i = recentTick + 1; i <= tick; ++i) {
      Position new_p = new Position(old_p.getX() * (tick - i) / (tick - recentTick) + p.getX() *
          (i - recentTick) / (tick - recentTick), old_p.getY() * (tick - i) / (tick - recentTick) +
          p.getY() * (i - recentTick) / (tick - recentTick));
      Dimension new_d = new Dimension(old_d.getH() * (tick - i) / (tick - recentTick) + d.getH() *
          (i - recentTick) / (tick - recentTick), old_d.getW() * (tick - i) / (tick - recentTick) +
          d.getW() * (i - recentTick) / (tick - recentTick));
      Color new_c = new Color((int) (old_c.getRed() * (double) (tick - i) / (double) (tick - recentTick)
          + c.getRed() * (double) (i - recentTick) / (double) (tick - recentTick)), (int) (old_c.getGreen()
          * (double) (tick - i) / (double) (tick - recentTick) + c.getGreen() * (double) (i -
          recentTick) / (double) (tick - recentTick)), (int) (old_c.getBlue() * (double) (tick - i) / (double)
          (tick - recentTick) + c.getBlue() * (double) (i - recentTick) / (double) (tick - recentTick)));
      log.put(i, new ShapeState(i, s, new_p, new_d, new_c));
    }
    recentTick = tick;
  }

  /**
   * getShapeState returns a ShapeState based on tick.
   *
   * @return ShapeState, s
   */
  public Map<Integer, ShapeState> getLog() {
    return log;
  }

  /**
   * getName returns the name of a Shape.
   *
   * @return String, name
   */
  public String getName() {
    return name;
  }

  public ShapeType getShapeType() {
    return s;
  }

  public int getResolution() {
    return resolution;
  }

  public int getRecentTick() {
    return recentTick;
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
    if (!(obj instanceof Shape)) {
      return false;
    }
    Shape temp = (Shape) obj;
    return (temp.name.equals(this.name) && log.equals(this.log));
  }
}