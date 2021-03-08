package cs3500.animator.model;

import java.util.List;

/**
 * Represents a motion that occurs on a Shape.
 */
public interface Motion {

  /**
   * Returns the time during the animation that this Motion begins.
   *
   * @return - the time that this Motion begins
   */
  int getStartTime();

  /**
   * Returns the time during the animation that this Motion ends.
   *
   * @return - the time that this Motion ends
   */
  int getEndTime();

  /**
   * Returns a copy of this Motion.
   *
   * @return a copy of this Motion including all of the same field values
   */
  Motion copyMotion();

  /**
   * Returns the previous attribute before the Motion.
   *
   * @return previous state of an attribute before a Motion
   */
  int[] getPreviousState();

  /**
   * Returns the new State after the Motion.
   *
   * @return new state of an attribute after a Motion
   */
  int[] getNewState();

  /**
   * Returns the previous attribute before the Motion.
   *
   * @return previous state of an attribute before a Motion
   */
  Attribute getPreviousAttribute();

  /**
   * Returns the new attribute after the Motion.
   *
   * @return new state of an attribute after a Motion
   */
  Attribute getNewAttribute();

  /**
   * Gets the type of Changes in the motion.
   *
   * @return - list of changes in the motion
   */
  List<ChangeType> getChangeType();

  /**
   * Replaces an attribute of this Motion with a new Attribute.
   *
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   */
  void editKeyframe(int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Replaces an attribute of this Motion with a new Attribute.
   *
   * @param t The time for this keyframe
   * @param shapeAttribute attributes of the Shape
   */
  void editKeyframe(int t, Attribute shapeAttribute);

  /**
   * Adjusts a {@code MotionImpl} when an existing keyframe is deleted.
   *
   * @param tFrom - time of the deleted keyframe
   * @param tTo - new end time of the motion
   * @param a - the newAttribute of the Motion after this one
   */
  void adjustKeyframe(int tFrom, int tTo, Attribute a);
}