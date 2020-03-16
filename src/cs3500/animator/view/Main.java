package cs3500.animator.view;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import cs3500.model.AnimationModelInterface;
import cs3500.model.BasicAMI;
import cs3500.model.Dimension;
import cs3500.model.Position;
import cs3500.model.Shape;
import cs3500.model.ShapeType;

/**
 * Run a Tic Tac Toe game interactively.
 */
public class Main {
  /**
   * Run a Tic Tac Toe game interactively.
   */
  public static void main(String[] args) throws InterruptedException {
    // Old News: console-based game:
    //new TicTacToeConsoleController(new InputStreamReader(System.in),
    //    System.out).playGame(new TicTacToeModel());

    // New Hotness: Graphical User Interface:
    // 1. Create an instance of the model.
    // 2. Create an instance of the view.
    // 3. Create an instance of the controller, passing the view to its constructor.
    // 4. Call playGame() on the controller.
    AnimationModelInterface model = new BasicAMI(new Dimension(500, 500), new Position(200, 200));
    BasicView view = new BasicView("Animation", model);
    Shape s = new Shape("First", ShapeType.OVAL);
    s.setNewState(0, new Position(100, 100), new Dimension(50, 50), Color.BLUE);
    s.setNewState(1, new Position(150, 150), new Dimension(100, 100), Color.GREEN);
    s.setNewState(2, new Position(200, 200), new Dimension(150, 150), Color.YELLOW);
    s.setNewState(3, new Position(150, 150), new Dimension(100, 100), Color.GREEN);
    s.setNewState(4, new Position(100, 100), new Dimension(50, 50), Color.BLUE);
    s.getName();
    model.addShape(s);
    view.view();
    /*model.addShape(s);
    Shape d = new Shape("Second", ShapeType.TRIANGLE);
    d.setNewState(0,new Position(50,50), new Dimension(50,50), Color.BLUE);
    d.setNewState(10,new Position(100,100), new Dimension(50,50), Color.GREEN);
    model.addShape(d);
    view.view();*/
  }
}
