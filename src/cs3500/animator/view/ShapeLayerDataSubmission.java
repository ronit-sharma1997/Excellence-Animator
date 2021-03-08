package cs3500.animator.view;

import cs3500.animator.model.ShapeType;


/**
 * ShapeLayerDataSubmission Class. Allows view to package data collected from input regarding a
 * shape such as adding a new Shape in specific layer.
 */
public class ShapeLayerDataSubmission extends ShapeDataSubmission {

  private int currentLayerSelected;
  private int layer;

  /**
   * Constructs a ShapeLayerDataSubmission object that contains the shape name, type of Shape, and
   * layer.
   *
   * @param shapeName - keyframe Shape Name
   * @param shapeType - Type of Shape(rectangle, ellipse, etc.)
   * @param layer - layer of Shape
   */
  public ShapeLayerDataSubmission(String shapeName, ShapeType shapeType, int layer,
      int currentLayerSelected) {
    super(shapeName, shapeType);
    this.layer = layer;
    this.currentLayerSelected = currentLayerSelected;
  }

  @Override
  public int getLayer() {
    return layer;
  }

  @Override
  public int getCurrentLayerSelected() {
    return currentLayerSelected;
  }
}
