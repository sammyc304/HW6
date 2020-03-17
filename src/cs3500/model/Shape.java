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
  private int resolution;
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

  public Shape(String name, String type) {
    this.name = name;
    switch (type) {
      case "rectangle":
        this.s = ShapeType.RECTANGLE;
        break;
      case "ellipse":
        this.s = ShapeType.OVAL;
        break;
      default:
        this.s = null;
        break;
    }
    this.recentTick = 0;
  }

  public void setResolution(int resolution) {
    this.resolution = resolution;
  }

  public void setNewState(String tick, String px, String py, String dh, String dw,
                          String cr, String cg, String cb) {
    setNewState(Integer.parseInt(tick), new Position(Integer.parseInt(px), Integer.parseInt(py)),
        new Dimension(Integer.parseInt(dh), Integer.parseInt(dw)), new Color(Integer.parseInt(cr),
            Integer.parseInt(cg), Integer.parseInt(cb)));
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
    tick *= resolution;
    if (tick < recentTick) {
      throw new IllegalArgumentException("Invalid tick");
    }
    if (log.isEmpty()) {
      log.put(tick, new ShapeState(tick, s, p, d, c));
      recentTick = tick;
      return;
    }
    Position old_p = log.get(recentTick).getP();
    Dimension old_d = log.get(recentTick).getD();
    Color old_c = log.get(recentTick).getC();
    for (int i = recentTick + 1; i <= tick; ++i) {
      Position new_p = new Position((int) Math.round(old_p.getX() * (double) (tick - i) /
          (double) (tick - recentTick) + p.getX() * (double) (i - recentTick) /
          (double) (tick - recentTick)), (int) Math.round(old_p.getY() * (double) (tick - i) /
          (double) (tick - recentTick) + p.getY() * (double) (i - recentTick) /
          (double) (tick - recentTick)));
      Dimension new_d = new Dimension((int) Math.round(old_d.getH() * (double) (tick - i) /
          (double) (tick - recentTick) + d.getH() * (double) (i - recentTick) /
          (double) (tick - recentTick)), (int) Math.round(old_d.getW() * (double) (tick - i) /
          (double) (tick - recentTick) + d.getW() * (double) (i - recentTick) /
          (double) (tick - recentTick)));
      Color new_c = new Color((int) Math.round(old_c.getRed() * (double) (tick - i) /
          (double) (tick - recentTick) + c.getRed() * (double) (i - recentTick) /
          (double) (tick - recentTick)), (int) Math.round(old_c.getGreen() * (double) (tick - i) /
          (double) (tick - recentTick) + c.getGreen() * (double) (i - recentTick) /
          (double) (tick - recentTick)), (int) Math.round(old_c.getBlue() * (double) (tick - i) /
          (double) (tick - recentTick) + c.getBlue() * (double) (i - recentTick) /
          (double) (tick - recentTick)));
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