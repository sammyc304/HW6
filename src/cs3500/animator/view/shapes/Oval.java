package cs3500.animator.view.shapes;

import java.awt.*;

import cs3500.animator.view.ShapeCommand;
import cs3500.model.ShapeState;

public class Oval implements ShapeCommand {

  private ShapeState s;

  public Oval(ShapeState s) {
    this.s = s;
  }

  @Override
  public void go(Graphics2D g) {
    g.setColor(s.getC());
    g.fillOval(s.getP().getX(), s.getP().getY(), s.getD().getW(), s.getD().getH());
  }
}
