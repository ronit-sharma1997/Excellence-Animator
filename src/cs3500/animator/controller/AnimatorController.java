package cs3500.animator.controller;

/**
 * The controller interface for the Animation program. The functions here have been designed to give
 * control to the controller, and the primary operation for the controller to function (Play
 * Animation of Different Shapes).
 */

public interface AnimatorController {

  /**
   * Start the program, i.e. give control to the controller and plays the given Animation with
   * specified View.
   */
  void playAnimation();
}
