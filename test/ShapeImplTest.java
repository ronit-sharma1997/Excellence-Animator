import junit.framework.TestCase;

import cs3500.animator.model.Motion;
import cs3500.animator.model.MotionImpl;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;

import static org.junit.Assert.assertEquals;

public class ShapeImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameR() {
    Shape r = new Rectangle(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameO() {
    Shape o = new Oval(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyNameR() {
    Shape r = new Rectangle("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyNameO() {
    Shape o = new Oval("");
  }

  @Test
  public void testGetAttribute() {
    Position2D p = new Position2D(0, 0);
    Shape r = new Rectangle("r");
    r.setValues(p, 5, 5, Color.RED);
    assertEquals(new AttributeImpl.Builder(p, 5, 5, Color.RED).build(),
        r.getAttribute());
    Shape o = new Rectangle("o");
    o.setValues(p, 5, 5, Color.RED);
    assertEquals(new AttributeImpl.Builder(p, 5, 5, Color.RED).build(),
        o.getAttribute());
  }

  @Test
  public void testGetAttributeAngle() {
    Position2D p = new Position2D(0, 0);
    Shape r = new Rectangle("r");
    r.setValues(p, 5, 5, Color.RED);
    assertEquals(new AttributeImpl.Builder(p, 5, 5, Color.RED).build(),
        r.getAttribute());
    Shape o = new Rectangle("o");
    o.setValues(p, 5, 5, Color.RED);
    assertEquals(new AttributeImpl.Builder(p, 5, 5, Color.RED).build(),
        o.getAttribute());
  }

  @Test
  public void testGetResultSimultaneous() {
    Position2D p = new Position2D(0, 0);
    Shape r = new Rectangle("r");
    r.setValues(p, 5, 5, Color.RED);
    r.addMotion(new MotionImpl(1, 2,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5, Color.RED).build(),
        new AttributeImpl.Builder(new Position2D(10, 10), 2, 10,
            Color.BLUE).build()));
    r.checkShapeMotions();
    assertEquals("shape r rectangle\n"
        + "motion r 1 0.0 0.0 5 5 255 0 0 0      2 10.0 10.0 2 10 0 0 255 0\n", r.getResult());

  }

  @Test
  public void testGetResultRegular() {
    Position2D p = new Position2D(0, 0);
    Shape r = new Rectangle("r");
    r.setValues(p, 5, 5, Color.RED);
    r.addMotion(new MotionImpl(1, 2,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5, Color.RED).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            Color.BLUE).build()));
    r.addMotion(new MotionImpl(2, 3,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10),
        5, 5, Color.BLUE).build()));
    r.addMotion(new MotionImpl(3, 4,
        new AttributeImpl.Builder(new Position2D(10, 10), 5, 5,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10),
        5, 10, Color.BLUE).build()));
    r.addMotion(new MotionImpl(4, 6,
        new AttributeImpl.Builder(new Position2D(10, 10), 5, 10,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10), 2,
        10, Color.BLUE).build()));
    r.checkShapeMotions();
    assertEquals("shape r rectangle\n"
        + "motion r 1 0.0 0.0 5 5 255 0 0 0      2 0.0 0.0 5 5 0 0 255 0\n"
        + "motion r 2 0.0 0.0 5 5 0 0 255 0      3 10.0 10.0 5 5 0 0 255 0\n"
        + "motion r 3 10.0 10.0 5 5 0 0 255 0      4 10.0 10.0 5 10 0 0 255 0\n"
        + "motion r 4 10.0 10.0 5 10 0 0 255 0      6 10.0 10.0 2 10 0 0 255 0\n", r.getResult());

    Shape o = new Oval("o");
    o.setValues(p, 5, 5, Color.RED);
    assertEquals("shape o oval\n", o.getResult());
    o.addMotion(new MotionImpl(1, 2,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5, Color.RED).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            Color.BLUE).build()));
    o.addMotion(new MotionImpl(2, 3,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10),
        5, 5, Color.BLUE).build()));
    o.addMotion(new MotionImpl(3, 4,
        new AttributeImpl.Builder(new Position2D(10, 10), 5, 5,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10), 5,
        10, Color.BLUE).build()));
    o.addMotion(new MotionImpl(4, 6,
        new AttributeImpl.Builder(new Position2D(10, 10), 5, 10,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(10, 10), 2,
        10, Color.BLUE).build()));
    assertEquals("shape o oval\n"
        + "motion o 1 0.0 0.0 5 5 255 0 0 0      2 0.0 0.0 5 5 0 0 255 0\n"
        + "motion o 2 0.0 0.0 5 5 0 0 255 0      3 10.0 10.0 5 5 0 0 255 0\n"
        + "motion o 3 10.0 10.0 5 5 0 0 255 0      4 10.0 10.0 5 10 0 0 255 0\n"
        + "motion o 4 10.0 10.0 5 10 0 0 255 0      6 10.0 10.0 2 10 0 0 255 0\n", o.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddChangeNullInputR() {
    Position2D p = new Position2D(0, 0);
    Shape r = new Rectangle("r");
    r.addMotion(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddChangeNullInputO() {
    Position2D p = new Position2D(0, 0);
    Shape o = new Rectangle("o");
    o.addMotion(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingChangeWidth() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 50,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200), 25,
        50, Color.BLUE).build()));
    o.addMotion(new MotionImpl(69, 75,
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 50,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200), 50,
        50, Color.BLUE).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingChangeHeight() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        25, 25, Color.BLUE).build()));
    o.addMotion(new MotionImpl(69, 75,
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 25,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        50, 100, Color.BLUE).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingChangePosition() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(300, 300),
        25, 25, Color.BLUE).build()));
    o.addMotion(new MotionImpl(69, 75,
        new AttributeImpl.Builder(new Position2D(300, 300), 25, 25,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(300, 300),
        50, 100, Color.BLUE).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingChangeColor() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.RED).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        50, 100, Color.BLUE).build()));
    o.addMotion(new MotionImpl(69, 75,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        50, 100, Color.RED).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchColor() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.RED).build(), new AttributeImpl.Builder(new Position2D(200, 200), 50,
        100, Color.BLUE).build()));
    o.addMotion(new MotionImpl(70, 75,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.GREEN).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        50, 100, Color.RED).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchPosition() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.RED).build(), new AttributeImpl.Builder(new Position2D(300, 300), 50,
        100, Color.BLUE).build()));
    o.addMotion(new MotionImpl(70, 75,
        new AttributeImpl.Builder(new Position2D(400, 400), 50, 100,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200), 50,
        100, Color.RED).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchWidth() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.RED).build(), new AttributeImpl.Builder(new Position2D(300, 300), 25,
        100, Color.BLUE).build()));
    o.addMotion(new MotionImpl(70, 75,
        new AttributeImpl.Builder(new Position2D(300, 300), 26, 100,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200), 50,
        100, Color.RED).build()));
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchHeight() {
    Position2D p = new Position2D(200, 200);
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            Color.RED).build(), new AttributeImpl.Builder(new Position2D(300, 300),
        25, 99, Color.BLUE).build()));
    o.addMotion(new MotionImpl(70, 75,
        new AttributeImpl.Builder(new Position2D(300, 300), 25, 98,
            Color.BLUE).build(), new AttributeImpl.Builder(new Position2D(200, 200),
        50, 100, Color.RED).build()));
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeNoMotions() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, 5, 5, 5, 5, 255, 0, 0);
    assertEquals(1, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeNoMotionsAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).setAngle(100).build());
    assertEquals(1, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeNoMotionsNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).build());
    assertEquals(1, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeAfterMotions() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, 5, 5, 5, 5, 255, 0, 0);
    o.addKeyframe(5, 6, 6, 6, 6, 255, 0, 0);
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }


  @Test
  public void testAddKeyframeAfterMotionsAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).setAngle(20).build());
    o.addKeyframe(5, new AttributeImpl.Builder(new Position2D(6, 6), 6, 6,
        new Color(255, 0, 0)).setAngle(20).build());
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeAfterMotionsNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).build());
    o.addKeyframe(5, new AttributeImpl.Builder(new Position2D(6, 6), 6, 6,
        new Color(255, 0, 0)).build());
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeBeforeMotions() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, 5, 255, 0, 0);
    o.addKeyframe(0, 6, 6, 6, 6, 255, 0, 0);
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeBeforeMotionsAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).setAngle(20).build());
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(6, 6), 6, 6,
        new Color(255, 0, 0)).setAngle(100).build());
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeBeforeMotionsNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).build());
    o.addKeyframe(0, new AttributeImpl.Builder(new Position2D(6, 6), 6, 6,
        new Color(255, 0, 0)).build());
    assertEquals(2, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test
  public void testAddKeyframeInBetweenMotions() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, 5, 255, 0, 0);
    o.addKeyframe(0, 6, 6, 6, 6, 255, 0, 0);
    o.addKeyframe(5, 6, 6, 6, 6, 255, 0, 0);
    o.addKeyframe(3, 4, 4, 4, 4, 255, 0, 0);
    assertEquals(4, o.getMotions().size());
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeTime() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, 5, 5, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeTimeAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).setAngle(20).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeTimeNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), 5, 5,
        new Color(255, 0, 0)).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeWidth() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, -1, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeWidthAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), -1, 5,
        new Color(255, 0, 0)).setAngle(20).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeWidthNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), -1, 5,
        new Color(255, 0, 0)).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeHeight() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, -1, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeHeightAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), 5, -5,
        new Color(255, 0, 0)).setAngle(20).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeHeightNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(-1, new AttributeImpl.Builder(new Position2D(5, 5), 5, -5,
        new Color(255, 0, 0)).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeR() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, 5, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeG() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, 5, 255, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNegativeB() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, 5, 5, 5, 5, 255, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeTime() {
    Shape o = new Rectangle("o");
    o.editKeyframe(-1, 5, 5, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeWidth() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, -1, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeWidthAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), -5, 5,
        new Color(255, 0, 0)).setAngle(20).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeWidthNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), -5, 5,
        new Color(255, 0, 0)).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeHeight() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, 5, -1, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeHeightAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), 5, -5,
        new Color(255, 0, 0)).setAngle(20).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeHeightNoAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(2, new AttributeImpl.Builder(new Position2D(5, 5), 5, -5,
        new Color(255, 0, 0)).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeR() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, 5, 5, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeG() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, 5, 5, 255, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNegativeB() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, 5, 5, 255, 0, -1);
  }

  @Test
  public void testEditKeyframeDNE() {
    Shape o = new Rectangle("o");
    o.editKeyframe(2, 5, 5, 5, 5, 255, 0, 0);
    o.checkShapeMotions();
    assertEquals(0, o.getMotions().size());
  }

  @Test
  public void testEditKeyframeChangeAdjacentFrame() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).build()));
    List<Motion> motionList = o.getMotions();
    assertEquals(2, motionList.size());
    Motion m = motionList.get(0);
    Motion m1 = motionList.get(1);
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());

    TestCase.assertEquals(0.0, m1.getPreviousAttribute().getX());
    TestCase.assertEquals(0.0, m1.getPreviousAttribute().getY());
    TestCase.assertEquals(5, m1.getPreviousAttribute().getW());
    TestCase.assertEquals(10, m1.getPreviousAttribute().getH());
    TestCase.assertEquals(255, m1.getPreviousAttribute().getR());
    TestCase.assertEquals(0, m1.getPreviousAttribute().getG());
    TestCase.assertEquals(0, m1.getPreviousAttribute().getB());
    TestCase.assertEquals(5, m1.getStartTime());
    TestCase.assertEquals(10, m1.getEndTime());
    // Edit keyframe
    m.editKeyframe(5, 3, 2, 1, 2, 3, 4, 5);
    m1.editKeyframe(5, 3, 2, 1, 2, 3, 4, 5);
    // Check that values changed to what they should have for specified frame
    TestCase.assertEquals(3.0, m.getNewAttribute().getX());
    TestCase.assertEquals(2.0, m.getNewAttribute().getY());
    TestCase.assertEquals(1, m.getNewAttribute().getW());
    TestCase.assertEquals(2, m.getNewAttribute().getH());
    TestCase.assertEquals(3, m.getNewAttribute().getR());
    TestCase.assertEquals(4, m.getNewAttribute().getG());
    TestCase.assertEquals(5, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // Check that adjacent frame changed as well
    TestCase.assertEquals(3.0, m1.getPreviousAttribute().getX());
    TestCase.assertEquals(2.0, m1.getPreviousAttribute().getY());
    TestCase.assertEquals(1, m1.getPreviousAttribute().getW());
    TestCase.assertEquals(2, m1.getPreviousAttribute().getH());
    TestCase.assertEquals(3, m1.getPreviousAttribute().getR());
    TestCase.assertEquals(4, m1.getPreviousAttribute().getG());
    TestCase.assertEquals(5, m1.getPreviousAttribute().getB());
    TestCase.assertEquals(5, m1.getStartTime());
    TestCase.assertEquals(10, m1.getEndTime());
    o.checkShapeMotions();
  }

  @Test
  public void testEditKeyframeChangeAdjacentFrameAngle() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(20).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).setAngle(360).build()));
    List<Motion> motionList = o.getMotions();
    assertEquals(2, motionList.size());
    Motion m = motionList.get(0);
    Motion m1 = motionList.get(1);
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(100, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());

    TestCase.assertEquals(0.0, m1.getPreviousAttribute().getX());
    TestCase.assertEquals(0.0, m1.getPreviousAttribute().getY());
    TestCase.assertEquals(5, m1.getPreviousAttribute().getW());
    TestCase.assertEquals(10, m1.getPreviousAttribute().getH());
    TestCase.assertEquals(255, m1.getPreviousAttribute().getR());
    TestCase.assertEquals(0, m1.getPreviousAttribute().getG());
    TestCase.assertEquals(0, m1.getPreviousAttribute().getB());
    TestCase.assertEquals(100, m1.getPreviousAttribute().getAngle());
    TestCase.assertEquals(5, m1.getStartTime());
    TestCase.assertEquals(10, m1.getEndTime());
    // Edit keyframe
    m.editKeyframe(5, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).setAngle(200).build());
    m1.editKeyframe(5, new AttributeImpl.Builder(new Position2D(3, 2), 1, 2,
        new Color(3, 4, 5)).setAngle(200).build());
    // Check that values changed to what they should have for specified frame
    TestCase.assertEquals(3.0, m.getNewAttribute().getX());
    TestCase.assertEquals(2.0, m.getNewAttribute().getY());
    TestCase.assertEquals(1, m.getNewAttribute().getW());
    TestCase.assertEquals(2, m.getNewAttribute().getH());
    TestCase.assertEquals(3, m.getNewAttribute().getR());
    TestCase.assertEquals(4, m.getNewAttribute().getG());
    TestCase.assertEquals(5, m.getNewAttribute().getB());
    TestCase.assertEquals(200, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // Check that adjacent frame changed as well
    TestCase.assertEquals(3.0, m1.getPreviousAttribute().getX());
    TestCase.assertEquals(2.0, m1.getPreviousAttribute().getY());
    TestCase.assertEquals(1, m1.getPreviousAttribute().getW());
    TestCase.assertEquals(2, m1.getPreviousAttribute().getH());
    TestCase.assertEquals(3, m1.getPreviousAttribute().getR());
    TestCase.assertEquals(4, m1.getPreviousAttribute().getG());
    TestCase.assertEquals(5, m1.getPreviousAttribute().getB());
    TestCase.assertEquals(200, m1.getPreviousAttribute().getAngle());
    TestCase.assertEquals(5, m1.getStartTime());
    TestCase.assertEquals(10, m1.getEndTime());
    o.checkShapeMotions();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeNonexistent() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).build()));
    o.deleteKeyframe(11);
  }

  @Test
  public void testDeleteFirstKeyframe() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // delete the first frame
    o.deleteKeyframe(0);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());
    // Check all values after
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(15, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(5, m.getStartTime());
    TestCase.assertEquals(10, m.getEndTime());
  }

  @Test
  public void testDeleteFirstKeyframeAngle() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(0).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).setAngle(360).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(100, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // delete the first frame
    o.deleteKeyframe(0);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());
    // Check all values after
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(15, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(360, m.getNewAttribute().getAngle());
    TestCase.assertEquals(5, m.getStartTime());
    TestCase.assertEquals(10, m.getEndTime());
  }

  @Test
  public void testDeleteLastKeyframe() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // delete the last frame
    o.deleteKeyframe(10);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());
    // Check all values after
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
  }

  @Test
  public void testDeleteLastKeyframeAngle() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(0).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(100).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).setAngle(400).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(100, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    // delete the last frame
    o.deleteKeyframe(10);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());
    // Check all values after
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());
    TestCase.assertEquals(100, m.getNewAttribute().getAngle());
  }

  @Test
  public void testDeleteMiddleFrame() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());

    // delete the start frame of second motion
    o.deleteKeyframe(5);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());

    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(15, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(10, m.getEndTime());
  }

  @Test
  public void testDeleteMiddleFrameAngle() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(0, 5,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
            new Color(255, 0, 0)).setAngle(0).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(10).build()));
    o.addMotion(new MotionImpl(5, 10,
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 10,
            new Color(255, 0, 0)).setAngle(20).build(),
        new AttributeImpl.Builder(new Position2D(0, 0), 5, 15,
            new Color(255, 0, 0)).setAngle(100).build()));
    List<Motion> motionList = o.getMotions();
    Motion m = motionList.get(0);
    assertEquals(2, motionList.size());
    // Check all values before
    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(10, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(10, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(5, m.getEndTime());

    // delete the start frame of second motion
    o.deleteKeyframe(5);
    motionList = o.getMotions();
    m = motionList.get(0);
    assertEquals(1, motionList.size());

    TestCase.assertEquals(0.0, m.getNewAttribute().getX());
    TestCase.assertEquals(0.0, m.getNewAttribute().getY());
    TestCase.assertEquals(5, m.getNewAttribute().getW());
    TestCase.assertEquals(15, m.getNewAttribute().getH());
    TestCase.assertEquals(255, m.getNewAttribute().getR());
    TestCase.assertEquals(0, m.getNewAttribute().getG());
    TestCase.assertEquals(0, m.getNewAttribute().getB());
    TestCase.assertEquals(100, m.getNewAttribute().getAngle());
    TestCase.assertEquals(0, m.getStartTime());
    TestCase.assertEquals(10, m.getEndTime());
  }

  @Test
  public void testDeleteAllButOne() {
    Shape o = new Rectangle("o");
    o.addKeyframe(10, 1, 1, 1, 1, 1, 1, 1);
    o.addKeyframe(20, 1, 1, 1, 1, 1, 1, 1);
    o.addKeyframe(30, 1, 1, 1, 1, 1, 1, 1);
    o.deleteKeyframe(30);
    o.deleteKeyframe(20);
    List<Motion> motionList = o.getMotions();
    assertEquals(1, motionList.size());
    Motion m = motionList.get(0);
    o.checkShapeMotions();
  }

  @Test
  public void testDeleteAllButOneAngle() {
    Shape o = new Rectangle("o");
    o.addKeyframe(10, new AttributeImpl.Builder(new Position2D(1, 1), 1, 1,
        new Color(1, 1, 1)).build());
    o.addKeyframe(20, new AttributeImpl.Builder(new Position2D(1, 1), 1, 1,
        new Color(1, 1, 1)).build());
    o.addKeyframe(30, new AttributeImpl.Builder(new Position2D(1, 1), 1, 1,
        new Color(1, 1, 1)).build());
    o.deleteKeyframe(30);
    o.deleteKeyframe(20);
    List<Motion> motionList = o.getMotions();
    assertEquals(1, motionList.size());
    Motion m = motionList.get(0);
    o.checkShapeMotions();
  }

  @Test
  public void testNoMotions() {
    Shape o = new Rectangle("o");
    o.addKeyframe(10, 1, 1, 1, 1, 1, 1, 1);
    assertEquals(false, o.noMotions());
    o.deleteKeyframe(10);
    assertEquals(true, o.noMotions());
    o.addMotion(new MotionImpl(1, 2, new AttributeImpl.Builder(new Position2D(0,
        0), 5, 5, Color.RED).build(),
        new AttributeImpl.Builder(new Position2D(10, 10), 2, 10,
            Color.BLUE).build()));
    assertEquals(false, o.noMotions());
    o.deleteKeyframe(2);
    assertEquals(false, o.noMotions());
    o.deleteKeyframe(1);
    assertEquals(true, o.noMotions());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteDNE() {
    Shape o = new Rectangle("o");
    o.deleteKeyframe(2);
  }

  @Test
  public void testSmallDemoAddAndDelete() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(10, 50,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(50, 51,
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(51, 70,
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(300, 300), 25, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(70, 100,
        new AttributeImpl.Builder(new Position2D(300, 300), 25, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    List<Motion> motionList = o.getMotions();
    assertEquals(5, motionList.size());
    assertEquals(100, o.getMaxEndTime());
    o.addKeyframe(110, 400, 400, 25, 100, 255, 0, 0);
    o.checkShapeMotions();
    motionList = o.getMotions();
    assertEquals(6, motionList.size());
    assertEquals(110, o.getMaxEndTime());
    o.deleteKeyframe(110);
    o.checkShapeMotions();
    motionList = o.getMotions();
    assertEquals(5, motionList.size());
    assertEquals(100, o.getMaxEndTime());
    o.deleteKeyframe(100);
    o.deleteKeyframe(70);
    o.deleteKeyframe(50);
    o.deleteKeyframe(51);
    o.deleteKeyframe(10);
    o.checkShapeMotions();
    motionList = o.getMotions();
    assertEquals(1, motionList.size());
    assertEquals(1, o.getMaxEndTime());
    o.deleteKeyframe(1);
    o.checkShapeMotions();
    motionList = o.getMotions();
    assertEquals(0, motionList.size());
    assertEquals(0, o.getMaxEndTime());
  }

  @Test
  public void testDeleteKeyframeDur01Motion() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 1,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    o.deleteKeyframe(1);
    o.checkShapeMotions();
    assertEquals(0, o.getMotions().size());
  }

  @Test
  public void testDeleteKeyframe1MotionEndFrame() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    o.deleteKeyframe(10);
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    assertEquals(1, o.getMotions().get(0).getEndTime());
    TestCase.assertEquals(200.0, o.getMotions().get(0).getNewAttribute().getX());
    TestCase.assertEquals(200.0, o.getMotions().get(0).getNewAttribute().getY());
  }

  @Test
  public void testDeleteKeyframe1MotionStartFrame() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    o.deleteKeyframe(1);
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    assertEquals(10, o.getMotions().get(0).getStartTime());
    TestCase.assertEquals(300.0, o.getMotions().get(0).getNewAttribute().getX());
    TestCase.assertEquals(300.0, o.getMotions().get(0).getNewAttribute().getY());
  }

  @Test
  public void testDeleteKeyframeDur0() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 1,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(2, o.getMotions().size());
    o.deleteKeyframe(1);
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    assertEquals(10, o.getMotions().get(0).getStartTime());
    assertEquals(10, o.getMotions().get(0).getEndTime());
    TestCase.assertEquals(25, o.getMotions().get(0).getNewAttribute().getW());
    TestCase.assertEquals(25, o.getMotions().get(0).getPreviousAttribute().getW());
  }

  @Test
  public void testDeleteKeyframeNextDur0() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(10, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(2, o.getMotions().size());
    o.deleteKeyframe(10);
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().size());
    assertEquals(1, o.getMotions().get(0).getStartTime());
    assertEquals(1, o.getMotions().get(0).getEndTime());
    TestCase.assertEquals(50, o.getMotions().get(0).getNewAttribute().getW());
    TestCase.assertEquals(50, o.getMotions().get(0).getPreviousAttribute().getW());
  }

  @Test
  public void testDeleteKeyframeNextDur04Motions() {
    Shape o = new Rectangle("o");
    o.addMotion(new MotionImpl(1, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(10, 10,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(10, 20,
        new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.addMotion(new MotionImpl(20, 20,
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build(),
        new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
            new Color(255, 0, 0)).build()));
    o.checkShapeMotions();
    assertEquals(4, o.getMotions().size());
    o.deleteKeyframe(10);
    o.checkShapeMotions();
    assertEquals(2, o.getMotions().size());
    assertEquals(1, o.getMotions().get(0).getStartTime());
    assertEquals(20, o.getMotions().get(0).getEndTime());
    assertEquals(50, o.getMotions().get(0).getPreviousAttribute().getW());
    assertEquals(50, o.getMotions().get(0).getNewAttribute().getW());
    assertEquals(20, o.getMotions().get(1).getStartTime());
    assertEquals(20, o.getMotions().get(1).getEndTime());
    assertEquals(50, o.getMotions().get(1).getPreviousAttribute().getW());
    assertEquals(50, o.getMotions().get(1).getNewAttribute().getW());
    o.deleteKeyframe(20);
    o.checkShapeMotions();
    assertEquals(1, o.getMotions().get(0).getStartTime());
    assertEquals(1, o.getMotions().get(0).getEndTime());
    assertEquals(50, o.getMotions().get(0).getPreviousAttribute().getW());
    assertEquals(50, o.getMotions().get(0).getNewAttribute().getW());
  }
}