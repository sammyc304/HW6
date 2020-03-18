package cs3500.animator.view;

import cs3500.model.AnimationModelInterface;

/**
 * TextView implements AMIView and outputs the textual animation to the console.
 */
public class TextView implements AMIView {

  private AnimationModelInterface model;

  /**
   * Constructor for TextView.
   *
   * @param m Model used for animation rendering
   */
  public TextView(AnimationModelInterface m) {
    model = m;
  }

  @Override
  public void view() {
    System.out.print(model.textOutput());
  }
}
