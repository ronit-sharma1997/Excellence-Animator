package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents the characteristics of a Shape, including position, width, height, color.
 */
public final class AttributeImpl extends AbstractAttribute {

  protected int angle;

  private AttributeImpl() {

  }

  @Override
  public int getAngle() {
    return this.angle;
  }

  @Override
  public int[] getAttributes() {
    int[] attributes;
    attributes = new int[8];
    attributes[0] = (int) this.getX();
    attributes[1] = (int) this.getY();
    attributes[2] = this.getW();
    attributes[3] = this.getH();
    attributes[4] = this.getR();
    attributes[5] = this.getG();
    attributes[6] = this.getB();
    attributes[7] = this.getAngle();
    return attributes;
  }

  @Override
  protected String getMotion() {
    return position.getX() + " " + position.getY()
        + " " + width + " " + height + " " + getColorComponents(
        color) + " " + angle;
  }

  public static final class Builder extends AbstractAttribute.Builder<AttributeImpl, Builder> {

    @Override
    protected AttributeImpl getObject() {
      return new AttributeImpl();
    }

    @Override
    protected Builder thisObject() {
      return this;
    }

    public Builder(Position2D position2D, int width, int height, Color color) {
      super(position2D, width, height, color);
    }

    public Builder setAngle(int angle) {
      object.angle = angle;
      return this;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, width, height, color, angle);
  }

  @Override
  public boolean equals(Object obj) {
    boolean originalAttribute = super.equals(obj);
    if (originalAttribute) {
      Attribute attribute = (Attribute) obj;
      return originalAttribute
          && attribute.getAngle() == this.angle;
    } else {
      return originalAttribute;
    }
  }
}
