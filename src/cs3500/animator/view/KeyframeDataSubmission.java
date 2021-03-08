package cs3500.animator.view;

import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import java.awt.Color;

/**
 * KeyframeDataSubmission Class. Allows view to package data collected from input regarding a
 * shape's keyframe and can be used in editing and adding a keyframe.
 */
public class KeyframeDataSubmission implements DataSubmission {

  private int time;
  private Position2D position2D;
  private int width;
  private int height;
  private Color color;
  private String shapeName;

  /**
   * Constructs a KeyframeDataSubmission object that contains the time, 2D position, width, height,
   * color, and shape's name of the specific keyframe.
   *
   * @param time - keyframe time
   * @param position2D - keyframe position
   * @param width - keyframe width
   * @param height - keyframe height
   * @param color - keyframe color
   * @param shapeName - keyframe Shape Name
   */
  public KeyframeDataSubmission(int time, Position2D position2D, int width, int height,
      Color color, String shapeName) {
    this.time = time;
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
    this.shapeName = shapeName;
  }

  @Override
  public double getXValue() {
    return this.position2D.getX();
  }

  @Override
  public double getYValue() {
    return this.position2D.getY();
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getAngle() {
    return 0;
  }

  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    throw new UnsupportedOperationException("Operation not supported");
  }

  @Override
  public int getKeyframeTime() {
    return this.time;
  }

  @Override
  public int getLayer() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getCurrentLayerSelected() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }
}
