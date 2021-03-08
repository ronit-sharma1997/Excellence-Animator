package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ChangeType;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeType;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs3500.animator.model.ImmutableAnimator;
import cs3500.animator.model.ImmutableShape;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * A view which renders the data from a given model in the form of text in SVG format.
 */
public class AnimatorSVGView extends JFrame implements AnimatorView {

  private ImmutableAnimator model;
  private int speed;
  private boolean visibilitySet;
  private String begin;
  private String dur;
  private String attributeName;
  private String from;
  private String to;
  private String attrX;
  private String attrY;
  private String attrW;
  private String attrH;

  /**
   * Represents an implementation of view where the animation is rendered from SVG formatted text.
   *
   * @param model - represents the data of the animation to be rendered in SVG format
   * @param speed - the speed that the animation will Play at
   */
  public AnimatorSVGView(ImmutableAnimator model, int speed) {
    this.model = model;
    this.speed = speed;
    this.visibilitySet = false;
  }

  /**
   * Initializes the fields of this view.
   *
   * @param motion    - represents a Change
   * @param shapeType - represents a ShapeType
   */
  private void initialize(Motion motion, ShapeType shapeType) {
    this.begin = Integer.toString((motion.getStartTime() * 1000) / this.speed);
    this.dur = Integer.toString(((motion.getEndTime() * 1000)
            - (motion.getStartTime() * 1000)) / this.speed);
    this.attrX = "";
    this.attrY = "";
    this.attrW = "";
    this.attrH = "";
    switch (shapeType) {
      case RECTANGLE:
        attrX = "x";
        attrY = "y";
        attrW = "width";
        attrH = "height";
        break;
      case OVAL:
        attrX = "cx";
        attrY = "cy";
        attrW = "rx";
        attrH = "ry";
        break;
      default:
        break;
    }
  }

  /**
   * Appends the canvas in SVG formatted text to the output.
   *
   * @return - the canvas in SVG formatted text to the output
   */
  private StringBuilder makeCanvas() {
    StringBuilder output = new StringBuilder();
    if (!(model.getBoundsX() == 0
            && model.getBoundsY() == 0
            && model.getBoundsWidth() == 0
            && model.getBoundsHeight() == 0)) {
      String width = Integer.toString(model.getBoundsWidth());
      String height = Integer.toString(model.getBoundsHeight());
      output.append("<svg width=\"" + width
              + "\" height=\"" + height
              + "\" version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\">\n");
    } else {
      throw new IllegalArgumentException("Bounds were not set");
    }
    return output;
  }

  /**
   * Appends given characteristics of a change to the output in SVG format.
   *
   * @param output        - an output to append SVG formatted StringBuilder to
   * @param begin         - the start time of the Change
   * @param from          - the value that is being changed prior to the Change
   * @param to            - the value that is being changed after to the Change
   * @param dur           - the duration in milliseconds of the Change
   * @param attributeName - the attribute name of the Change
   * @return - SVG formatted text of a Change
   */
  private static StringBuilder appendToOutput(
          StringBuilder output, String begin, String from, String to,
          String dur, String attributeName) {
    return output.append("    <animate attributeType=\"xml\" begin=\""
            + begin + "ms\" dur=\"" + dur + "ms\" "
            + "attributeName=\"" + attributeName + "\" from=\""
            + from + "\" to=\"" + to + "\" fill=\"freeze\" />\n");
  }

  /**
   * Appends given characteristics of a change to the output in SVG format.
   *
   * @param output        - an output to append SVG formatted StringBuilder to
   * @param begin         - the start time of the Change
   * @param from          - the value that is being changed prior to the Change
   * @param to            - the value that is being changed after to the Change
   * @param dur           - the duration in milliseconds of the Change
   * @param attributeName - the attribute name of the Change
   * @return - SVG formatted text of a Change
   */
  private static StringBuilder appendRotationToOutput(
          StringBuilder output, String begin, String from, String to,
          String dur, String attributeName) {
    return output.append("    <animateTransform attributeType=\"xml\" type=\"rotate\" begin=\""
            + begin + "ms\" dur=\"" + dur + "ms\" "
            + "attributeName=\"" + attributeName + "\" from=\""
            + from + "\" to=\"" + to + "\" fill=\"freeze\" />\n");
  }

