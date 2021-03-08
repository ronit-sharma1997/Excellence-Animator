
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.MotionImpl;
import java.awt.Color;
import org.junit.Test;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;

import static junit.framework.TestCase.assertEquals;

public class MotionImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTimePosition() {
    Motion c = new MotionImpl(-1, 10, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.GREEN).build(),
        new AttributeImpl.Builder(new Position2D(10, 0), 1, 1,
            Color.GREEN).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTimeColor() {
    Motion c = new MotionImpl(-1, 10, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.GREEN).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 1, 1,
            Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTimeWidth() {
    Motion c = new MotionImpl(-1, 10, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), 2, 1, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTimeHeight() {
    Motion c = new MotionImpl(-1, 10, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), 1, 2, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndTimePosition() {
    Motion c = new MotionImpl(4, 2, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.GREEN).build(),
        new AttributeImpl.Builder(new Position2D(10, 0), 1,
            1, Color.GREEN).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndTimeColor() {
    Motion c = new MotionImpl(4, 2, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.GREEN).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 1,
            1, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndTimeWidth() {
    Motion c = new MotionImpl(4, 2, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), 2,
        1, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndTimeHeight() {
    Motion c = new MotionImpl(4, 2, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), 1,
        2, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndWidth() {
    Motion c = new MotionImpl(2, 4, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), -1,
        1, Color.RED).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndHeight() {
    Motion c = new MotionImpl(2, 4, new AttributeImpl.Builder(new Position2D(0,
        0), 1, 1, Color.RED).build(), new AttributeImpl.Builder(new Position2D(0,
        0), 1,
        -1, Color.RED).build());
  }

  @Test
  public void testEditKeyframe() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).build());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(5, 3, 2, 1, 2, 3, 4, 5);
    assertEquals(3.0, m.getNewAttribute().getX());
    assertEquals(2.0, m.getNewAttribute().getY());
    assertEquals(1, m.getNewAttribute().getW());
    assertEquals(2, m.getNewAttribute().getH());
    assertEquals(3, m.getNewAttribute().getR());
    assertEquals(4, m.getNewAttribute().getG());
    assertEquals(5, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testEditKeyframeEndAngle() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(20).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).setAngle(100).build());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(100, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(5, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).setAngle(30).build());
    assertEquals(3.0, m.getNewAttribute().getX());
    assertEquals(2.0, m.getNewAttribute().getY());
    assertEquals(1, m.getNewAttribute().getW());
    assertEquals(2, m.getNewAttribute().getH());
    assertEquals(3, m.getNewAttribute().getR());
    assertEquals(4, m.getNewAttribute().getG());
    assertEquals(5, m.getNewAttribute().getB());
    assertEquals(30, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testEditKeyframeEndNoAngle() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).build());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(5, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).build());
    assertEquals(3.0, m.getNewAttribute().getX());
    assertEquals(2.0, m.getNewAttribute().getY());
    assertEquals(1, m.getNewAttribute().getW());
    assertEquals(2, m.getNewAttribute().getH());
    assertEquals(3, m.getNewAttribute().getR());
    assertEquals(4, m.getNewAttribute().getG());
    assertEquals(5, m.getNewAttribute().getB());
    assertEquals(0, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testEditKeyframeStartAngle() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(20).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).setAngle(100).build());
    assertEquals(0.0, m.getPreviousAttribute().getX());
    assertEquals(0.0, m.getPreviousAttribute().getY());
    assertEquals(5, m.getPreviousAttribute().getW());
    assertEquals(5, m.getPreviousAttribute().getH());
    assertEquals(255, m.getPreviousAttribute().getR());
    assertEquals(0, m.getPreviousAttribute().getG());
    assertEquals(0, m.getPreviousAttribute().getB());
    assertEquals(20, m.getPreviousAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(0, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).setAngle(30).build());
    assertEquals(3.0, m.getPreviousAttribute().getX());
    assertEquals(2.0, m.getPreviousAttribute().getY());
    assertEquals(1, m.getPreviousAttribute().getW());
    assertEquals(2, m.getPreviousAttribute().getH());
    assertEquals(3, m.getPreviousAttribute().getR());
    assertEquals(4, m.getPreviousAttribute().getG());
    assertEquals(5, m.getPreviousAttribute().getB());
    assertEquals(30, m.getPreviousAttribute().getAngle());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(100, m.getNewAttribute().getAngle());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testEditKeyframeStartNoAngle() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).build());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(5, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).build());
    assertEquals(3.0, m.getNewAttribute().getX());
    assertEquals(2.0, m.getNewAttribute().getY());
    assertEquals(1, m.getNewAttribute().getW());
    assertEquals(2, m.getNewAttribute().getH());
    assertEquals(3, m.getNewAttribute().getR());
    assertEquals(4, m.getNewAttribute().getG());
    assertEquals(5, m.getNewAttribute().getB());
    assertEquals(0, m.getNewAttribute().getAngle());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testEditKeyframeDNE() {
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).build());
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.editKeyframe(7, 3, 2, 1, 2, 3, 4, 5);
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
  }

  @Test
  public void testAdjustKeyframe() {
    Shape r = new Rectangle("R");
    Motion m = new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(0, 0, 255)).build());
    r.addMotion(m);
    assertEquals(0.0, m.getNewAttribute().getX());
    assertEquals(0.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(5, m.getNewAttribute().getH());
    assertEquals(0, m.getNewAttribute().getR());
    assertEquals(0, m.getNewAttribute().getG());
    assertEquals(255, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(5, m.getEndTime());
    m.adjustKeyframe(5, 8,
        new AttributeImpl.Builder(new Position2D(7, 6), 5, 4,
            new Color(3, 2, 1)).build());
    assertEquals(7.0, m.getNewAttribute().getX());
    assertEquals(6.0, m.getNewAttribute().getY());
    assertEquals(5, m.getNewAttribute().getW());
    assertEquals(4, m.getNewAttribute().getH());
    assertEquals(3, m.getNewAttribute().getR());
    assertEquals(2, m.getNewAttribute().getG());
    assertEquals(1, m.getNewAttribute().getB());
    assertEquals(0, m.getStartTime());
    assertEquals(8, m.getEndTime());
    m.adjustKeyframe(8, 1,
        new AttributeImpl.Builder(new Position2D(7, 6), 5, 4,
            new Color(3, 2, 1)).build());
    assertEquals(0, m.getStartTime());
    assertEquals(1, m.getEndTime());
    m.adjustKeyframe(1, 0,
        new AttributeImpl.Builder(new Position2D(7, 6), 5, 4,
            new Color(3, 2, 1)).build());
    assertEquals(0, m.getStartTime());
    assertEquals(0, m.getEndTime());
  }
}