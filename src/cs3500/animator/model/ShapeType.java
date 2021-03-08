package cs3500.animator.model;

/**
 * Represents the types of shapes that can be made.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), OVAL("oval");

  private final String type;

  ShapeType(String type) {
    this.type = type;
  }

  public String getShapeType() {
    return this.type;
  }
}