  /**
   * Makes a change in SVG format and returns it as a StringBuilder.
   *
   * @param motion     - represents a change
   * @param changeType - represent a type of change
   * @param shapeType  - represents a type of shape the change is on
   * @return - a string builder representing a change in SVG format
   */
  private StringBuilder makeMotion(Motion motion, List<ChangeType> changeType,
                                   ShapeType shapeType) {
    StringBuilder output = new StringBuilder();
    int[] previousStateMotion = motion.getPreviousState();
    int[] newStateMotion = motion.getNewState();
    this.initialize(motion, shapeType);
    if (this.speed > 1000) {
      this.speed = 1000;
    }

    if (!this.visibilitySet) {
      from = "hidden";
      to = "visible";
      String d = Integer.toString(this.speed / this.speed);
      attributeName = "visibility";
      appendToOutput(output, begin, from, to, d, attributeName);
      this.visibilitySet = true;
    }
    for (ChangeType change : changeType) {
      if (change.equals(ChangeType.POSITION)) {
        double xFrom = (double) (previousStateMotion[0] - model.getBoundsX());
        double xTo = (double) (newStateMotion[0] - model.getBoundsX());
        double yFrom = (double) (previousStateMotion[1] - model.getBoundsY());
        double yTo = (double) (newStateMotion[1] - model.getBoundsY());
        if (xFrom != xTo) {
          from = Double.toString(xFrom);
          to = Double.toString(xTo);
          attributeName = attrX;
          appendToOutput(output, begin, from, to, dur, attributeName);
        }
        if (yFrom != yTo) {
          from = Double.toString(yFrom);
          to = Double.toString(yTo);
          attributeName = attrY;
          appendToOutput(output, begin, from, to, dur, attributeName);
        }
      } else if (change.equals(ChangeType.WIDTH)) {
        int wFrom = previousStateMotion[2];
        int wTo = newStateMotion[2];
        if (shapeType.equals(ShapeType.OVAL)) {
          wFrom = wFrom / 2;
          wTo = wTo / 2;
        }
        if (wFrom != wTo) {
          from = Integer.toString(wFrom);
          to = Integer.toString(wTo);
          attributeName = attrW;
          appendToOutput(output, begin, from, to, dur, attributeName);
        }
      } else if (change.equals(ChangeType.HEIGHT)) {
        int hFrom = previousStateMotion[3];
        int hTo = newStateMotion[3];
        if (shapeType.equals(ShapeType.OVAL)) {
          hFrom = hFrom / 2;
          hTo = hTo / 2;
        }
        if (hFrom != hTo) {
          from = Integer.toString(hFrom);
          to = Integer.toString(hTo);
          attributeName = attrH;
          appendToOutput(output, begin, from, to, dur, attributeName);
        }
      } else if (change.equals(ChangeType.COLOR)) {
        int rFrom = previousStateMotion[4];
        int rTo = newStateMotion[4];
        int gFrom = previousStateMotion[5];
        int gTo = newStateMotion[5];
        int bFrom = previousStateMotion[6];
        int bTo = newStateMotion[6];
        if (rFrom != rTo
                || gFrom != gTo
                || bFrom != bTo) {
          from = "rgb(" + rFrom + "," + gFrom + "," + bFrom + ")";
          to = "rgb(" + rTo + "," + gTo + "," + bTo + ")";
          attributeName = "fill";
          appendToOutput(output, begin, from, to, dur, attributeName);
        }
      } else if (change.equals(ChangeType.ROTATION)) {
        int wFrom = previousStateMotion[2];
        int wTo = newStateMotion[2];
        if (shapeType.equals(ShapeType.OVAL)) {
          wFrom = 0;
          wTo = 0;
        }
        int hFrom = previousStateMotion[3];
        int hTo = newStateMotion[3];
        if (shapeType.equals(ShapeType.OVAL)) {
          hFrom = 0;
          hTo = 0;
        }
        double xFrom = (double) (previousStateMotion[0] - model.getBoundsX());
        double xTo = (double) (newStateMotion[0] - model.getBoundsX());
        double yFrom = (double) (previousStateMotion[1] - model.getBoundsY());
        double yTo = (double) (newStateMotion[1] - model.getBoundsY());
        int aFrom = previousStateMotion[7];
        int aTo = newStateMotion[7];
        if (aFrom != aTo) {
          from = Integer.toString(aFrom) + " " + Integer.toString((int) xFrom + (wFrom / 2))
                  + " " + Integer.toString((int) yFrom + (hFrom / 2));
          to = Integer.toString(aTo) + " " + Integer.toString((int) xTo + (wTo / 2)) + " "
                  + Integer.toString((int) yTo + (hTo / 2));
          attributeName = "transform";
          appendRotationToOutput(output, begin, from, to, dur, attributeName);
        }
      }
    }
    return output;
  }

