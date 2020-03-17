package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import javax.swing.*;

import cs3500.animator.view.shapes.Oval;
import cs3500.animator.view.shapes.Rectangle;
import cs3500.animator.view.shapes.Triangle;
import cs3500.model.AnimationModelInterface;
import cs3500.model.Shape;
import cs3500.model.ShapeState;
import cs3500.model.ShapeType;

public class AMIPanel extends JPanel {

  private ArrayList<ShapeState> shapes;
  private static final Map<ShapeType, Function<ShapeState, ShapeCommand>> commands =
      Map.of(ShapeType.OVAL, Oval::new, ShapeType.RECTANGLE, Rectangle::new,
          ShapeType.TRIANGLE, Triangle::new);

  public AMIPanel() {
    this.shapes = new ArrayList<>();
  }

  public void update(ArrayList<ShapeState> s) {
    this.shapes = (ArrayList<ShapeState>) s.clone();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Collections.sort(shapes);
    for (ShapeState s : shapes) {
      if (s == null) {
        continue;
      }
      Function<ShapeState, ShapeCommand> cmd = commands.get(s.getT());
      ShapeCommand sc = cmd.apply(s);
      sc.go(g2d);
    }
  }
}
