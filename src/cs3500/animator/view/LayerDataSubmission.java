package cs3500.animator.view;

import cs3500.animator.model.ShapeType;
import java.awt.Color;

/**
 * LayerDataSubmission Class. Allows view to package data collected from input regarding a layer
 * such as adding a new Layer.
 */
public final class LayerDataSubmission implements DataSubmission {

  private int layer;

  /**
   * Constructs a LayerDataSubmission object that contains the layer number.
   *
   * @param layer - new layer to add
   */
  public LayerDataSubmission(int layer) {
    this.layer = layer;
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
    return this.layer;
  }

  @Override
  public int getCurrentLayerSelected() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public String getShapeName() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

  @Override
  public ShapeType getShapeType() {
    throw new UnsupportedOperationException("Unsupported operation!");
  }

}

