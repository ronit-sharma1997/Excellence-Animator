package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Abstract base class for implementations of {@link Shape}. An abstract class was used for Shapes,
 * as it provides an effective way to extend for special polygon cases that require more fields such
 * as extra information pertaining to the width and height(cross, hexagon, etc.). Each polygon needs
 * a name, appearTime, disappearTime, 2D position, width, height, color, and list of changes fields
 * in order to provide a terse and rich description of an animation. Class invariants are that the
 * appearTime and disappearTime will always be valid values(both non negative and appearTime <
 * disappearTime). In addition a width and height will both never be less than 1 unit and the list
 * of changes for a Shape will not have two motions that conflict in timing and change. Motions
 * cannot be added to a {@link Shape} unless that shape already has a specified appear and disappear
 * time.
 */
abstract class AbstractShape implements Shape {

  private String name;
  private Position2D position2D;
  private int width;
  private int height;
  private Color color;
  private int angle;
  private int maxEndTime;
  private int startTime;
  //used a list for a simple means of first in first out for changes
  private List<Motion> motions;
  private Motion currentMotion;
  private int numberMotions;
  protected ShapeType type;

  /**
   * Constructs a {@link Shape} in a manner selected by concrete subclasses of this class.
   *
   * @param name - the name of an AbstractShape subclass
   * @throws IllegalArgumentException - if the name is null or empty, the position2D is null, the
   *         color is null, or the width or height is less than one
   */
  public AbstractShape(String name)
      throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid Input");
    }
    this.name = name;
    this.maxEndTime = 0;
    this.numberMotions = 0;
    this.startTime = Integer.MAX_VALUE;
    this.currentMotion = null;
    this.motions = new ArrayList<>();
  }

  @Override
  public void addKeyframe(int t, int x, int y, int w, int h, int r, int g, int b) {
    addKeyframeHelper(t, new AttributeImpl.Builder(new Position2D(x, y), w, h,
        new Color(r, g, b)).build());
  }

  @Override
  public void addKeyframe(int t, Attribute attribute) {
    addKeyframeHelper(t, attribute);
  }

  private void addKeyframeHelper(int t, Attribute attribute) {
    if (t < 0 || attribute == null || attribute.getW() < 1 || attribute.getH() < 1 ||
        attribute.getR() < 0 || attribute.getG() < 0 || attribute.getB() < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    Attribute stateMotionAngle = new AttributeImpl.Builder(new Position2D(attribute.getX(),
        attribute.getY()), attribute.getW(), attribute.getH(), new Color(attribute.getR(),
        attribute.getG(), attribute.getB())).setAngle(attribute.getAngle()).build();
    //CASE0: No motions currently
    if (this.motions.size() == 0) {
      this.motions.add(new MotionImpl(t, t, stateMotionAngle, stateMotionAngle));
      this.maxEndTime = t;
      this.startTime = t;
      numberMotions++;
    }
    //CASE1: Before the first motion
    else if (t < this.motions.get(0).getStartTime()) {
      int firstMotionStartTime = this.motions.get(0).getStartTime();
      this.motions.add(0, new MotionImpl(t, firstMotionStartTime,
          stateMotionAngle,
          motions.get(0).getPreviousAttribute()));
      numberMotions++;
      this.startTime = t;
    }
    //CASE2: After all motions
    else if (t > maxEndTime) {
      this.motions.add(new MotionImpl(maxEndTime, t,
          motions.get(motions.size() - 1).getNewAttribute(), stateMotionAngle));
      this.maxEndTime = t;
      numberMotions++;
    } else {
      //CASE3: error, keyframe already exists
      int size = this.motions.size();
      for (int i = 0; i < size; i++) {
        Motion m = this.motions.get(i);
        if (t == m.getStartTime() || t == m.getEndTime()) {
          throw new IllegalArgumentException("Keyframe already exists, "
              + "cant add a new keyframe here");
        }
        //CASE4: In between keyframes of a motion
        else if (t > m.getStartTime() && t < m.getEndTime()) {
          int indexOfMotion = this.motions.indexOf(m);
          int motionEndTime = this.motions.get(indexOfMotion).getEndTime();
          this.motions.add(new MotionImpl(t, motionEndTime, stateMotionAngle,
              m.getNewAttribute()));
          this.motions.get(indexOfMotion).adjustKeyframe(this.motions.get(indexOfMotion)
              .getEndTime(), t, stateMotionAngle);
          numberMotions++;
        }
      }
    }
    currentMotion = null;
    checkShapeMotions();
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
    if (t < 0 || attribute == null || attribute.getW() < 1 || attribute.getH() < 1 ||
        attribute.getR() < 0 || attribute.getG() < 0 || attribute.getB() < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    for (Motion m : this.motions) {
      m.editKeyframe(t, attribute);
      if (!noCurrentMotion() && m.getStartTime() == currentMotion.getStartTime()
          && m.getEndTime() == currentMotion.getEndTime()) {
        currentMotion = m;
      }
      if (m.getEndTime() > t) {
        break;
      }
    }
    currentMotion = null;
  }

  @Override
  public void addMotion(Motion motion) throws IllegalArgumentException {
    if (motion == null) {
      throw new IllegalArgumentException("Motion is null");
    } else if (this.conflicts(motion)) {
      throw new IllegalArgumentException("Motion conflicts");
    }
    if (this.maxEndTime < motion.getEndTime()) {
      this.maxEndTime = motion.getEndTime();
    }
    if (this.startTime > motion.getStartTime()) {
      this.startTime = motion.getStartTime();
    }
    motions.add(motion);
    numberMotions++;
  }

  @Override
  public void checkShapeMotions() throws IllegalArgumentException {
    Collections.sort(motions, Comparator.comparingInt(Motion::getStartTime)
        .thenComparingInt(Motion::getEndTime));
    for (int countMotions = 0; countMotions < (numberMotions - 1); countMotions++) {
      if (incorrectAttributes(motions.get(countMotions), motions.get(countMotions + 1))) {
        throw new IllegalArgumentException("Motions don't have common endpoints");
      }
    }

  }

  @Override
  public Attribute getAttribute() {
    return new AttributeImpl.Builder(position2D, width, height, color).setAngle(angle).build();
  }

  @Override
  public String getResult() {
    return resultAppender();
  }

  @Override
  public String getName() {
    return this.name;
  }


  @Override
  public boolean noMotions() {
    return this.motions.isEmpty();
  }

  @Override
  public int getMaxEndTime() {
    return maxEndTime;
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public List<Motion> getMotions() {
    List<Motion> changeList = new ArrayList<>();
    for (Motion c : this.motions) {
      Motion motion = c.copyMotion();
      changeList.add(motion);
    }
    return changeList;
  }

  @Override
  public void setValues(Position2D position2D, int width, int height, Color color) {
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public void setValues(Attribute attribute) {
    this.setValues(new Position2D(attribute.getX(), attribute.getY()), attribute.getW(),
        attribute.getH(), new Color(attribute.getR(), attribute.getG(), attribute.getB()));
    this.angle = attribute.getAngle();
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public boolean noCurrentMotion() {
    return this.currentMotion == null;
  }

  @Override
  public Motion getCurrentMotion() {
    if (currentMotion == null) {
      return currentMotion;
    }
    return this.currentMotion.copyMotion();
  }

  @Override
  public void setCurrentMotion(Motion motion) {
    this.currentMotion = motion;
  }

  @Override
  public void deleteKeyframe(int t) {
    boolean frameDeleted = false;
    ArrayList<Motion> toDelete = new ArrayList<>();
    if (this.motions.isEmpty()) {
      throw new IllegalArgumentException("Keyframe does not exist");
    }
    for (int i = 0; i < this.motions.size(); i++) {
      Motion curMotion = this.motions.get(i);
      Motion futureMotion = this.motions.get(i);
      Motion afterFutureMotion = this.motions.get(i);
      if (toDelete.size() > 0) {
        break;
      }
      if (i < this.motions.size() - 1) {
        futureMotion = this.motions.get(i + 1);
      }
      if (i < this.motions.size() - 2) {
        afterFutureMotion = this.motions.get(i + 2);
      }
      // if there is one motion
      if (this.motions.size() == 1) {
        if (curMotion.getEndTime() == curMotion.getStartTime() && t == curMotion.getStartTime()) {
          this.maxEndTime = 0;
          toDelete.add(curMotion);
        } else if (t == curMotion.getEndTime()) {
          curMotion.adjustKeyframe(curMotion.getEndTime(), curMotion.getStartTime(),
              curMotion.getPreviousAttribute());
          this.maxEndTime = curMotion.getStartTime();
          frameDeleted = true;
        } else if (t == curMotion.getStartTime()) {
          curMotion.adjustKeyframe(curMotion.getStartTime(), curMotion.getEndTime(),
              curMotion.getNewAttribute());
          frameDeleted = true;
        }
      } else if (curMotion.getStartTime() == curMotion.getEndTime()
          && t == curMotion.getStartTime()) {
        futureMotion.adjustKeyframe(futureMotion.getStartTime(), futureMotion.getEndTime(),
            futureMotion.getNewAttribute());
        toDelete.add(curMotion);
      } else if (futureMotion.getStartTime() == futureMotion.getEndTime()
          && t == curMotion.getEndTime()) {
        if (i < this.motions.size() - 2) {
          curMotion.adjustKeyframe(curMotion.getEndTime(), afterFutureMotion.getEndTime(),
              afterFutureMotion.getNewAttribute());
          toDelete.add(futureMotion);
          toDelete.add(afterFutureMotion);
        } else if (i >= this.motions.size() - 2 && t == curMotion.getEndTime()) {
          curMotion.adjustKeyframe(curMotion.getEndTime(), curMotion.getStartTime(),
              curMotion.getPreviousAttribute());
          toDelete.add(futureMotion);
        }
      }
      // delete first keyframe
      else if (t == this.motions.get(0).getStartTime() && this.motions.size() > 1) {
        toDelete.add(this.motions.get(0));
      }
      // delete last keyframe
      else if (t == this.motions.get(this.motions.size() - 1).getEndTime()
          && this.motions.size() > 1) {
        toDelete.add(this.motions.get(this.motions.size() - 1));
        this.maxEndTime = this.motions.get(this.motions.size() - 1).getStartTime();
      }
      // delete keyframe in the middle
      else {
        if (t == curMotion.getEndTime()) {
          curMotion.adjustKeyframe(t, futureMotion.getEndTime(),
              futureMotion.getNewAttribute());
          toDelete.add(futureMotion);
        }
      }
    }
    // delete motion if keyframe was first or last frame
    for (Motion m : toDelete) {
      this.motions.remove(m);
      numberMotions--;
      frameDeleted = true;
    }
    if (!frameDeleted) {
      // delete keyframe that doesn't exist
      throw new IllegalArgumentException("Keyframe does not exist");
    }
    currentMotion = null;
    if (!this.motions.isEmpty()) {
      this.startTime = this.motions.get(0).getStartTime();
    }
  }

  @Override
  public void resetCurrentMotion() {
    this.setValues(this.motions.get(0).getPreviousAttribute());
    this.currentMotion = null;
  }

  /**
   * Returns a string representing the name and type of this Shape, as well as all of its Changes in
   * the following format: "shape 'shape name' 'shape type'" "motion 'name' 'time' 'x-pos' 'y-pos'
   * 'width' 'height' 'r' 'g' 'b'" (next line would go here) "'name' 'time' 'x-pos' 'y-pos' 'width'
   * 'height' 'r' 'g' 'b'" more motions would go here and below.
   *
   * @return - a string which describes the name, type, and Changes of this Shape.
   */
  private String resultAppender() {
    StringBuilder results = new StringBuilder();
    results.append("shape " + this.name + " " + this.getType().getShapeType() + "\n");
    for (Motion motion : motions) {
      results.append("motion " + this.name + " " + motion.getStartTime() + " " +
          motion.getPreviousAttribute().getString() + "      ");
      results.append(motion.getEndTime() + " " + motion.getNewAttribute().getString() + "\n");
    }
    return results.toString();
  }

  /**
   * Determines whether a inputted change conflicts with an already existing change.
   *
   * @param c - Change object that represents a change of certain field
   * @return whether a change conflicts with any changes stored in the Shape so far
   */
  private boolean conflicts(Motion c) {
    for (Motion change : motions) {
      if (overlap(c, change)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether two changes overlap based on timing.
   *
   * @param cAdd - Change object that represents a change of certain field
   * @param c1 - Change object that represents a change of certain field
   * @return whether two changes overlap
   */
  private static boolean overlap(Motion cAdd, Motion c1) {
    return !(cAdd.getStartTime() >= c1.getEndTime() || cAdd.getEndTime() <= c1.getStartTime());
  }

  private boolean incorrectAttributes(Motion motionBefore, Motion newMotion) {
    return !motionBefore.getNewAttribute().equals(newMotion.getPreviousAttribute())
        || motionBefore.getEndTime() != newMotion.getStartTime();
  }

  @Override
  public Attribute interpolate(int tick) {
    for (Motion motion : motions) {
      if (tick >= motion.getStartTime() && tick <= motion.getEndTime()) {
        int[] attributes = newAttributesShape(motion.getStartTime(), motion.getEndTime(),
            tick, motion.getPreviousState(), motion.getNewState());
        this.position2D = new Position2D(attributes[0], attributes[1]);
        this.width = attributes[2];
        this.height = attributes[3];
        this.color = new Color(attributes[4], attributes[5], attributes[6]);
        return this.getAttribute();
      }
    }
    return null;
  }

  private int interpolation(double previous, double next, int startTime, int endTime,
      int currentTick) {
    return (int) ((previous * (endTime - currentTick) / (endTime - startTime)) + (
        next * (currentTick - startTime) / (endTime - startTime)));
  }

  private int[] newAttributesShape(int motionStartTime, int motionEndTime, int currentTick,
      int[] previousAttributes, int[] newAttributes) {
    int[] result = new int[7];
    if (motionStartTime == motionEndTime) {
      result[0] = newAttributes[0];
      result[1] = newAttributes[1];
      result[2] = newAttributes[2];
      result[3] = newAttributes[3];
      result[4] = newAttributes[4];
      result[5] = newAttributes[5];
      result[6] = newAttributes[6];
    } else {
      result[0] = (interpolation(previousAttributes[0], newAttributes[0], motionStartTime,
          motionEndTime, currentTick));
      result[1] = (interpolation(previousAttributes[1], newAttributes[1], motionStartTime,
          motionEndTime, currentTick));
      result[2] = (interpolation(previousAttributes[2], newAttributes[2], motionStartTime,
          motionEndTime, currentTick));
      result[3] = (interpolation(previousAttributes[3], newAttributes[3], motionStartTime,
          motionEndTime, currentTick));
      result[4] = (interpolation(previousAttributes[4], newAttributes[4], motionStartTime,
          motionEndTime, currentTick));
      result[5] = (interpolation(previousAttributes[5], newAttributes[5], motionStartTime,
          motionEndTime, currentTick));
      result[6] = (interpolation(previousAttributes[6], newAttributes[6], motionStartTime,
          motionEndTime, currentTick));
    }
    return result;
  }
}
