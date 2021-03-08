import cs3500.animator.model.Attribute;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.Position2D;

import java.awt.Color;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttributeImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    Attribute attribute = new AttributeImpl.Builder(new Position2D(0, 0), -1, 5,
        Color.RED).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    Attribute attribute = new AttributeImpl.Builder(new Position2D(0, 0), 5, -1,
        Color.RED).build();
  }

  @Test
  public void testGetString() {
    Attribute attribute = new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
        Color.RED).build();
    assertEquals("0.0 0.0 5 5 255 0 0 0", attribute.getString());
    //assertEquals("0.0 0.0 5 5 255 0 0", attribute.getString());
    Attribute attribute1 = new AttributeImpl.Builder(new Position2D(5.5, 6.6),
        7, 8, Color.LIGHT_GRAY).build();
    //assertEquals("5.5 6.6 7 8 192 192 192", attribute1.getString());
    assertEquals("5.5 6.6 7 8 192 192 192 0", attribute1.getString());
    Attribute attribute2 = new AttributeImpl.Builder(new Position2D(-9.1, 6.6),
        7, 8, Color.LIGHT_GRAY).build();
    //assertEquals("-9.1 6.6 7 8 192 192 192", attribute2.getString());
    assertEquals("-9.1 6.6 7 8 192 192 192 0", attribute2.getString());
  }

  @Test
  public void testBuilderNoAngle() {
    Attribute attribute = new AttributeImpl.Builder(new Position2D(100, 10), 25,
        10, new Color(255, 0, 0)).build();
    TestCase.assertEquals(100, attribute.getX(), 0.001);
    TestCase.assertEquals(10, attribute.getY(), 0.001);
    assertEquals(25, attribute.getW());
    assertEquals(10, attribute.getH());
    assertEquals(255, attribute.getR());
    assertEquals(0, attribute.getG());
    assertEquals(0, attribute.getB());
    assertEquals(0, attribute.getAngle());
    //assertEquals(false, attribute.angleSet());
  }

  @Test
  public void testBuilderAngle() {
    Attribute attribute = new AttributeImpl.Builder(new Position2D(100, 10), 25,
        10, new Color(255, 0, 0)).setAngle(20).build();
    TestCase.assertEquals(100, attribute.getX(), 0.001);
    TestCase.assertEquals(10, attribute.getY(), 0.001);
    assertEquals(25, attribute.getW());
    assertEquals(10, attribute.getH());
    assertEquals(255, attribute.getR());
    assertEquals(0, attribute.getG());
    assertEquals(0, attribute.getB());
    assertEquals(20, attribute.getAngle());
  }

}
