package cs3500.animator.view;

import java.awt.Graphics2D;

/**
 * ShapeCommand is an interface to control what gets painted given a specific shape type.
 */
public interface ShapeCommand {

  /**
   * Go makes the command execute.
   *
   * @param g Passing along the graphics object so this method can paint on a given JPanel
   */
  void goExecute(Graphics2D g);
}
