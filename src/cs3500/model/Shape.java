package cs3500.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Shape represents an element within our animation model.
 */
public class Shape {

  private final ArrayList<ShapeState> log;
  private final String name;
  private final ShapeType s;

  /**
   * Constructor for shape class.
   *
   * @param name Name of string
   * @param tick Tick
   * @param p    Position
   * @param d    Dimension
   * @param c    Color
   * @param s    ShapeType
   */
  public Shape(String name, int tick, Position p, Dimension d, Color c, ShapeType s) {
    this.log = new ArrayList<>();
    this.name = name;
    this.s = s;
    log.add(new ShapeState(tick, p, d, c));
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
    log.add(new ShapeState(tick, p, d, c));
  }

  /**
   * getLog returns a list of a ShapeStates.
   *
   * @return ArrayList of ShapeState, log
   */
  public ArrayList<ShapeState> getLog() {
    return (ArrayList<ShapeState>) log.clone();
  }

  /**
   * getShapeType returns the enum for Shape Type.
   *
   * @return ShapeType, s
   */
  public ShapeType getShapeType() {
    return s;
  }

  /**
   * getName returns the name of a Shape.
   *
   * @return String, name
   */
  public String getName() {
    return name;
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
    return (temp.log.get(temp.log.size() - 1).equals(this.log.get(this.log.size() - 1)));
  }


}