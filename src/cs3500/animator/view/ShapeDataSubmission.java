package cs3500.animator.view;

import cs3500.animator.model.ShapeType;
import java.awt.Color;

/**
 * ShapeDataSubmission Class. Allows view to package data collected from input regarding a shape
 * such as adding a new Shape.
 */
public class ShapeDataSubmission implements DataSubmission {

  private String shapeName;
  private ShapeType shapeType;
  private int layer;

  /**
   * Constructs a ShapeDataSubmission object that contains the shape name and type of Shape.
   *
   * @param shapeName - keyframe Shape Name
   * @param shapeType - Type of Shape(rectangle, ellipse, etc.)
   */
  public ShapeDataSubmission(String shapeName, ShapeType shapeType) {
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.layer = 0;
  }

  @Override
  public double getXValue() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public double getYValue() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getWidth() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getHeight() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getAngle() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public Color getColor() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getKeyframeTime() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public int getLayer() {
    return layer;
  }

  @Override
  public int getCurrentLayerSelected() {
    return 0;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

}
