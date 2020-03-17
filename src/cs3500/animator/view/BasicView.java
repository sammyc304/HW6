package cs3500.animator.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import cs3500.model.AnimationModelInterface;
import cs3500.model.BasicAMI;
import cs3500.model.Shape;
import cs3500.model.ShapeState;

public class BasicView extends JFrame implements AMIView {

  private AMIPanel panel;
  private AnimationModelInterface model;

  public BasicView(String windowTitle, AnimationModelInterface m) {
    super(windowTitle);
    setSize(m.getDimension().getW(), m.getDimension().getH());
    setLocation(m.getPosition().getX(), m.getPosition().getY());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    model = m;
    panel = new AMIPanel();
    this.add(panel);
  }

  @Override
  public void view() throws InterruptedException {
    int tick = 0;
    while (true) {
      this.setVisible(true);
      ArrayList<ShapeState> arr = new ArrayList<>();
      int size = 0;
      for (Map.Entry<String, Shape> e : model.getElements().entrySet()) {
        try {
          if (e.getValue().getLog().get(tick) != null) {
            arr.add(e.getValue().getLog().get(tick));
          }
          if (e.getValue().getRecentTick() > size) {
            size = e.getValue().getRecentTick();
          }
        } catch (Exception ignored) {
        }
      }
      panel.update(arr);
      TimeUnit.MICROSECONDS.sleep((1000000 / model.getSpeed()) / model.getResolution());
      this.repaint();
      tick++;
      tick %= size;
    }
  }
}
