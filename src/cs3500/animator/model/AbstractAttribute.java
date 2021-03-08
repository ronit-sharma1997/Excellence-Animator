package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

public abstract class AbstractAttribute implements Attribute {

  protected Position2D position;
  protected int width;
  protected int height;
  protected Color color;

  /**
   * Constructs {@code AttributeImpl} object which represents movement and changes on a shape over a
   * specified amount of time. Width and height will always be greater than 0 and color will always
   * be a valid color.
   *
   * @param position2D - Start position of the shape
   * @param width - Starting width of the shape
   * @param height - Starting height of the shape
   * @param color - Starting color of the shape
   * @throws IllegalArgumentException if 2D position is null, if width or height is less than one,
   *         or if the color is null
   */
  public AbstractAttribute(Position2D position2D, int width, int height, Color color)
      throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.position = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  protected AbstractAttribute() {
      //abstract empty constructor that is overriden by subclasses
  }

  @Override
  public String getString() {
    StringBuilder result = new StringBuilder();
    result.append(getMotion());
    return result.toString();
  }

  @Override
  public double getX() {
    if (this.position != null) {
      return this.position.getX();
    }
    return 0;
  }

  @Override
  public double getY() {
    if (this.position != null) {
      return this.position.getY();
    }
    return 0;
  }

  @Override
  public int getW() {
    return this.width;
  }

  @Override
  public int getH() {
    return this.height;
  }

  @Override
  public int getR() {

    if (this.color != null) {
      return this.color.getRed();
    }
    return 0;
  }

  @Override
  public int getG() {
    if (this.color != null) {
      return this.color.getGreen();
    }
    return 0;
  }

  @Override
  public int getB() {
    if (this.color != null) {
      return this.color.getBlue();
    }
    return 0;
  }

  @Override
  public int[] getAttributes() {
    int[] attributes;
    attributes = new int[7];
    attributes[0] = (int) this.getX();
    attributes[1] = (int) this.getY();
    attributes[2] = this.getW();
    attributes[3] = this.getH();
    attributes[4] = this.getR();
    attributes[5] = this.getG();
    attributes[6] = this.getB();
    return attributes;
  }

  protected String getColorComponents(Color color) {
    return color.getRed() + " " + color.getGreen() + " " + color.getBlue();
  }

  protected String getMotion() {
    return position.getX() + " " + position.getY()
        + " " + width + " " + height + " " + getColorComponents(
        color);
  }

  public abstract static class Builder<T extends AbstractAttribute, B extends Builder<T, B>> {

    protected T object;
    protected B thisObject;

    protected abstract T getObject();

    protected abstract B thisObject();

    /**
     * Constructs a Builder of Attribute type that consists of required parameters of position,
     * size, and color.
     * @param position2D - x, y position
     * @param width - width of attribute
     * @param height - height of attribute
     * @param color - color of attribute
     */
    public Builder(Position2D position2D, int width, int height, Color color) {
      if (width < 0 || height < 0) {
        throw new IllegalArgumentException("Invalid input");
      }
      object = getObject();
      thisObject = thisObject();
      object.position = position2D;
      object.width = width;
      object.height = height;
      object.color = color;
    }

    protected Builder() {
        //abstract empty constructor that is overriden by subclasses
    }

    public T build() {
      return object;
    }

  }

  @Override
  public int hashCode() {
    return Objects.hash(position, width, height, color);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Attribute)) {
      return false;
    }
    Attribute attribute = (Attribute) obj;
    return new Position2D(attribute.getX(), attribute.getY()).equals(position)
        && attribute.getW() == width
        && attribute.getH() == height
        && new Color(attribute.getR(), attribute.getG(), attribute.getB()).equals(color);
  }
}
