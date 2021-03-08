package cs3500.animator.model;

import java.util.List;

/**
 * Represents an immutable shape in the animation. Clients only have read only access to the shape
 * and can only retrieve data regarding a shape.
 */
public interface ImmutableShape {

  /**
   * Returns an Attribute representing the characteristics of this Shape at the current moment.
   *
   * @return - an Attribute describing this shape's characteristics.
   */
  Attribute getAttribute();

  /**
   * Returns a string representing the name and type of this Shape, as well as all of its Changes in
   * the following format: "shape 'shape name' 'shape type'" "motion 'name' 'time' 'x-pos' 'y-pos'
   * 'width' 'height' 'r' 'g' 'b'" (next line would go here) "'name' 'time' 'x-pos' 'y-pos' 'width'
   * 'height' 'r' 'g' 'b'" more motions would go here and below.
   *
   * @return - a string which describes the name, type, and Changes of this Shape.
   */
  String getResult();


  /**
   * Get the list of changes in this shape.
   *
   * @return - List of Changes of the Shape
   */
  List<Motion> getMotions();


  /**
   * Returns the name of this Shape.
   *
   * @return - the name of this Shape
   */
  String getName();

  /**
   * Gets the max end time of the Shape.
   *
   * @return - the max end time of a Motion in the Shape
   */
  int getMaxEndTime();

  /**
   * Gets the start time of the Shape.
   *
   * @return - the time when a shape first appears
   */
  int getStartTime();

  /**
   * Gets the type of Shape.
   *
   * @return - what type of Shape the Shape is
   */
  ShapeType getType();

  /**
   * Interpolates a shape based on a given tick.
   *
   * @param tick - point in time in the animation
   * @return - a Shape at a given point in time
   */
  Attribute interpolate(int tick);

}
