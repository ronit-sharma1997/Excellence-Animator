package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class SuperAnimatorMockExtraCreditEditorView extends AnimatorMockEditorView {

  public JSlider animationSlider;
  public int animationEndTime;
  public int animationStartTime;
  public boolean showKeyframe;
  public boolean addKeyframeCompleted;
  public boolean editKeyframeCompleted;
  public boolean setAnimationEndTime;
  public boolean setAnimationStartTime;
  public boolean setAnimationSliderValue;
  public boolean addLayerCompleted;
  public boolean removeLayerCompleted;
  public boolean reorderLayerCompleted;
  public JTextField angleEdit;
  public JTextField layerNumber;
  public JComboBox<Integer> layersComboBox;
  public JList<Integer> shapeLayers;
  public JButton addLayer;
  public JButton deleteLayer;
  public JButton reorderLayer;
  public Set<Integer> layersSet;
  public JTextField currentLayer;

  /**
   * Constructs the editor view to edit and display changes to a visual.
   *
   * @param width - the specified width of the window
   * @param height - the specified height of the window
   * @param originX - the x coordinate origin specified by the user
   * @param originY - the y coordinate origin specified by the user
   */
  public SuperAnimatorMockExtraCreditEditorView(int width, int height, int originX, int originY,
      int speed, int startTime, int endTime, Set<String> shapeMap, Set<Integer> layers,
      Map<String, Map<Integer, Attribute>> shapeKeyframes) {
    super(width, height, originX, originY, speed, shapeMap, shapeKeyframes);
    this.animationStartTime = startTime;
    this.animationEndTime = endTime;
    this.animationSlider = new JSlider(this.animationStartTime, this.animationEndTime,
        this.animationStartTime);
    this.animationSlider
        .setMajorTickSpacing((this.animationEndTime - this.animationStartTime) / 20);
    this.animationSlider.setPaintLabels(true);
    this.animationSlider.setPaintTicks(true);

    this.angleEdit = new JTextField(3);
    this.currentLayer = new JTextField(3);
    this.addKeyframeCompleted = false;
    this.editKeyframeCompleted = false;
    this.showKeyframe = false;
    this.setAnimationEndTime = false;
    this.setAnimationStartTime = false;
    this.setAnimationSliderValue = false;
    this.reorderLayerCompleted = false;
    this.removeLayerCompleted = false;
    this.addLayerCompleted = false;
    this.layersSet = layers;
    this.addLayer = new JButton("Add Layer");
    this.addLayer.setActionCommand("AddLayer");
    this.deleteLayer = new JButton("Delete Layer");
    this.deleteLayer.setActionCommand("DeleteLayer");
    this.reorderLayer = new JButton("Reorder Layers");
    this.reorderLayer.setActionCommand("ReorderLayer");

    this.layerNumber = new JTextField(3);

    this.layersComboBox = new JComboBox<>();

    this.layersComboBox.setModel(new DefaultComboBoxModel(this.layersSet.toArray()));
    DefaultListModel<Integer> dataForListOfLayers = new DefaultListModel<>();
    this.shapeLayers = new JList<>(dataForListOfLayers);
    for (int layer : layersSet) {
      dataForListOfLayers.addElement(layer);
    }
    shapeLayers.setModel(dataForListOfLayers);


  }

  @Override
  public void addScrubbingChangeSliderListener(ChangeListener listener) {
    this.animationSlider.addChangeListener(listener);
  }

  @Override
  public void setAnimationEndTime(int endTime) {
    this.animationEndTime = endTime;
    this.animationSlider.setMaximum(endTime);
    this.setAnimationEndTime = true;
  }

  @Override
  public void setAnimationStartTime(int startTime) {

    this.animationStartTime = startTime;
    this.animationSlider.setMinimum(startTime);
    this.setAnimationStartTime = true;
  }

  @Override
  public void setAnimationSliderValue(int currentTick) {
    this.animationSlider.setValue(currentTick);
    this.setAnimationSliderValue = true;
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {
    super.showKeyframeAttributes(keyframe);
    Map<Integer, Attribute> shapeKeyframe = shapeKeyframes.get(listOfShapes.getSelectedValue());
    Attribute textBoxInfo = shapeKeyframe.get(keyframe);
    this.angleEdit.setText(String.valueOf(textBoxInfo.getAngle()));
    this.showKeyframe = true;
  }

  @Override
  public DataSubmission addKeyframetoAnimation() {
    DataSubmission newKeyframeSubmission = null;
    try {
      newKeyframeSubmission = new KeyframeRotationDataSubmission(Integer
          .parseInt(addTime.getText()), new Position2D(Double.parseDouble(xPositionAdd.getText()),
          Double.parseDouble(yPositionAdd.getText())), Integer.parseInt(widthAdd.getText()),
          Integer.parseInt(heightAdd.getText()), new Color(Integer.parseInt(colorRAdd.getText()),
          Integer.parseInt(colorGAdd.getText()), Integer.parseInt(colorBAdd.getText())),
          Integer.parseInt(this.angleEdit.getText()), shapeNamesComboBox.getSelectedItem()
          .toString());
      this.addKeyframeCompleted = true;
    } catch (NumberFormatException e) {
      showErrorMessage("Non Number Inputs!");
    }
    return newKeyframeSubmission;
  }


  @Override
  public DataSubmission editKeyframe() {
    DataSubmission editShapeKeyframeSubmission = null;
    if (listOfShapes.getSelectedValue() != null
        && shapeListOfKeyframes.getSelectedValue() != null) {
      try {
        editShapeKeyframeSubmission = new KeyframeRotationDataSubmission(shapeListOfKeyframes.
            getSelectedValue(), new Position2D(Double.parseDouble(xPositionEdit.getText()),
            Double.parseDouble(yPositionEdit.getText())), Integer.parseInt(widthEdit.getText()),
            Integer.parseInt(heightEdit.getText()), new Color(Integer.
            parseInt(colorREdit.getText()), Integer.parseInt(colorGEdit.getText()),
            Integer.parseInt(colorBEdit.getText())), Integer.parseInt(angleEdit.getText()),
            listOfShapes.getSelectedValue());
        this.editKeyframeCompleted = true;
      } catch (NumberFormatException e) {
        showErrorMessage("Non Number Inputs!");
      }
    }
    return editShapeKeyframeSubmission;
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    DataSubmission newShape;
    addLayerCompleted = true;

    DataSubmission newLayerSubmission;
    newLayerSubmission = new LayerDataSubmission(Integer.parseInt(layerNumber.getText()));

    return newLayerSubmission;
  }

  @Override
  public DataSubmission editLayerPosition() {
    DataSubmission newLayerSubmission = null;
    newLayerSubmission = new ShapeLayerDataSubmission("", ShapeType.OVAL,
        (int) layersComboBox.getSelectedItem(), shapeLayers.getSelectedValue());
    return newLayerSubmission;
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    removeLayerCompleted = true;
    return new LayerDataSubmission(shapeLayers.getSelectedValue());
  }

  @Override
  public void addActionListener(ActionListener listener) {
    super.addActionListener(listener);
    this.addLayer.addActionListener(listener);
    this.deleteLayer.addActionListener(listener);
    this.reorderLayer.addActionListener(listener);
  }

}
