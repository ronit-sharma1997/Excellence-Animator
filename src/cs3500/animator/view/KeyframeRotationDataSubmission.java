package cs3500.animator.view;

import cs3500.animator.model.Position2D;
import java.awt.Color;

public class KeyframeRotationDataSubmission extends KeyframeDataSubmission {

  private int angle;

  /**
   * Constructs a KeyframeRotationDataSubmission object that contains the time, 2D position, width,
   * height, color, angle, and shape's name of the specific keyframe.
   *
   * @param time - keyframe time
   * @param position2D - keyframe position
   * @param width - keyframe width
   * @param height - keyframe height
   * @param color - keyframe color
   * @param angle - keyframe angle
   * @param shapeName - keyframe Shape Name
   */
  public KeyframeRotationDataSubmission(int time, Position2D position2D,
      int width,
      int height, Color color, int angle, String shapeName) {
    super(time, position2D, width, height, color, shapeName);
    this.angle = angle;
  }

  @Override
  public int getAngle() {
    return this.angle;
  }
}
