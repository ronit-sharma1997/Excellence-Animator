package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an oval shape. Allows us to create a specific class for Oval that can extend {@link
 * AbstractShape}'s functionality.
 */
public final class Oval extends AbstractShape {

  /**
   * Constructs an Oval shape.
   *
   * @param name - represents the name of this oval
   */
  public Oval(String name) {

    super(name);
    type = ShapeType.OVAL;
  }

  @Override
  public Shape copyShape() {
    Shape shape = new Oval(this.getName());
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
