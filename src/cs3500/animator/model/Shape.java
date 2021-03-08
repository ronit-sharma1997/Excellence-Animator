package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents a shape in the animation. It is important to have a class that can allow us to
 * represent a Shape and all of the motions that are being applied on this shape. This interface
 * allows for creating and mutating Shapes.
 */
public interface Shape extends ImmutableShape {

  /**
   * Sets the updated values of this shape.
   *
   * @param position2D - represents the updated 2D position of this Shape
   * @param width - represents the updated width position of this Shape
   * @param height - represents the updated height of this Shape
   * @param color - represents the updated Color of this Shape
   */
  void setValues(Position2D position2D, int width, int height, Color color);

  /**
   * Sets the updated values of this shape.
   *
   * @param shapeAttribute - represents shape's attributes
   */
  void setValues(Attribute shapeAttribute);

  /**
   * Checks if there are no changes in this shape.
   *
   * @return - true if there have been no changes on this shape
   */
  boolean noMotions();


  /**
   * Adds a new change to this Shape's changes. Needed to keep track of changes to a Shape and
   * determine whether an invalid Change was provided.
   *
   * @param motion - represents a change of this shape's attributes
   * @throws IllegalArgumentException - if change is null or if change conflicts with same type of
   *         change at an interval
   */
  void addMotion(Motion motion) throws IllegalArgumentException;

  /**
   * Returns a copy of this Shape.
   *
   * @return a copy of this Shape including all of the same field values
   */
  Shape copyShape();

  /**
   * Sets the current change of motion of the shape.
   *
   * @param change - represents the new change the Shape is going through
   */
  void setCurrentMotion(Motion change);

  /**
   * Get the current change of this shape.
   *
   * @return - List of Changes of the Shape
   */
  Motion getCurrentMotion();

  /**
   * Checks if the shape has a current transformation at the moment.
   *
   * @return - true if the shape has a current change
   */
  boolean noCurrentMotion();

  void checkShapeMotions();

  /**
   * Adds an individual keyframe to this Shape.
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
  void addKeyframe(int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Adds an individual keyframe to this Shape.
   *
   * @param t The time for this keyframe
   * @param keyframeAttributes attributes at the given keyframe
   */
  void addKeyframe(int t, Attribute keyframeAttributes);


  /**
   * Edits an individual keyframe of this Shape.
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
   * Edits an individual keyframe of this Shape.
   *
   * @param t The time for this keyframe
   * @param keyframeAttributes attributes at the given keyframe
   */
  void editKeyframe(int t, Attribute keyframeAttributes);


  /**
   * Deletes a keyframe from this shape.
   *
   * @param t - the keyframe to be deleted
   */
  void deleteKeyframe(int t);

  /**
   * Resets the current motion for a shape.
   */
  void resetCurrentMotion();

}