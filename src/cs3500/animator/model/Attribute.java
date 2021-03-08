package cs3500.animator.model;

/**
 * Represents the characteristics of an existing shape in the animation. It is important to have a
 * class that can allow us to create one object that can encapsulate non time information of a Shape
 * (Position, width, height, Color) so that we can grab this information at any instantaneous time.
 */
public interface Attribute {

  /**
   * Returns a string that describes the attributes of this Attribute.
   *
   * @return a string containing the 2D position (split into x-coordinate and y-coordinate both
   *         represented as doubles), width, height, and color (in rgb format where r, g, and b are
   *         integers from 0 to 255 inclusive) of this attribute represented in the following
   *         structure: type: double double        int     int    int int int "'x-coordinate'
   *         'y-coordinate' 'width' 'height' 'R' 'G' 'B'"
   */
  String getString();

  /**
   * Returns the x-coordinate from this Attribute.
   *
   * @return - the x-coordinate from this Attribute
   */
  double getX();

  /**
   * Returns the y-coordinate from this Attribute.
   *
   * @return - the y-coordinate from this Attribute
   */
  double getY();

  /**
   * Returns the width from this Attribute.
   *
   * @return - the width from this Attribute
   */
  int getW();

  /**
   * Returns the height from this Attribute.
   *
   * @return - the height from this Attribute
   */
  int getH();

  /**
   * Returns the r color component from this Attribute.
   *
   * @return - the r color component from this Attribute
   */
  int getR();

  /**
   * Returns the g color component from this Attribute.
   *
   * @return - the g color component from this Attribute
   */
  int getG();

  /**
   * Returns the b color component from this Attribute.
   *
   * @return - the b color component from this Attribute
   */
  int getB();

  /**
   * Returns all components from this Attribute.
   *
   * @return - array representing the Attribute
   */
  int[] getAttributes();

  /**
   * Returns an angle if set for the attribute.
   * @return - angle integer value
   */
  int getAngle();
}
