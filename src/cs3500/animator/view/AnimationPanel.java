package cs3500.animator.view;

import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Panel that provides a container to place and change Shapes visually based on input.
 */
public final class AnimationPanel extends JPanel {

  private int originX;
  private int originY;
  private List<ImmutableShape> currentShapes;

  /**
   * Constructs a new animation panel that allows us to draw on.
   *
   * @param originX - x coordinate of the top left corner
   * @param originY - y coordinate of the top left corner
   */
  public AnimationPanel(int originX, int originY) {
    super();
    this.currentShapes = new ArrayList<>();
    this.originX = originX;
    this.originY = originY;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphic2D = (Graphics2D) g;
    AffineTransform transform;
    for (ImmutableShape shape : currentShapes) {
      int[] attributes = shape.getAttribute().getAttributes();
      graphic2D.setColor(new Color(attributes[4], attributes[5], attributes[6]));
      transform = new AffineTransform();
      transform.rotate(Math.toRadians(attributes[7]),
          (attributes[0] - originX) + ((attributes[2]) / 2),
          (attributes[1] - originY) + ((attributes[3]) / 2));
      if (shape.getType().equals(ShapeType.RECTANGLE)) {
        Rectangle2D rectangle2D =
            new Double(attributes[0] - originX, attributes[1] - originY, attributes[2],
                attributes[3]);

        Shape transformedRectangle = transform.createTransformedShape(rectangle2D);
        graphic2D.draw(transformedRectangle);
        graphic2D.fill(transformedRectangle);
      } else if (shape.getType().equals(ShapeType.OVAL)) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(attributes[0] - originX,
            attributes[1] - originY, attributes[2], attributes[3]);
        Shape transformedEllipse = transform.createTransformedShape(ellipse2D);
        graphic2D.draw(transformedEllipse);
        graphic2D.fill(transformedEllipse);
      }
    }
  }

  protected void setCurrentShapes(List<ImmutableShape> shapes) {
    this.currentShapes = shapes;
  }

  protected void updateAnimationPanel(int originX, int originY) {
    this.originX = originX;
    this.originY = originY;
  }
}