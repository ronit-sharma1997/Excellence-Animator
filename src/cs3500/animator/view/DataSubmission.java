package cs3500.animator.view;

import cs3500.animator.model.ShapeType;
import java.awt.Color;

/**
 * Data Submission interface. Allows view to package data collected from input.
 */
public interface DataSubmission {

  /**
   * Returns the x-coordinate from this Attribute.
   *
   * @return - the x-coordinate from this Attribute
   */
  double getXValue();

  /**
   * Returns the y-coordinate from this data submission.
   *
   * @return - the y-coordinate from data submission
   */
  double getYValue();

  /**
   * Returns the width from this data submission.
   *
   * @return - the width from data submission
   */
  int getWidth();

  /**
   * Returns the height from this data submission.
   *
   * @return - the height from data submission
   */
  int getHeight();

  /**
   * Returns the angle from this data submission.
   *
   * @return - the angle from data submission
   */
  int getAngle();

  /**
   * Returns the color from this data submission.
   *
   * @return - the color from this data submission
   */
  Color getColor();

  /**
   * Returns the name of shape from this data submission.
   *
   * @return - name of shape from this data submission
   */
  String getShapeName();

  /**
   * Returns the shape type from this data submission.
   *
   * @return - {@link ShapeType}
   */
  ShapeType getShapeType();

  /**
   * Returns the keyframe time from this data submission.
   *
   * @return - keyframe time
   */
  int getKeyframeTime();

  /**
   * Returns the new layer from this data submission.
   *
   * @return - new layer value
   */
  int getLayer();

  /**
   * Returns the current layer from this data submission.
   *
   * @return - current layer value
   */
  int getCurrentLayerSelected();

}
