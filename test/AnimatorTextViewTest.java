import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.ImmutableAnimator;
import cs3500.animator.model.Position2D;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.AnimatorTextView;
import cs3500.animator.view.AnimatorView;

import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

public class AnimatorTextViewTest {

  @Test
  public void testSmallDemo() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("R", "rectangle")
        .addMotion("R",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("R",
            10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R",
            50, 300, 300, 50, 100, 255, 0, 0,
            51, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R",
            51, 300, 300, 50, 100, 255, 0, 0,
            70, 300, 300, 25, 100, 255, 0, 0)
        .addMotion("R",
            70, 300, 300, 25, 100, 255, 0, 0,
            100, 200, 200, 25, 100, 255, 0, 0)
        .declareShape("C", "ellipse")
        .addMotion("C", 6, 440, 70, 120, 60, 0, 0, 255,
            20, 440, 70, 120, 60, 0, 0, 255)
        .addMotion("C", 20, 440, 70, 120, 60, 0, 0, 255,
            50, 440, 250, 120, 60, 0, 0, 255)
        .addMotion("C", 50, 440, 250, 120, 60, 0, 0, 255,
            70, 440, 370, 120, 60, 0, 170, 85)
        .addMotion("C", 70, 440, 370, 120, 60, 0, 170, 85,
            80, 440, 370, 120, 60, 0, 255, 0)
        .addMotion("C", 80, 440, 370, 120, 60, 0, 255, 0,
            100, 440, 370, 120, 60, 0, 255, 0);
    Animator animation = animator1.build();
    AnimatorView test = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    test.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape R rectangle 0\n"
            + "motion R 1 200 200 50 100 255 0 0 0      10 200 200 50 100 255 0 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 0      50 300 300 50 100 255 0 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 0      51 300 300 50 100 255 0 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 0      70 300 300 25 100 255 0 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 0      100 200 200 25 100 255 0 0 0\n"
            + "shape C oval 0\n"
            + "motion C 6 440 70 120 60 0 0 255 0      20 440 70 120 60 0 0 255 0\n"
            + "motion C 20 440 70 120 60 0 0 255 0      50 440 250 120 60 0 0 255 0\n"
            + "motion C 50 440 250 120 60 0 0 255 0      70 440 370 120 60 0 170 85 0\n"
            + "motion C 70 440 370 120 60 0 170 85 0      80 440 370 120 60 0 255 0 0\n"
            + "motion C 80 440 370 120 60 0 255 0 0      100 440 370 120 60 0 255 0 0\n",
        a.toString());
  }

  @Test
  public void testOneShape() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 50, 100, 255, 0, 0,
            50, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r",
            50, 200, 200, 50, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 200 200 50 100 255 0 0 0      10 300 300 50 100 255 0 0 0\n"
            + "motion r 10 300 300 50 100 255 0 0 0      50 200 200 50 100 255 0 0 0\n"
            + "motion r 50 200 200 50 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
        , a.toString());
  }

  @Test
  public void testOneShapeMotion0LengthInterval() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 50, 100, 255, 0, 0,
            50, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r",
            50, 200, 200, 50, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0)
        .addMotion("r",
            70, 200, 200, 25, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 200 200 50 100 255 0 0 0      10 300 300 50 100 255 0 0 0\n"
            + "motion r 10 300 300 50 100 255 0 0 0      50 200 200 50 100 255 0 0 0\n"
            + "motion r 50 200 200 50 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
            + "motion r 70 200 200 25 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
        , a.toString());
  }

  @Test
  public void testOneShapeMotion0LengthInterval2() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            0, 200, 200, 50, 100, 255, 0, 0,
            0, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("r",
            0, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r",
            10, 300, 300, 50, 100, 255, 0, 0,
            50, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("r",
            50, 200, 200, 50, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0)
        .addMotion("r",
            70, 200, 200, 25, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 0 200 200 50 100 255 0 0 0      0 200 200 50 100 255 0 0 0\n"
            + "motion r 0 200 200 50 100 255 0 0 0      10 300 300 50 100 255 0 0 0\n"
            + "motion r 10 300 300 50 100 255 0 0 0      50 200 200 50 100 255 0 0 0\n"
            + "motion r 50 200 200 50 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
            + "motion r 70 200 200 25 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
        , a.toString());
  }

  @Test
  public void testTwoShape() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            10, 300, 300, 50, 100, 255, 0, 0,
            50, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("r",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r",
            50, 200, 200, 50, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0)
        .declareShape("o", "oval")
        .addMotion("o",
            10, 300, 300, 50, 100, 255, 0, 0,
            50, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("o",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("o",
            50, 200, 200, 50, 100, 255, 0, 0,
            70, 200, 200, 25, 100, 255, 0, 0);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 200 200 50 100 255 0 0 0      10 300 300 50 100 255 0 0 0\n"
            + "motion r 10 300 300 50 100 255 0 0 0      50 200 200 50 100 255 0 0 0\n"
            + "motion r 50 200 200 50 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
            + "shape o oval 0\n"
            + "motion o 1 200 200 50 100 255 0 0 0      10 300 300 50 100 255 0 0 0\n"
            + "motion o 10 300 300 50 100 255 0 0 0      50 200 200 50 100 255 0 0 0\n"
            + "motion o 50 200 200 50 100 255 0 0 0      70 200 200 25 100 255 0 0 0\n"
        , a.toString());
  }


  @Test
  public void testEmptyAnimationBounds() {
    //need to set bounds or else program will throw exception trying to build empty animation with
    // nothing
    AnimationBuilder<Animator> animator1 =
        new AnimatorImpl.Builder().setBounds(0, 0, 500, 500);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n", a.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyAnimation() {
    //Bounds needs to be set for the canvas in order to create a view and display
    AnimationBuilder<Animator> animator1 =
        new AnimatorImpl.Builder();
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("", a.toString());
  }

  @Test
  public void testSimultaneousMovesOverlappingPeriod() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r",
            1, 0, 0, 5, 5, 255, 0, 0,
            2, 10, 10, 2, 10, 0, 0, 255);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 0 0 5 5 255 0 0 0      2 10 10 2 10 0 0 255 0\n"
        , a.toString());
  }

  @Test
  public void testSimultaneousMovesOverlappingPeriodWithRotation() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1,
            new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
                new Color(255, 0, 0)).setAngle(0).build(), 2,
            new AttributeImpl.Builder(new Position2D(10, 10), 2, 10,
                new Color(0, 0, 255)).setAngle(360).build());
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 0 0 5 5 255 0 0 0      2 10 10 2 10 0 0 255 360\n"
        , a.toString());
  }

  @Test
  public void testSimultaneousMovesOverlappingPeriodWithRotationMultipleMotions() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 1,
            new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
                new Color(255, 0, 0)).setAngle(0).build(), 2,
            new AttributeImpl.Builder(new Position2D(10, 10), 2, 10,
                new Color(0, 0, 255)).setAngle(360).build())
        .addMotion("r", 2, new AttributeImpl.Builder(new Position2D(10, 10),
                2, 10,
                new Color(0, 0, 255)).setAngle(360).build(), 3,
            new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
                new Color(255, 0, 0)).setAngle(0).build());
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "shape r rectangle 0\n"
            + "motion r 1 0 0 5 5 255 0 0 0      2 10 10 2 10 0 0 255 360\n"
            + "motion r 2 10 10 2 10 0 0 255 360      3 0 0 5 5 255 0 0 0\n"
        , a.toString());
  }

  @Test
  public void testTextViewMultipleLayers() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareLayer(1)
        .declareLayer(2)
        .declareShape("r", "rectangle", 0)
        .addMotion("r", 1,
            new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
                new Color(255, 0, 0)).setAngle(0).build(), 2,
            new AttributeImpl.Builder(new Position2D(10, 10), 2, 10,
                new Color(0, 0, 255)).setAngle(360).build())
        .addMotion("r", 2, new AttributeImpl.Builder(new Position2D(10, 10),
                2, 10,
                new Color(0, 0, 255)).setAngle(360).build(), 3,
            new AttributeImpl.Builder(new Position2D(0, 0), 5, 5,
                new Color(255, 0, 0)).setAngle(0).build())
        .declareShape("o", "oval", 1)
        .declareShape("e", "ellipse", 2);
    ImmutableAnimator animation = animator1.build();
    AnimatorView view = new AnimatorTextView(animation);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("canvas 0 0 500 500\n"
            + "layer 1\n"
            + "layer 2\n"
            + "shape r rectangle 0\n"
            + "motion r 1 0 0 5 5 255 0 0 0      2 10 10 2 10 0 0 255 360\n"
            + "motion r 2 10 10 2 10 0 0 255 360      3 0 0 5 5 255 0 0 0\n"
            + "shape o oval 1\n"
            + "shape e oval 2\n"
        , a.toString());
  }
}
