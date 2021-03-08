package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an Rectangle shape. Allows us to create a specific class for Rectangle that can extend
 * {@link AbstractShape}'s functionality.
 */
public final class Rectangle extends AbstractShape {

  /**
   * Constructs a Rectangle shape.
   *
   * @param name - represents the name of this rectangle
   */
  public Rectangle(String name) {
    super(name);
    type = ShapeType.RECTANGLE;
  }

  @Override
  public Shape copyShape() {
    Shape shape = new Rectangle(this.getName());
    shape.setValues(
        new Position2D(this.getAttribute().getX(),
            this.getAttribute().getY()),
        this.getAttribute().getW(),
        this.getAttribute().getH(),
        new Color(
            this.getAttribute().getR(),
            this.getAttribute().getG(),
            this.getAttribute().getB()));
    for (Motion c : this.getMotions()) {
      shape.addMotion(c);
    }
    return shape;
  }
}
