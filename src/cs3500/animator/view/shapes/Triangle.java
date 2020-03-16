package cs3500.animator.view.shapes;

import java.awt.*;

import cs3500.animator.view.ShapeCommand;
import cs3500.model.ShapeState;

public class Triangle implements ShapeCommand {

  private ShapeState s;

  public Triangle(ShapeState s) {
    this.s = s;
  }

  @Override
  public void go(Graphics2D g) {
    g.setColor(s.getC());
    g.fillPolygon(new int[]{(s.getP().getX() - (s.getD().getW() / 2)), s.getP().getX(),
        (s.getP().getX() + (s.getD().getW() / 2))}, new int[]{(s.getP().getY() +
        (s.getD().getH() / 2)), (s.getP().getY() - (s.getD().getH() / 2)), (s.getP().getY() +
        (s.getD().getH() / 2))}, 3);
  }
}
