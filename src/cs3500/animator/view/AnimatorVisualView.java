package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ImmutableShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * Visual View of the Animation. Allows us to visually display the shapes at different states in the
 * animation.
 */
public class AnimatorVisualView extends JFrame implements AnimatorView {

  private AnimationPanel animationPanel;

  /**
   * Constructs a panel to draw the visual.
   *
   * @param width - the specified width of the window
   * @param height - the specified height of the window
   * @param originX - the x coordinate origin specified by the user
   * @param originY - the y coordinate origin specified by the user
   */
  public AnimatorVisualView(int width, int height, int originX, int originY) {
    super();
    JScrollPane scrollPane;
    this.setTitle("Animator");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel(originX, originY);
    animationPanel.setPreferredSize(new Dimension(width, height));
    scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void makeVisible() {

    this.setVisible(true);
  }

  @Override
  public void refresh(List<ImmutableShape> shapes) {
    animationPanel.setCurrentShapes(shapes);
    this.repaint();
  }


  @Override
  public void animationOutput(Appendable output) {
    try {
      output.append("Playing Visual Animation!");
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
    return;
  }

}
