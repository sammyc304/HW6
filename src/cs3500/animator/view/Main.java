package cs3500.animator.view;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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
    AnimationModelInterface model = null;
    BasicView view = null;
    try {
      File f = new File("src/cs3500/resources/big-bang-big-crunch.txt");
      Scanner read = new Scanner(f);
      while (read.hasNextLine()) {
        String data = read.nextLine();
        String[] tokens = data.split("\\s+");
        switch (tokens[0]) {
          case "canvas":
            model = new BasicAMI(new Dimension(Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[4])), new Position(Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2])), 1, 5000);
            view = new BasicView("Animation", model);
            break;
          case "shape":
            if (model != null) {
              model.addShape(new Shape(tokens[1], tokens[2]));
            }
            break;
          case "motion":
            if (model != null) {
              model.getShape(tokens[1]).setNewState(tokens[2], tokens[3], tokens[4],
                  tokens[6], tokens[5], tokens[7], tokens[8], tokens[9]);
              model.getShape(tokens[1]).setNewState(tokens[10], tokens[11], tokens[12],
                  tokens[14], tokens[13], tokens[15], tokens[16], tokens[17]);
            }
            break;
          default:
            throw new IllegalArgumentException("This is bad: " + data);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    //AnimationModelInterface model = new BasicAMI(new Dimension(500, 500), new Position(200, 200), 100, 10);
    //BasicView view = new BasicView("Animation", model);
    /*model.addShape(new Shape("First", ShapeType.OVAL));
    model.getShape("First").setNewState(0, new Position(100, 100), new Dimension(100, 100), Color.RED);
    model.getShape("First").setNewState(10, new Position(250, 250), new Dimension(200, 200), Color.ORANGE);
    model.getShape("First").setNewState(11, new Position(350, 350), new Dimension(100, 100), Color.RED);
    model.getShape("First").setNewState(12, new Position(250, 250), new Dimension(200, 200), Color.ORANGE);
    model.getShape("First").setNewState(22, new Position(100, 100), new Dimension(100, 100), Color.RED);*/
    view.view();
    /*model.addShape(s);
    Shape d = new Shape("Second", ShapeType.TRIANGLE);
    d.setNewState(0,new Position(50,50), new Dimension(50,50), Color.BLUE);
    d.setNewState(10,new Position(100,100), new Dimension(50,50), Color.GREEN);
    model.addShape(d);
    view.view();*/
  }
}
