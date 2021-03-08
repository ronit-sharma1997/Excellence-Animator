package cs3500.animator.model;


import java.util.Objects;

/**
 * This class represents a 2D position.
 */
public final class Position2D {

  private final double x;

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  private final double y;

  /**
   * Initialize this object to the specified position.
   *
   * @param x - x coordinate of position
   * @param y - y coordinate of position
   * @throws IllegalArgumentException - if inputs are negative
   */
  public Position2D(double x, double y) throws IllegalArgumentException {
    this.x = x;
    this.y = y;
  }

  /**
   * Copies the constructor.
   *
   * @param v - a 2D position
   */
  public Position2D(Position2D v) {
    this(v.x, v.y);
  }

  /**
   * Represents this position as a pair of coordinates.
   *
   * @return - a pair of coordinates in the structure "(x-coordinate, y-coordinate)"
   */
  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}


