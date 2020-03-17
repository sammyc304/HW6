package cs3500.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicAMI implements the Animation Interface. This implementation is the bare minimum of what this
 * interface needs to function.
 */
public class BasicAMI implements AnimationModelInterface {

  /**
   * Only data field, represents the current list of shapes.
   */
  private final Map<String, Shape> elements;
  private Dimension dim;
  private Position pos;
  private final int resolution;
  private final int speed;

  public BasicAMI(Dimension dim, Position pos, int resolution, int speed) {
    this.dim = dim;
    this.pos = pos;
    this.resolution = resolution;
    this.speed = speed;
    this.elements = new HashMap<>();
  }

  @Override
  public void addShape(Shape s) {
    s.setResolution(resolution);
    elements.put(s.getName(), s);
  }

  @Override
  public Shape getShape(String name) {
    if (!this.elements.containsKey(name)) {
      throw new IllegalArgumentException("Shape is not in the ami");
    }

    return elements.get(name);
  }

  @Override
  public Dimension getDimension() {
    return dim;
  }

  @Override
  public Position getPosition() {
    return pos;
  }

  @Override
  public int getResolution() {
    return resolution;
  }

  public int getSpeed() {
    return speed;
  }

  public Map<String, Shape> getElements() {
    return elements;
  }

  @Override
  public void modifyShape(String name, int tick, Position p, Dimension d, Color c) {
    if (!elements.containsKey(name)) {
      throw new IllegalArgumentException(("Shape doesn't exist in the AMI"));
    }
    elements.get(name).setNewState(tick, p, d, c);
  }

  @Override
  public void removeShape(String name) {
    if (!elements.containsKey(name)) {
      throw new IllegalArgumentException(("Shape already doesn't exist in the AMI"));
    }
    elements.remove(name);
  }

  @Override
  public void removeAll() {
    elements.clear();
  }

  @Override
  public String textOutput() {
    StringBuilder ret_val = new StringBuilder();
    for (Map.Entry<String, Shape> entry : elements.entrySet()) {
      ret_val.append("\n").append("shape ").append(entry.getKey()).append(" ")
          .append(entry.getValue().getShapeType().toString()).append("\n\n");
      int size = entry.getValue().getLog().size();
      for (int i = 0; i < size; ++i) {
        if (i < size - 1) {
          ret_val.append("motion ").append(entry.getKey()).append(" ").
              append(entry.getValue().getLog().get(i).toString());
          ret_val.append("   ").append("motion ").append(entry.getKey()).append(" ").
              append(entry.getValue().getLog().get(i + 1).toString());
        }
        ret_val.append("\n");
      }
    }
    return ret_val.toString();
  }
}