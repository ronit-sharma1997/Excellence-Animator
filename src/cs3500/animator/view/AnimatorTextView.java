package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ImmutableAnimator;
import cs3500.animator.model.ImmutableShape;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;


/**
 * Text View of the Animation. Allows us to create and output a text view of what shapes look at
 * different states in the animation.
 */
public class AnimatorTextView extends JFrame implements AnimatorView {

  private ImmutableAnimator model;

  /**
   * Represents a text rendering of the data.
   *
   * @param model - represents the model with the data to be animated
   */
  public AnimatorTextView(ImmutableAnimator model) {
    this.model = model;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void makeVisible() {
    return;
  }


  @Override
  public void refresh(List<ImmutableShape> shapes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }


  @Override
  public void animationOutput(Appendable output) {
    try {
      output.append(this.getAnimationState());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addActionListener(ActionListener listener) {
    return;
  }

  @Override
  public void addShapeListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void addSpeedChangeSliderListener(ChangeListener listener) {
    return;
  }

  @Override
  public void addKeyframeListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void addLayerListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void changeSpeed(int speed) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void showShapeListKeyframe(String name) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addShapeToAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateShapeList(Set<String> updatedShapes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateLayerList(Set<Integer> updatedLayers) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateKeyframeList(Map<String, Map<Integer, Attribute>> shapeKeyframes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeShapeFromAnimation() {

    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeKeyframe() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addKeyframetoAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission editKeyframe() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission editLayerPosition() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public File getSaveAnimationType() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public File loadAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateAnimationPanel(int height, int width, int originX, int originY) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void addScrubbingChangeSliderListener(ChangeListener listener) {
    return;
  }

  @Override
  public void setAnimationEndTime(int endTime) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void setAnimationStartTime(int startTime) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void setAnimationSliderValue(int currentTick) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  /**
   * Produces a text description of the animation.a text description of the animation so far for
   * each shape in shapeMap this is a template of how the string output would look: "shape 'shape
   * name' 'shape type'" "motion 'name' 'time' 'x-pos' 'y-pos' 'width' 'height' 'r' 'g' 'b'" (spaces
   * here but same line and then) "'name' 'time' 'x-pos' 'y-pos' 'width' 'height' 'r' 'g' 'b'" more
   * motions would go here and below for this shape, more shapes after that with their motions and
   * so on
   */
  private String getAnimationState() {
    StringBuilder output = new StringBuilder();
    if (!(model.getBoundsX() == 0
        && model.getBoundsY() == 0
        && model.getBoundsWidth() == 0
        && model.getBoundsHeight() == 0)) {
      output.append("canvas "
          + Integer.toString(model.getBoundsX()) + " "
          + Integer.toString(model.getBoundsY()) + " "
          + Integer.toString(model.getBoundsWidth()) + " "
          + Integer.toString(model.getBoundsHeight()));
      output.append("\n");
      for (Map.Entry<Integer, List<String>> layers : model.getLayers().entrySet()) {
        if (layers.getKey() != 0) {
          output.append("layer " + Integer.toString(layers.getKey()) + "\n");
        }
      }
    } else {
      throw new IllegalArgumentException("Bounds were not set");
    }
    for (Map.Entry<String, ImmutableShape> e : model.getShapeMap().entrySet()) {
      output.append(e.getValue().getResult());
      for (Map.Entry<Integer, List<String>> entry : model.getLayers().entrySet()) {
        if (entry.getValue().contains(e.getKey())) {
          int i = output.indexOf(" " + e.getValue().getName() + " ");
          int length = e.getValue().getType().getShapeType().length()
              + e.getValue().getName().length()
              + 2;
          output.insert(i + length, " " + Integer.toString(entry.getKey()));
        }
      }
    }
    String o = output.toString();
    o = o.replace(".0", "");
    return o;
  }
}
