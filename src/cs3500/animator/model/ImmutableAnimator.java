package cs3500.animator.model;

import java.util.List;
import java.util.Map;


/**
 * Read only Animator Interface that allows for safe retrieval of data from Animator Model.
 */
public interface ImmutableAnimator {

  /**
   * Gets the X coordinate of the top left corner of this Animator's canvas.
   *
   * @return - X coordinate of the top left corner of this Animator's canvas
   */
  int getBoundsX();

  /**
   * Gets the Y coordinate of the top left corner of this Animator's canvas.
   *
   * @return - Y coordinate of the top left corner of this Animator's canvas
   */
  int getBoundsY();

  /**
   * Gets the width of this Animator's canvas.
   *
   * @return - the width of the canvas
   */
  int getBoundsWidth();

  /**
   * Gets the height of this Animator's canvas.
   *
   * @return - the height of the canvas
   */
  int getBoundsHeight();

  /**
   * Gets the end time of the animation.
   *
   * @return - end time of the animation
   */
  int getAnimationEndTime();

  /**
   * Gets the start time of the animation.
   *
   * @return - start time of the animation
   */
  int getAnimationStartTime();

  /**
   * Gets the shapes of this Animator.
   *
   * @return - a map with the immutable shapes of this Animator
   */
  Map<String, ImmutableShape> getShapeMap();

  /**
   * Gets the keyframes of the Shapes of this Animator.
   *
   * @return - a map of a map with the immutable shapes and their keyframes of this Animator
   */
  Map<String, Map<Integer, Attribute>> getKeyframes();

  /**
   * Gets current animation state of Shapes in the Animator.
   *
   * @param currentTick - tick of the Animation
   * @return - list of {@code ImmutableShape} objects and their attributes
   */
  List<ImmutableShape> getForwardAnimationStateTick(int currentTick);

  /**
   * Gets the layers of this animation, with the shapes in each layer in the order they were added
   * to the animation.
   *
   * @return a map of layers and the shapes in them
   */
  Map<Integer, List<String>> getLayers();
}
