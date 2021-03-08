import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.ImmutableAnimator;
import cs3500.animator.model.Position2D;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.AnimatorSVGView;

import static org.junit.Assert.assertEquals;

public class AnimatorSVGViewTest {

  @Test
  public void testToSVGFormatSpeed1() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"3000ms\" "
        + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
        + "attributeName=\"cx\" from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" "
        + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatMotionDur0() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            1, 200, 200, 50, 100, 128, 0, 128)
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            50, 300, 200, 50, 100, 128, 0, 128);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\""
        + " fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"49000ms\" "
        + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatSpeed2Motions() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .addMotion("P",
            4, 300, 200, 50, 100, 128, 0, 128,
            6, 300, 300, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)"
        + "\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\""
        + "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"3000ms\" attributeName=\"x\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" attributeName=\"y\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" fill=\""
        + "rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"1ms\" attributeName=\""
        + "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" attributeName=\"cx\" "
        + "from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" attributeName=\"cy\" "
        + "from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatSpeed2MotionEach() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .addMotion("P",
            4, 300, 200, 50, 100, 128, 0, 128,
            6, 300, 300, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0)
        .addMotion("E",
            5, 600, 400, 120, 60, 255, 128, 0,
            8, 600, 400, 120, 60, 60, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)"
        + "\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\""
        + "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"3000ms\" attributeName=\"x\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"4000ms\" dur=\"2000ms\" attributeName=\"y\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" fill=\""
        + "rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"1ms\" attributeName=\""
        + "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" attributeName=\""
        + "cx\" from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" attributeName=\""
        + "cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"3000ms\" attributeName=\""
        + "fill\" from=\"rgb(255,128,0)\" to=\"rgb(60,128,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testEmptyAnimation() {
    AnimationBuilder<Animator> animator1 =
        new AnimatorImpl.Builder().setBounds(0, 0, 500, 500);
    ImmutableAnimator animation = animator1.build();
    AnimatorSVGView view = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    view.animationOutput(a);
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatSpeed2() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 2);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
        "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)\""
        + " visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"1ms\" attributeName=\"visibility"
        + "\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"1500ms\" attributeName=\"x\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        +
        "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" fill=\""
        + "rgb(255,128,0)\" visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\""
        + "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1500ms\" attributeName=\""
        + "cx\" from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1500ms\" attributeName=\""
        + "cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        +
        "</ellipse>\n" +
        "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatSpeed20() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 20);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
        "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)"
        + "\" visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"50ms\" dur=\"1ms\" attributeName=\"visibility\""
        + " from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"50ms\" dur=\"150ms\" attributeName=\"x\" from="
        + "\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        +
        "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" fill=\"rgb"
        + "(255,128,0)\" visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"100ms\" dur=\"1ms\" attributeName=\"visibility"
        + "\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"100ms\" dur=\"150ms\" attributeName=\"cx\" "
        + "from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"100ms\" dur=\"150ms\" attributeName=\"cy\" "
        + "from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        +
        "</ellipse>\n" +
        "</svg>\n", a.toString());
  }

  @Test
  public void testToSVGFormatSpeed1000() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 200, 200, 50, 100, 128, 0, 128,
            4, 300, 200, 50, 100, 128, 0, 128)
        .declareShape("E", "oval")
        .addMotion("E",
            2, 500, 100, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1000);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
        "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)\""
        + " visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"1ms\" dur=\"1ms\" attributeName=\"visibility\" "
        + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"1ms\" dur=\"3ms\" attributeName=\"x\" from=\""
        + "200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        +
        "</rect><ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60\" ry=\"30\" fill=\"rgb"
        + "(255,128,0)\" visibility=\"hidden\" >\n"
        +
        "    <animate attributeType=\"xml\" begin=\"2ms\" dur=\"1ms\" attributeName=\"visibility"
        + "\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"2ms\" dur=\"3ms\" attributeName=\"cx\" from="
        + "\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        +
        "    <animate attributeType=\"xml\" begin=\"2ms\" dur=\"3ms\" attributeName=\"cy\" from="
        + "\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        +
        "</ellipse>\n" +
        "</svg>\n", a.toString());
  }

  @Test
  public void testEmptyAnimationSVG() {
    //need to set bounds or else program will throw exception trying to build empty animation with
    // nothing
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1000);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithRotationOnRectangleAndOval() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P", 1, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(360).build())
        .declareShape("E", "oval")
        .addMotion("E", 1, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(360).build());
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"300.0\" y=\"300.0\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 325 350\" to=\"360 325 350\" "
        + "fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"600.0\" cy=\"400.0\" rx=\"60\" ry=\"30\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 600 400\" to=\"360 600 400\" "
        + "fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithNegativeRotation() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P", 1, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(-360).build())
        .declareShape("E", "oval")
        .addMotion("E", 1, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(-360).build());
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"300.0\" y=\"300.0\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 325 350\" to=\"-360 325 350\" "
        + "fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"600.0\" cy=\"400.0\" rx=\"60\" ry=\"30\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 600 400\" to=\"-360 600 400\" "
        + "fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithOverloadRotation() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P", 1, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(460).build())
        .declareShape("E", "oval")
        .addMotion("E", 1, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(460).build());
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"300.0\" y=\"300.0\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 325 350\" to=\"460 325 350\" "
        + "fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"600.0\" cy=\"400.0\" rx=\"60\" ry=\"30\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 600 400\" to=\"460 600 400\" "
        + "fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithNegativeOverloadRotation() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P", 1, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(-460).build())
        .declareShape("E", "oval")
        .addMotion("E", 1, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0))
                .setAngle(-460).build());
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"300.0\" y=\"300.0\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(128,0,128)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 325 350\" to=\"-460 325 350\" "
        + "fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"600.0\" cy=\"400.0\" rx=\"60\" ry=\"30\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"1000ms\" "
        + "dur=\"9000ms\" attributeName=\"transform\" from=\"0 600 400\" to=\"-460 600 400\" "
        + "fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithRotationMotionAfterRegularMotion() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 600, 300, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0)
        .addMotion("P", 5, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(460).build())
        .declareShape("E", "oval")
        .addMotion("E",
            1, 600, 300, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0)
        .addMotion("E", 5, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(460).build());
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"600.0\" y=\"300.0\" width=\"120\" height=\"60\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
        + "attributeName=\"y\" from=\"300.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"x\" from=\"600.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"y\" from=\"400.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"width\" from=\"120\" to=\"50\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"height\" from=\"60\" to=\"100\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"fill\" from=\"rgb(255,128,0)\" to=\"rgb(128,0,128)\" "
        + "fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" "
        + "begin=\"5000ms\" dur=\"5000ms\" attributeName=\"transform\" "
        + "from=\"0 660 430\" to=\"460 325 350\" fill=\"freeze\" />\n"
        + "</rect><ellipse id=\"E\" cx=\"600.0\" cy=\"300.0\" rx=\"60\" ry=\"30\" "
        + ""
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
        + "attributeName=\"cy\" from=\"300.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"cx\" from=\"600.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"cy\" from=\"400.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"rx\" from=\"60\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"ry\" from=\"30\" to=\"50\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"fill\" from=\"rgb(255,128,0)\" to=\"rgb(128,0,128)\" "
        + "fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"5000ms\" "
        + "dur=\"5000ms\" attributeName=\"transform\" from=\"0 600 400\" to=\"460 300 300\" "
        + "fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>\n", a.toString());
  }

  @Test
  public void testSVGWithRotationMotionBeforeRegularMotion() {
    AnimationBuilder<Animator> animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        700, 500)
        .declareShape("P", "rectangle")
        .addMotion("P",
            1, 600, 300, 120, 60, 255, 128, 0,
            5, 600, 400, 120, 60, 255, 128, 0)
        .addMotion("P", 5, new AttributeImpl.Builder(new Position2D(600, 400),
                120, 60, new Color(255, 128, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(460).build())
        .addMotion("P", 10, new AttributeImpl.Builder(new Position2D(300, 300),
                50, 100, new Color(128, 0, 128)).setAngle(460).build(),
            15, new AttributeImpl.Builder(new Position2D(600, 300),
                120, 60, new Color(255, 128, 0)).setAngle(0).build())
        .addMotion("P",
            15, 600, 300, 120, 60, 255, 128, 0,
            20, 600, 400, 120, 60, 255, 128, 0);
    Animator animation = animator1.build();
    AnimatorSVGView svg = new AnimatorSVGView(animation, 1);
    Appendable a = new StringBuilder();
    svg.animationOutput(a);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"P\" x=\"600.0\" y=\"300.0\" width=\"120\" height=\"60\" "
        + "fill=\"rgb(255,128,0)\" visibility=\"hidden\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
        + "attributeName=\"y\""
        + " from=\"300.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"x\""
        + " from=\"600.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"y\""
        + " from=\"400.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"width\" from=\"120\" to=\"50\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"height\" from=\"60\" to=\"100\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" "
        + "attributeName=\"fill\" from=\"rgb(255,128,0)\" to=\"rgb(128,0,128)\" "
        + "fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"5000ms\" "
        + "dur=\"5000ms\" attributeName=\"transform\" from=\"0 660 430\" to=\"460 325 350\" "
        + "fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" "
        + "attributeName=\"x\" from=\"300.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" "
        + "attributeName=\"width\" from=\"50\" to=\"120\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" "
        + "attributeName=\"height\" from=\"100\" to=\"60\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" "
        + "attributeName=\"fill\" from=\"rgb(128,0,128)\" to=\"rgb(255,128,0)\" "
        + "fill=\"freeze\" />\n"
        + "    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\"10000ms\" "
        + "dur=\"5000ms\" attributeName=\"transform\" from=\"460 325 350\" to=\"0 660 330\" "
        + "fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"15000ms\" dur=\"5000ms\" "
        + "attributeName=\"y\" from=\"300.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>\n", a.toString());
  }
}