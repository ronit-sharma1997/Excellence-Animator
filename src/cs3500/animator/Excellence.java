package cs3500.animator;

import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorEditorView;
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.AnimatorTextView;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.AnimatorVisualView;
import cs3500.animator.view.SuperAnimatorExtraCreditEditorView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Implementation of Animation which can take command line inputs and create an Animation based on
 * inputs.
 */
public final class Excellence {

  /**
   * Implementation of this method takes input from the command line and processes it to either
   * start creating an Animation or create a error pop up box.
   *
   * @param args - command line arguments
   */
  public static void main(String[] args) {
    //Scanner commandInputs = new Scanner(args.toString());
    Animator animation;
    AnimationBuilder<Animator> animationBuilder = new AnimatorImpl.Builder();
    AnimatorView view;
    AnimatorController controller;
    view = new AnimatorVisualView(1, 1, 1, 1);
    Readable input = null;
    String inputFile = "";
    String outputType = "System.out";
    int speed = 1;
    String typeView = "";
    try {
      for (int count = 0; count < args.length; count += 2) {
        switch (args[count]) {
          case "-in":
            inputFile = args[count + 1];
            break;
          case "-out":
            outputType = args[count + 1];
            break;
          case "-view":
            typeView = args[count + 1];
            break;
          case "-speed":
            speed = Integer.parseInt(args[count + 1]);
            break;
          default:
            view.showErrorMessage("Invalid command");
            System.exit(0);
        }
      }
    } catch (IndexOutOfBoundsException l) {
      view.showErrorMessage("Invalid Input");
      System.exit(0);
    } catch (NumberFormatException n) {
      view.showErrorMessage("Invalid Speed");
      System.exit(0);
    }
    File inputInfo = new File(inputFile);
    try {
      input = new BufferedReader(new FileReader(inputInfo));
    } catch (FileNotFoundException e) {
      view.showErrorMessage("File Could Not Be Found");
      System.exit(0);
    }
    animation = AnimationReader.parseFile(input, animationBuilder);
    switch (typeView) {
      case "text":
        view = new AnimatorTextView(animation);
        break;
      case "visual":
        view = new AnimatorVisualView(animation.getBoundsWidth(), animation.getBoundsHeight(),
            animation.getBoundsX(), animation.getBoundsY());
        break;
      case "svg":
        view = new AnimatorSVGView(animation, speed);
        break;
      case "edit":
        view = new AnimatorEditorView(animation.getBoundsWidth(), animation.getBoundsHeight(),
            animation.getBoundsX(), animation.getBoundsY(), speed, animation.getShapeMap().keySet(),
            animation.getKeyframes());
        break;
      case "super":
        view = new SuperAnimatorExtraCreditEditorView(animation.getBoundsWidth(),
            animation.getBoundsHeight(), animation.getBoundsX(), animation.getBoundsY(), speed,
            animation.getAnimationStartTime(), animation.getAnimationEndTime(),
            animation.getShapeMap().keySet(), animation.getLayers().keySet(),
            animation.getKeyframes());
        break;
      default:
        view.showErrorMessage("Invalid View Input");
        System.exit(0);
    }
    controller = new AnimationControllerImpl(animation, view, typeView, outputType, speed);
    controller.playAnimation();
  }

}

