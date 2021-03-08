import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.ChangeType;
import cs3500.animator.model.ImmutableAnimator;
import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import cs3500.animator.util.AnimationBuilder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimatorImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape(null, "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullType() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeEmptyName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("", "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalid() {
    Animator animation = new AnimatorImpl();
    animation.addShape("r", "krispy");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePosition2DNullName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion(null,
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePosition2DNullFromPosition() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion(null,
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePosition2DShapeDNE() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("mom",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePosition2DInvalidStartTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            -1, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePosition2DInvalidEndTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            3, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNullName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion(null,
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 6, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthInvalidWidthTo() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, -5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthInvalidWidthFrom() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, -5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthShapeDNE() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("mom",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 6, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthInvalidStartTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            -1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 6, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthInvalidEndTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            3, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 6, 5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNullName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion(null,
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 6, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightInvalidHeight() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, -5, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightShapeDNE() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("mom",
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 6, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightInvalidStartTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            -1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 6, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightInvalidEndTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            3, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 6, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNullName() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion(null,
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 5, 25, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNullColor() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 5, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorShapeDNE() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("mom",
            1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 5, 55, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidStartTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            -1, 0, 0, 0, 5, 255, 0, 0,
            2, 0, 0, 0, 5, 25, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidEndTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            3, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 55, 0, 0);
  }

  @Test
  public void testGetBoundsX() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 55, 0, 0);
    ImmutableAnimator animator = animator1.build();
    assertEquals(0, animator.getBoundsX());
  }

  @Test
  public void testGetBoundsY() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 55, 0, 0);
    ImmutableAnimator animator = animator1.build();
    assertEquals(0, animator.getBoundsY());
  }

  @Test
  public void testGetBoundsWidth() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 55, 0, 0);
    ImmutableAnimator animator = animator1.build();
    assertEquals(500, animator.getBoundsWidth());
  }

  @Test
  public void testGetBoundsHeight() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 0, 0, 5, 5, 55, 0, 0);
    ImmutableAnimator animator = animator1.build();
    assertEquals(500, animator.getBoundsHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBoundsWidth() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        0, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBoundsHeight() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGapInTimeAddChange() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            9, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
  }

  @Test
  public void testTweeningOneShapeNormalSpeed() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(11);
    assertEquals(1, shapes.size());
    assertEquals("297.0 297.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(12);
    assertEquals(1, shapes.size());
    assertEquals("295.0 295.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(13);
    assertEquals(1, shapes.size());
    assertEquals("292.0 292.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(14);
    assertEquals(1, shapes.size());
    assertEquals("290.0 290.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(15);
    assertEquals(1, shapes.size());
    assertEquals("287.0 287.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());


  }

  @Test
  public void testTweeningTimeZeroNormalSpeed() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            0, 100, 75, 20, 15, 255, 0, 0,
            0, 100, 75, 20, 15, 255, 0, 0)
        .addMotion("r",
            0, 100, 75, 20, 15, 255, 0, 0,
            10, 100, 0, 20, 15, 255, 0, 0);
    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(0);
    assertEquals(1, shapes.size());
    assertEquals("100.0 75.0 20 15 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("100.0 67.0 20 15 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(2);
    assertEquals(1, shapes.size());
    assertEquals("100.0 60.0 20 15 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(9);
    assertEquals(1, shapes.size());
    assertEquals("100.0 7.0 20 15 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(10);
    assertEquals(1, shapes.size());
    assertEquals("100.0 0.0 20 15 255 0 0 0", shapes.get(0).getAttribute().getString());
  }

  @Test
  public void testTweeningOneShapeDoubleSpeed1() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(0);
    assertEquals(0, shapes.size());
    shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(2);
    assertEquals(1, shapes.size());
    assertEquals("211.0 211.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(10);
    assertEquals(1, shapes.size());
    assertEquals("300.0 300.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(11);
    assertEquals(1, shapes.size());
    assertEquals("297.0 297.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(12);
    assertEquals(1, shapes.size());
    assertEquals("295.0 295.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(13);
    assertEquals(1, shapes.size());
    assertEquals("292.0 292.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(14);
    assertEquals(1, shapes.size());
    assertEquals("290.0 290.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(15);
    assertEquals(1, shapes.size());
    assertEquals("287.0 287.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());


  }

  @Test
  public void testTweeningOneShapeNormalSpeed1() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(2);
    assertEquals("211.0 211.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(3);
    assertEquals("222.0 222.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(4);
    assertEquals("233.0 233.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(5);
    assertEquals("244.0 244.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(6);
    assertEquals("255.0 255.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(7);
    assertEquals("266.0 266.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(8);
    assertEquals("277.0 277.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(9);
    assertEquals("288.0 288.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(10);
    assertEquals("300.0 300.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
    shapes = animation.getForwardAnimationStateTick(11);
    assertEquals("297.0 297.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    assertEquals(1, shapes.size());
  }

  @Test
  public void testTweeningOneShapeTick1() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 190, 180, 5, 5, 255, 0, 0,
            1, 190, 180, 5, 5, 255, 0, 0);

    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(0);
    assertEquals(0, shapes.size());
    shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("190.0 180.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(2);
    assertEquals(0, shapes.size());
  }

  @Test
  public void testTweeningNoChanges() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle");

    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(10);
    assertEquals(0, shapes.size());
  }

  @Test
  public void testTweeningNoMoreMotions() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);

    Animator animation = animator1.build();
    List<ImmutableShape> shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(2);
    assertEquals(1, shapes.size());
    assertEquals("211.0 211.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(3);
    assertEquals(1, shapes.size());
    assertEquals("222.0 222.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(4);
    assertEquals(1, shapes.size());
    assertEquals("233.0 233.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(5);
    assertEquals(1, shapes.size());
    assertEquals("244.0 244.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(6);
    assertEquals(1, shapes.size());
    assertEquals("255.0 255.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(7);
    assertEquals(1, shapes.size());
    assertEquals("266.0 266.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(8);
    assertEquals(1, shapes.size());
    assertEquals("277.0 277.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(9);
    assertEquals(1, shapes.size());
    assertEquals("288.0 288.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(10);
    assertEquals(1, shapes.size());
    assertEquals("300.0 300.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(11);
    assertEquals(1, shapes.size());
    assertEquals("297.0 297.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(12);
    assertEquals(1, shapes.size());
    assertEquals("295.0 295.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(13);
    assertEquals(1, shapes.size());
    assertEquals("292.0 292.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(14);
    assertEquals(1, shapes.size());
    assertEquals("290.0 290.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(15);
    assertEquals(1, shapes.size());
    assertEquals("287.0 287.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(16);
    assertEquals(1, shapes.size());
    assertEquals("285.0 285.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(17);
    assertEquals(1, shapes.size());
    assertEquals("282.0 282.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(18);
    assertEquals(1, shapes.size());
    assertEquals("280.0 280.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(19);
    assertEquals(1, shapes.size());
    assertEquals("277.0 277.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(20);
    assertEquals(1, shapes.size());
    assertEquals("275.0 275.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(21);
    assertEquals(1, shapes.size());
    assertEquals("272.0 272.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(22);
    assertEquals(1, shapes.size());
    assertEquals("270.0 270.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(23);
    assertEquals(1, shapes.size());
    assertEquals("267.0 267.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(24);
    assertEquals(1, shapes.size());
    assertEquals("265.0 265.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(25);
    assertEquals(1, shapes.size());
    assertEquals("262.0 262.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(50);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(51);
    assertEquals(0, shapes.size());
    shapes = animation.getForwardAnimationStateTick(1);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(10);
    assertEquals(1, shapes.size());
    assertEquals("300.0 300.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(5);
    assertEquals(1, shapes.size());
    assertEquals("244.0 244.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(50);
    assertEquals(1, shapes.size());
    assertEquals("200.0 200.0 5 5 255 0 0 0", shapes.get(0).getAttribute().getString());
    shapes = animation.getForwardAnimationStateTick(51);
    assertEquals(0, shapes.size());
  }

  @Test
  public void testGetMaxEndTime() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("R", "rectangle")
        .declareShape("C", "ellipse")
        .addMotion("R",
            1, 200, 200, 50, 100, 255, 0, 0,
            5, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("R",
            5, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R", 10, 300, 300, 50, 100, 255, 0, 0,
            15, 300, 300, 25, 100, 255, 0, 0)
        .addMotion("R", 15, 300, 300, 25, 100, 255, 0, 0,
            20, 200, 200, 25, 100, 255, 0, 0)
        .addMotion("C",
            6, 440, 70, 120, 60, 0, 0, 255,
            10, 440, 70, 120, 60, 0, 0, 255)
        .addMotion("C",
            10, 440, 70, 120, 60, 0, 0, 255,
            15, 440, 250, 120, 60, 0, 0, 255)
        //multiple changes occur here
        .addMotion("C", 15, 440, 250, 120, 60, 0, 0, 255,
            20, 440, 370, 120, 60, 0, 170, 85)
        .addMotion("C", 20, 440, 370, 120, 60, 0, 170, 85,
            30, 440, 370, 120, 60, 0, 255, 0);
    Animator animation = animator1.build();
    Map<String, ImmutableShape> shapes = animation.getShapeMap();
    assertEquals(2, shapes.size());
    assertEquals(20, shapes.get("R").getMaxEndTime());
    assertEquals(30, shapes.get("C").getMaxEndTime());
  }

  @Test
  public void testOneShape() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    Map<String, ImmutableShape> testShape = animation.getShapeMap();
    assertEquals(1, testShape.size());
    List<Motion> testChanges = testShape.get("r").getMotions();
    assertEquals(2, testChanges.size());
    assertEquals(1, testChanges.get(0).getChangeType().size());
    assertEquals(ChangeType.POSITION, testChanges.get(0).getChangeType().get(0));
    assertEquals(1, testChanges.get(0).getStartTime());
    assertEquals(10, testChanges.get(0).getEndTime());
    int[] shape = new int[8];
    shape[0] = 200;
    shape[1] = 200;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    shape[7] = 0;
    assertArrayEquals(shape, testShape.get("r").getAttribute().getAttributes());
    assertArrayEquals(shape, testShape.get("r").getMotions().get(0).getPreviousState());
    shape[0] = 300;
    shape[1] = 300;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    assertArrayEquals(shape, testShape.get("r").getMotions().get(0).getNewState());
    assertArrayEquals(shape, testShape.get("r").getMotions().get(1).getPreviousState());
    shape[0] = 200;
    shape[1] = 200;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    assertArrayEquals(shape, testShape.get("r").getMotions().get(1).getNewState());
  }

  @Test
  public void testBuilder() {
    AnimationBuilder<Animator> animation = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle").addMotion("r", 1, 0,
            0, 5,
            5, 255, 0, 0, 10, 0, 0, 10,
            5, 255, 0, 0);
    Animator animator1 = animation.build();
    Map<String, ImmutableShape> testShape = animator1.getShapeMap();
    assertEquals(1, testShape.size());
    assertEquals(true, testShape.containsKey("r"));
    assertEquals(1, testShape.get("r").getMotions().size());
    assertEquals(1, testShape.get("r").getMotions().get(0).getStartTime());
    assertEquals(10, testShape.get("r").getMotions().get(0).getEndTime());
    assertEquals(1, testShape.get("r").getMotions().get(0).getChangeType().size());
    assertEquals(ChangeType.WIDTH, testShape.get("r").getMotions().get(0).getChangeType().get(0));
    int[] width = new int[8];
    width[0] = 0;
    width[1] = 0;
    width[2] = 5;
    width[3] = 5;
    width[4] = 255;
    width[5] = 0;
    width[6] = 0;
    width[7] = 0;
    assertArrayEquals(width, testShape.get("r").getMotions().get(0).getPreviousState());
    width[0] = 0;
    width[1] = 0;
    width[2] = 10;
    width[3] = 5;
    width[4] = 255;
    width[5] = 0;
    width[6] = 0;
    assertArrayEquals(width, testShape.get("r").getMotions().get(0).getNewState());
  }

  @Test
  public void addMotionsOutOfOrder() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 100, 100, 4, 4, 155, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            50, 200, 200, 5, 5, 255, 0, 0,
            100, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            10, 200, 200, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    Map<String, ImmutableShape> testShape = animation.getShapeMap();
    assertEquals(1, testShape.size());
    List<Motion> testChanges = testShape.get("r").getMotions();
    assertEquals(3, testChanges.size());

  }

  @Test
  public void testTwoShape() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 200, 200, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("r",
            1, 100, 100, 4, 4, 155, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o",
            10, 300, 300, 5, 5, 255, 0, 0,
            50, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("o",
            1, 200, 200, 5, 5, 255, 0, 0,
            10, 300, 300, 5, 5, 255, 0, 0);
    Animator animation = animator1.build();
    Map<String, ImmutableShape> testShape = animation.getShapeMap();
    assertEquals(2, testShape.size());
    List<Motion> testChanges = testShape.get("r").getMotions();
    assertEquals(2, testChanges.size());
    assertEquals(4, testChanges.get(0).getChangeType().size());
    assertEquals(ChangeType.POSITION, testChanges.get(0).getChangeType().get(0));
    assertEquals(ChangeType.WIDTH, testChanges.get(0).getChangeType().get(1));
    assertEquals(ChangeType.HEIGHT, testChanges.get(0).getChangeType().get(2));
    assertEquals(ChangeType.COLOR, testChanges.get(0).getChangeType().get(3));
    assertEquals(1, testChanges.get(1).getChangeType().size());
    assertEquals(ChangeType.NOCHANGE, testChanges.get(1).getChangeType().get(0));
    assertEquals(1, testChanges.get(0).getStartTime());
    assertEquals(10, testChanges.get(0).getEndTime());
    assertEquals(10, testChanges.get(1).getStartTime());
    assertEquals(50, testChanges.get(1).getEndTime());
    int[] shape = new int[8];
    shape[0] = 100;
    shape[1] = 100;
    shape[2] = 4;
    shape[3] = 4;
    shape[4] = 155;
    shape[5] = 0;
    shape[6] = 0;
    shape[7] = 0;
    assertArrayEquals(shape, testShape.get("r").getAttribute().getAttributes());
    assertArrayEquals(shape, testChanges.get(0).getPreviousState());
    shape[0] = 200;
    shape[1] = 200;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    shape[7] = 0;
    assertArrayEquals(shape, testChanges.get(0).getNewState());
    assertArrayEquals(shape, testChanges.get(1).getPreviousState());
    assertArrayEquals(shape, testChanges.get(1).getNewState());
    List<Motion> testChangesOval = testShape.get("o").getMotions();
    assertEquals(1, testChangesOval.get(0).getStartTime());
    assertEquals(10, testChangesOval.get(0).getEndTime());
    assertEquals(10, testChangesOval.get(1).getStartTime());
    assertEquals(50, testChangesOval.get(1).getEndTime());
    shape[0] = 200;
    shape[1] = 200;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    assertArrayEquals(shape, testShape.get("o").getAttribute().getAttributes());
    assertArrayEquals(shape, testChangesOval.get(0).getPreviousState());
    shape[0] = 300;
    shape[1] = 300;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    assertArrayEquals(shape, testChangesOval.get(0).getNewState());
    assertArrayEquals(shape, testChangesOval.get(1).getPreviousState());
    shape[0] = 200;
    shape[1] = 200;
    shape[2] = 5;
    shape[3] = 5;
    shape[4] = 255;
    shape[5] = 0;
    shape[6] = 0;
    assertArrayEquals(shape, testChangesOval.get(1).getNewState());
  }

  @Test
  public void testEmptyAnimation() {
    Animator animation = new AnimatorImpl.Builder().build();
    assertEquals(0, animation.getShapeMap().size());
  }

  @Test
  public void testEmptyAnimationOneShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle").build();
    assertEquals(1, animation.getShapeMap().size());
  }

  @Test
  public void testDeclareShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .declareShape("c", "ellipse")
        .addMotion("r", 1, 100, 100, 4, 4, 155, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .addMotion("c", 1, 100, 100, 4, 4, 155, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    Map<String, ImmutableShape> testShapes = animation.getShapeMap();
    assertEquals(2, testShapes.size());
    assertEquals(true, testShapes.containsKey("r"));
    assertEquals(true, testShapes.containsKey("c"));
    assertEquals(ShapeType.RECTANGLE, testShapes.get("r").getType());
    assertEquals(ShapeType.OVAL, testShapes.get("c").getType());
  }

  @Test
  public void testAddMotion() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    Map<String, ImmutableShape> testShapes = animation.getShapeMap();
    assertEquals(1, testShapes.size());
    assertEquals(true, testShapes.containsKey("r"));
    assertEquals(ShapeType.RECTANGLE, testShapes.get("r").getType());
    List<Motion> testChange = testShapes.get("r").getMotions();
    assertEquals(1, testChange.size());
    assertEquals(1, testChange.get(0).getStartTime());
    assertEquals(10, testChange.get(0).getEndTime());
    assertEquals(1, testChange.get(0).getChangeType().size());
    assertEquals(ChangeType.POSITION, testChange.get(0).getChangeType().get(0));
    int[] position = new int[8];
    position[0] = 100;
    position[1] = 100;
    position[2] = 5;
    position[3] = 5;
    position[4] = 255;
    position[5] = 0;
    position[6] = 0;
    position[7] = 0;
    assertArrayEquals(position, testChange.get(0).getPreviousState());
    position[0] = 200;
    position[1] = 200;
    position[2] = 5;
    position[3] = 5;
    position[4] = 255;
    position[5] = 0;
    position[6] = 0;
    assertArrayEquals(position, testChange.get(0).getNewState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionGreaterEndTime() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotion() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeNullName() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.deleteKeyframe(null, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeFrameDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.deleteKeyframe("r", 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeFrameShapeDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.deleteKeyframe("q", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNullName() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe(null, 100, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("r", 101, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameBadInputs() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("r", 100, -1, -1, -1, -1, -1, -1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameShapeDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("q", 100, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNullNameAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe(null, 100, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameDNEAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("r", 101, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameBadInputsAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("r", 100, new AttributeImpl.Builder(new Position2D(-1, -1),
        -1, -1, new Color(-1, -1, -1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeFrameShapeDNEAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.editKeyframe("q", 100, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNullName() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe(null, 100, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("r", 101, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameBadInputs() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("r", 100, -1, -1, -1, -1, -1, -1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameShapeDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("q", 100, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeNullNameAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe(null, 100, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameDNEAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("r", 101, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameBadInputsAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("r", 100, new AttributeImpl.Builder(new Position2D(-1, -1),
        -1, -1, new Color(-1, -1, -1)).setAngle(1).build());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFrameShapeDNEAttributeMethod() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, -255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.addKeyframe("q", 100, new AttributeImpl.Builder(new Position2D(1, 1),
        1, 1, new Color(1, 1, 1)).setAngle(1).build());
  }

  @Test
  public void testDeleteShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.deleteShape("o");
    ImmutableShape o = animation.getShapeMap().get("o");
    assertEquals(null, o);
    assertTrue(!animation.getShapeMap().containsKey("o"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShapeDNE() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 100, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 100, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    animation.deleteShape("q");
  }

  @Test
  public void testGetAnimationEndTime() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    assertEquals(10, animation.getAnimationEndTime());
  }

  @Test
  public void testGetShapeMapSortedByLayerNoLayers() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    assertEquals(shapes.get(0).getValue().getType(), sortedShapes.get(0).getValue().getType());
  }

  @Test
  public void testGetShapeMapSortedByLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    assertEquals(shapes.get(0).getValue().getType(), sortedShapes.get(1).getValue().getType());
  }

  @Test
  public void testGetShapeMapSortedByLayerSameLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    assertEquals(shapes.get(0).getValue().getName(), sortedShapes.get(2).getValue().getName());
    assertEquals(shapes.get(1).getValue().getName(), sortedShapes.get(0).getValue().getName());
    assertEquals(shapes.get(2).getValue().getName(), sortedShapes.get(3).getValue().getName());
    assertEquals(shapes.get(3).getValue().getName(), sortedShapes.get(1).getValue().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeclareLayerShapeThenShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeclareShapeThenLayerShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareLayer(1)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 1)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
  }

  @Test
  public void testDeclareLayerShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeclareLayerShapeInvalidLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareLayer(-1)
        .declareShape("r", "rectangle", -1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addShape("B", "rectangle", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeclareLayerShapeNullShape() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareLayer(1)
        .declareShape(null, "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addShape("B", "rectangle", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeclareLayerShapeInvalidType() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareLayer(1)
        .declareShape("r", "", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addShape("B", "rectangle", 2);
  }

  @Test
  public void testChangeLayerFromLayerLessThanToLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.changeLayer(0, 2);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("r", sortedShapes.get(0).getValue().getName());
    assertEquals("e", sortedShapes.get(1).getValue().getName());
    assertEquals("B", sortedShapes.get(2).getValue().getName());
    assertEquals("o", sortedShapes.get(3).getValue().getName());
    assertEquals("R", sortedShapes.get(4).getValue().getName());
  }


  @Test
  public void testChangeLayerFromLayerGreaterThanToLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.changeLayer(2, 0);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("B", sortedShapes.get(0).getValue().getName());
    assertEquals("o", sortedShapes.get(1).getValue().getName());
    assertEquals("R", sortedShapes.get(2).getValue().getName());
    assertEquals("r", sortedShapes.get(3).getValue().getName());
    assertEquals("e", sortedShapes.get(4).getValue().getName());
  }

  @Test
  public void testChangeLayerFromLayerSameAsToLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.changeLayer(1, 1);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
  }

  @Test
  public void testChangeLayerAllShapesInLayer0() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareShape("r", "rectangle", 0)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 0)
        .declareShape("R", "rectangle", 0).build();
    animation.addShape("B", "rectangle", 0);
    animation.addLayer(1);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("r", sortedShapes.get(0).getValue().getName());
    assertEquals("o", sortedShapes.get(1).getValue().getName());
    assertEquals("e", sortedShapes.get(2).getValue().getName());
    assertEquals("R", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.changeLayer(0, 1);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("r", sortedShapes.get(0).getValue().getName());
    assertEquals("o", sortedShapes.get(1).getValue().getName());
    assertEquals("e", sortedShapes.get(2).getValue().getName());
    assertEquals("R", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.changeLayer(1, 0);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("r", sortedShapes.get(0).getValue().getName());
    assertEquals("o", sortedShapes.get(1).getValue().getName());
    assertEquals("e", sortedShapes.get(2).getValue().getName());
    assertEquals("R", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeLayerInvalidLayerNegative() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse")
        .declareShape("R", "rectangle").build();
    animation.addShape("B", "rectangle");
    animation.changeLayer(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteInvalidLayerNeg() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse")
        .declareShape("R", "rectangle").build();
    animation.addShape("B", "rectangle");
    animation.deleteLayer(-1);
  }

  @Test
  public void testDeleteLayerOneLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse")
        .declareShape("R", "rectangle").build();
    animation.addShape("B", "rectangle");
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals("r", sortedShapes.get(0).getValue().getName());
    assertEquals("o", sortedShapes.get(1).getValue().getName());
    assertEquals("e", sortedShapes.get(2).getValue().getName());
    assertEquals("R", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    animation.deleteLayer(0);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals(0, sortedShapes.size());
    assertEquals(0, shapes.size());
  }

  @Test
  public void testDeleteLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    Map<String, ImmutableShape> shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    List<Map.Entry<String, ImmutableShape>> sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    List<Map.Entry<String, ImmutableShape>> shapes
        = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals("B", shapes.get(4).getValue().getName());
    assertEquals(5, shapes.size());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals("B", sortedShapes.get(4).getValue().getName());
    assertEquals(5, sortedShapes.size());
    animation.deleteLayer(2);
    shapesSorted = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : animation.getLayers().entrySet()) {
      for (String s : e.getValue()) {
        shapesSorted.put(s, animation.getShapeMap().get(s));
      }
    }
    sortedShapes =
        new ArrayList<>();
    for (Map.Entry<String, ImmutableShape> e : shapesSorted.entrySet()) {
      sortedShapes.add(e);
    }
    shapes = new ArrayList<>(animation.getShapeMap().entrySet());
    assertEquals("r", shapes.get(0).getValue().getName());
    assertEquals("o", shapes.get(1).getValue().getName());
    assertEquals("e", shapes.get(2).getValue().getName());
    assertEquals("R", shapes.get(3).getValue().getName());
    assertEquals(4, shapes.size());
    assertEquals("o", sortedShapes.get(0).getValue().getName());
    assertEquals("R", sortedShapes.get(1).getValue().getName());
    assertEquals("r", sortedShapes.get(2).getValue().getName());
    assertEquals("e", sortedShapes.get(3).getValue().getName());
    assertEquals(4, sortedShapes.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteInvalidLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse")
        .declareShape("R", "rectangle").build();
    animation.addShape("B", "rectangle");
    animation.deleteLayer(-1);
  }

  @Test
  public void testGetLayers() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("o", "oval", 0)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareShape("e", "ellipse", 1)
        .declareShape("R", "rectangle", 0).build();
    animation.addLayer(2);
    animation.addShape("B", "rectangle", 2);
    assertEquals(3, animation.getLayers().size());
    animation.deleteLayer(0);
    assertEquals(2, animation.getLayers().size());
  }

  @Test
  public void testGetLayersNone() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .build();
    assertEquals(1, animation.getLayers().size());
    animation.deleteLayer(0);
    assertEquals(0, animation.getLayers().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNonexistentLayer() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)
        .build();
    assertEquals(1, animation.getLayers().size());
    animation.deleteLayer(1);
  }

  @Test
  public void testTwoShapesThreeLayers() {
    Animator animation = new AnimatorImpl.Builder().setBounds(0, 0, 500, 500)

        .declareLayer(1)
        .declareShape("r", "rectangle", 1)
        .addMotion("r", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0)
        .declareLayer(2)
        .declareShape("o", "oval", 2)
        .addMotion("o", 1, 100, 100, 5, 5, 255, 0, 0,
            10, 200, 200, 5, 5, 255, 0, 0).build();
    assertEquals(0, animation.getLayers().get(0).size());
    assertEquals(1, animation.getLayers().get(1).size());
    assertEquals(1, animation.getLayers().get(2).size());
    animation.changeLayer(2, 0);
    assertEquals(1, animation.getLayers().get(0).size());
    assertEquals(0, animation.getLayers().get(1).size());
    assertEquals(1, animation.getLayers().get(2).size());
  }
}

