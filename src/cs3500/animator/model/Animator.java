package cs3500.animator.model;

/**
 * Represents the animation of specified shapes and motions on them described in words by the user.
 */
public interface Animator extends ImmutableAnimator {

  /**
   * Allows user to add a new shape to the animation. Provides an effective and simple way to store
   * the initial state of a Shape when it's added to the animation.
   *
   * @param name - represents the name of the shape
   * @param type - represents the type of shape it is
   * @throws IllegalArgumentException - if the name or type is null, if the name is empty, the type
   *         is not a Shape
   */
  void addShape(String name, String type) throws IllegalArgumentException;

  /**
   * Adds a shape to this animation, as well as puts that shape into an existing layer in the
   * animation.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   * @param layer the layer to add the shape to
   */
  void addShape(String name, String type, int layer);

  /**
   * Removes a shape from this animation.
   *
   * @param name - the name of the Shape to be deleted
   */
  void deleteShape(String name);

  /**
   * Edits a keyframe of a Shape's motions within the animation.
   *
   * @param name The name of the shape to edit the keyframe of
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   */
  void editKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Edits a keyframe of a Shape's motions within the animation.
   *
   * @param name The name of the shape to edit the keyframe of
   * @param t The time for this keyframe
   * @param keyframeAttributes the attributes of the keyframe
   */
  void editKeyframe(String name, int t, Attribute keyframeAttributes);

  /**
   * Deletes a keyframe of a Shape within the animation.
   *
   * @param name - the name of the Shape where the keyframe is
   * @param t - the keyframe to be deleted
   */
  void deleteKeyframe(String name, int t);

  /**
   * Adds an individual keyframe to the animation.
   *
   * @param name The name of the shape
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   */
  void addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Adds an individual keyframe to the animation.
   *
   * @param name The name of the shape to edit the keyframe of
   * @param t The time for this keyframe
   * @param keyframeAttributes the attributes of the keyframe
   */
  void addKeyframe(String name, int t, Attribute keyframeAttributes);

  /**
   * Adds a layer to this animation.
   *
   * @param layer represents a non-negative layer to be added to the animation
   */
  void addLayer(int layer);

  /**
   * Deletes a layer in the animation and all of the shapes inside of it.
   *
   * @param layer the layer to be deleted
   */
  void deleteLayer(int layer);

  /**
   * Changes a layer from one existing layer to another, moving all of the shapes to the new layer,
   * and reordering the rest of the layers as a result.
   *
   * @param fromLayer the layer to be moved
   * @param toLayer the layer the changing layer will be moved to
   */
  void changeLayer(int fromLayer, int toLayer);

  /**
   * Resets all the current motions for all the shapes.
   */
  void resetCurrentMotions();
}