package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementations of {@link Motion}. We implemented Motion interface, as it provides an effective
 * way to track changes that could be applied to a Shape such as change in color, etc. Each Motion
 * needs a startTime and endTime and each state have their own unique fields to keep track of the
 * changes to the corresponding field. Creating this class made it a lot easier to incorporate
 * changes with a Shape by separating each change by fields and then storing {@link Attribute} in a
 * class that implements Shape.
 */
public final class MotionImpl implements Motion {

  private Attribute previousState;
  private Attribute newState;
  private int startTime;
  private int endTime;
  private List<ChangeType> type;

  /**
   * Constructs a {@link MotionImpl} object with previous and next Attributes, start and end time.
   *
   * @param startTime - represents the start time of a Change in an animation
   * @param endTime - represents the end time of a Change in an animation
   * @throws IllegalArgumentException - if the start time is less than one or the end time is less
   *         than the start time
   */
  public MotionImpl(int startTime, int endTime, Attribute previousState, Attribute newState)
      throws IllegalArgumentException {
    if (startTime < 0 || endTime < startTime || previousState == null || newState == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.type = new ArrayList<>();
    this.startTime = startTime;
    this.endTime = endTime;
    this.previousState = previousState;
    this.newState = newState;
    addChangeType();
  }

  private void addChangeType() {
    if (previousState.getX() != newState.getX() || previousState.getY() != newState.getY()) {
      this.type.add(ChangeType.POSITION);
    }
    if (previousState.getW() != newState.getW()) {
      this.type.add(ChangeType.WIDTH);
    }
    if (previousState.getH() != newState.getH()) {
      this.type.add(ChangeType.HEIGHT);
    }
    if (previousState.getR() != newState.getR() || previousState.getB() != newState.getB()
        || previousState.getG() != newState.getG()) {
      this.type.add(ChangeType.COLOR);
    }
    if (previousState.getX() == newState.getX() && previousState.getY() == newState.getY()
        && previousState.getW() == newState.getW() && previousState.getH() == newState.getH()
        && previousState.getR() == newState.getR() && previousState.getB() == newState.getB()
        && previousState.getG() == newState.getG()
        && previousState.getAngle() == newState.getAngle()) {
      this.type.add(ChangeType.NOCHANGE);
    }
    if (previousState.getAngle() != newState.getAngle()) {
      this.type.add(ChangeType.ROTATION);
    }
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public Motion copyMotion() {
    Motion motionToReturn;
    motionToReturn = new MotionImpl(startTime, endTime, new AttributeImpl.Builder(
        new Position2D(previousState.getX(), previousState.getY()), previousState.getW(),
        previousState.getH(), new Color(previousState.getR(), previousState.getG(),
        previousState.getB())).setAngle(this.previousState.getAngle()).build(),
        new AttributeImpl.Builder(new Position2D(newState.getX(), newState.getY()),
            newState.getW(), newState.getH(), new Color(newState.getR(), newState.getG(),
            newState.getB())).setAngle(this.newState.getAngle()).build());
    return motionToReturn;
  }

  @Override
  public int[] getPreviousState() {
    return this.previousState.getAttributes();
  }

  @Override
  public int[] getNewState() {
    return this.newState.getAttributes();
  }

  @Override
  public Attribute getPreviousAttribute() {
    return new AttributeImpl.Builder(new Position2D(this.previousState.getX(),
        this.previousState.getY()), this.previousState.getW(), this.previousState.getH(),
        new Color(this.previousState.getR(), this.previousState.getG(),
            this.previousState.getB())).setAngle(this.previousState.getAngle()).build();
  }

  @Override
  public Attribute getNewAttribute() {
    return new AttributeImpl.Builder(new Position2D(this.newState.getX(), this.newState.getY()),
        this.newState.getW(), this.newState.getH(), new Color(this.newState.getR(),
        this.newState.getG(), this.newState.getB())).setAngle(this.newState.getAngle()).build();
  }

  @Override
  public List<ChangeType> getChangeType() {
    return type;
  }

  @Override
  public void editKeyframe(int t, int x, int y, int w, int h, int r, int g, int b) {
    editKeyframeHelper(t, new AttributeImpl.Builder(new Position2D(x, y), w, h,
        new Color(r, g, b)).build());
  }

  @Override
  public void editKeyframe(int t, Attribute attribute) {
    editKeyframeHelper(t, attribute);
  }

  private void editKeyframeHelper(int t, Attribute attribute) {
    if (t < 0 || attribute.getW() < 1 || attribute.getH() < 1 || attribute.getR() < 0 ||
        attribute.getG() < 0 || attribute.getB() < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (t == this.startTime) {
      this.previousState = new AttributeImpl.Builder(new Position2D(attribute.getX(),
          attribute.getY()), attribute.getW(), attribute.getH(), new Color(attribute.getR(),
          attribute.getG(), attribute.getB())).setAngle(attribute.getAngle()).build();
    }
    if (t == this.endTime) {
      this.newState = new AttributeImpl.Builder(new Position2D(attribute.getX(),
          attribute.getY()), attribute.getW(), attribute.getH(), new Color(attribute.getR(),
          attribute.getG(), attribute.getB())).setAngle(attribute.getAngle()).build();
    }
  }

  @Override
  public void adjustKeyframe(int tFrom, int tTo, Attribute a) {
    if (a == null) {
      throw new IllegalArgumentException("Attribute cannot be null");
    } else if (tFrom == this.endTime) {
      this.newState = a;
      this.endTime = tTo;
    } else if (tFrom == this.startTime) {
      this.previousState = a;
      this.startTime = tTo;
    } else {
      throw new IllegalArgumentException("Keyframe does not exist");
    }
  }
}