  /**
   * Makes a shape in SVG format and returns it as a StringBuilder.
   *
   * @param shapeName       - the name of the shape to be rendered in SVG
   * @param shapeType       - the type of shape to be rendered in SVG
   * @param shapeAttributes - the attributes of the shape to be rendered in SVG
   * @param changeList      - the changes made to the shape to be rendered in SVG
   * @return - a string builder representing a shape in SVG format
   */
  private StringBuilder makeShape(String shapeName, ShapeType shapeType,
                                  int[] shapeAttributes, List<Motion> changeList) {
    StringBuilder output = new StringBuilder();
    String type = "";
    String x = Double.toString((double) (shapeAttributes[0] - model.getBoundsX()));
    String y = Double.toString((double) (shapeAttributes[1] - model.getBoundsY()));
    String w = Integer.toString(shapeAttributes[2]);
    String h = Integer.toString(shapeAttributes[3]);
    String r = Integer.toString(shapeAttributes[4]);
    String g = Integer.toString(shapeAttributes[5]);
    String b = Integer.toString(shapeAttributes[6]);
    if (shapeType.equals(ShapeType.RECTANGLE)) {
      type = "rect";
      output.append("<" + type + " id=\"" + shapeName + "\" "
              + "x=\"" + x + "\" y=\"" + y + "\" "
              + "width=\"" + w + "\" height=\"" + h + "\" "
              + "fill=\"rgb(" + r + "," + g + "," + b + ")\" visibility=\"hidden\" >\n");
    }
    if (shapeType.equals(ShapeType.OVAL)) {
      type = "ellipse";
      String rx = Integer.toString(Integer.parseInt(w) / 2);
      String ry = Integer.toString(Integer.parseInt(h) / 2);
      output.append("<" + type + " id=\"" + shapeName + "\" "
              + "cx=\"" + x + "\" cy=\"" + y + "\" "
              + "rx=\"" + rx + "\" ry=\"" + ry + "\" "
              + "fill=\"rgb(" + r + "," + g + "," + b + ")\" visibility=\"hidden\" >\n");
    }
    for (Motion c : changeList) {
      output.append(makeMotion(c, c.getChangeType(), shapeType));
    }
    output.append("</" + type + ">");
    this.visibilitySet = false;
    return output;
  }

  private String toSVGFormat() {
    StringBuilder output = new StringBuilder();
    output.append(this.makeCanvas());
    for (Map.Entry<String, ImmutableShape> e : model.getShapeMap().entrySet()) {
      String shapeName = e.getKey();
      ShapeType shapeType = e.getValue().getType();
      int[] shapeAttribute = e.getValue().getAttribute().getAttributes();
      List<Motion> changeList = e.getValue().getMotions();
      output.append(makeShape(shapeName, shapeType, shapeAttribute, changeList));
    }
    output.append("\n</svg>\n");
    return output.toString();
  }

  @Override
  public void animationOutput(Appendable output) {
    try {
      output.append(this.toSVGFormat());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addActionListener(ActionListener listener) {
    return;
  }

  @Override
  public void addShapeListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void addSpeedChangeSliderListener(ChangeListener listener) {
    return;
  }

  @Override
  public void addKeyframeListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void addLayerListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void changeSpeed(int speed) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void showShapeListKeyframe(String name) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addShapeToAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateShapeList(Set<String> updatedShapes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateLayerList(Set<Integer> updatedLayers) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateKeyframeList(Map<String, Map<Integer, Attribute>> shapeKeyframes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeShapeFromAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission removeKeyframe() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission addKeyframetoAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission editKeyframe() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public DataSubmission editLayerPosition() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public File getSaveAnimationType() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public File loadAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void updateAnimationPanel(int height, int width, int originX, int originY) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void addScrubbingChangeSliderListener(ChangeListener listener) {
    return;
  }

  @Override
  public void setAnimationEndTime(int endTime) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void setAnimationStartTime(int startTime) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void setAnimationSliderValue(int currentTick) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void makeVisible() {
    return;
  }

  @Override
  public void refresh(List<ImmutableShape> shapes) {
    throw new UnsupportedOperationException("Operation Not Supported");
  }
}
