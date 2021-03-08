package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ImmutableShape;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;


/**
 * The view interface. Allows views to communicate with controller.
 */
public interface AnimatorView {
  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   *
   * @param error - String message
   */
  void showErrorMessage(String error);

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Signal the view to draw itself.
   * @param shapes - List of Shapes and their properties
   */
  void refresh(List<ImmutableShape> shapes);

  /**
   * Appends the animation to an Appendable.
   * @param output - Appendable output
   */
  void animationOutput(Appendable output);

  /**
   * Sets a listener for any action events that may occur for certain JFrame Components.
   * @param listener - ActionListener waiting for an event
   */
  void addActionListener(ActionListener listener);

  /**
   * Sets a listener for the Shape list within the view.
   * @param listener - ListSelectionListener waiting for an event
   */
  void addShapeListListener(ListSelectionListener listener);

  /**
   * Sets a listener for the Speed Slider within the view.
   * @param listener - ChangeListener waiting for an event
   */
  void addSpeedChangeSliderListener(ChangeListener listener);

  /**
   * Sets a listener for the Keyframe list within the view.
   * @param listener - ListSelectionListener waiting for an event
   */
  void addKeyframeListListener(ListSelectionListener listener);

  /**
   * Sets a listener for the Keyframe list within the view.
   * @param listener - ListSelectionListener waiting for an event
   */
  void addLayerListListener(ListSelectionListener listener);

  /**
   * Sets the updated speed within the view.
   * @param speed - the updated speed based on the Speed Slider
   */
  void changeSpeed(int speed);

  /**
   * Displays the list of keyframes within the view based on the input Shape provided.
   * @param shapeName - string that represents the shape name
   */
  void showShapeListKeyframe(String shapeName);

  /**
   * Displays the keyframe attributes such as size and color depending on the keyframe provided.
   * @param keyframe - number that represents a keyframe
   */
  void showKeyframeAttributes(int keyframe);

  /**
   * Gets a shape to be added to the animation based on input from the view.
   * @return DataSubmission - the shape to be added to the animation
   */
  DataSubmission addShapeToAnimation();

  /**
   * Gets a layer to be added to the animation based on input from the view.
   * @return DataSubmission - the layer to be added to the animation
   */
  DataSubmission addLayerToAnimation();

  /**
   * Updates the shape list of the view.
   * @param updatedShapes - set of Shape Names of the updated shapes within the animation
   */
  void updateShapeList(Set<String> updatedShapes);

  /**
   * Updates the shape list of the view.
   * @param updatedLayers - set of Shape Names of the updated shapes within the animation
   */
  void updateLayerList(Set<Integer> updatedLayers);

  /**
   * Updates the keyframe list of a Shape in the view.
   * @param shapeKeyframes - map of Shape Name to map of a Keyframe to a Keyframe's Attributes
   */
  void updateKeyframeList(Map<String, Map<Integer, Attribute>> shapeKeyframes);

  /**
   * Retrieves name of the Shape to be deleted in the animation.
   * @return DataSubmission that represents Shape's name
   */
  DataSubmission removeShapeFromAnimation();

  /**
   * Retrieves layer number to be deleted in the animation.
   * @return DataSubmission that represents layer
   */
  DataSubmission removeLayerFromAnimation();

  /**
   * Retrieves the keyframe to be deleted in the animation.
   * @return DataSubmission that contains Shape's Name and the specific keyframe to remove
   */
  DataSubmission removeKeyframe();

  /**
   * Gets the keyframe to be added to the animation based on input from the view.
   * @return DataSubmission that contains Shape's Name and the specific keyframe to add
   */
  DataSubmission addKeyframetoAnimation();

  /**
   * Retrieves the edited keyframe to be modified in the animation based on input from the view.
   * @return DataSubmission that contains Shape's Name and the modified keyframe
   */
  DataSubmission editKeyframe();

  /**
   * Retrieves the edited layer to be modified in the animation based on input from the view.
   * @return DataSubmission that contains Shape's Name and the modified layer
   */
  DataSubmission editLayerPosition();

  /**
   * Gets the export file type of the animation defined by user input.
   * @return File that represents the specific type of export
   */
  File getSaveAnimationType();

  /**
   * Retrieve the import file of the animation defined by user input.
   * @return File that represents an import of an animation
   */
  File loadAnimation();

  /**
   * Updates the dimensions and origin point of an Animation based on input.
   * @param height - desired height of animation visual
   * @param width - desired width of animation visual
   * @param originX - x coordinate of origin point
   * @param originY - y coordinate of origin point
   */
  void updateAnimationPanel(int height, int width, int originX, int originY);

  /**
   * Sets a listener for the Scrubbing Slider within the view.
   *
   * @param listener - ChangeListener waiting for an event
   */
  void addScrubbingChangeSliderListener(ChangeListener listener);

  /**
   * Sets the animation end time in the view.
   *
   * @param endTime - end time of the animation
   */
  void setAnimationEndTime(int endTime);

  /**
   * Sets the animation start time in the view.
   *
   * @param startTime - start time of the animation
   */
  void setAnimationStartTime(int startTime);

  /**
   * Sets the value of the scrubbing slider of the Animation.
   * @param currentTick - the current tick of the animation
   */
  void setAnimationSliderValue(int currentTick);

}

