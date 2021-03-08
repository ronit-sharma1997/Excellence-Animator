package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * Extra Credit Editor View of the Animation. Allows us to visually display the shapes at different
 * states in the animation and edit, add, delete keyframes along with shapes in real time.
 * Allows the ability to export as well. Provides the ability to see rotation transformations
 * and add/modify layers of animations.
 */
public class SuperAnimatorExtraCreditEditorView extends AnimatorEditorView implements
    AnimatorView {

  private JSlider animationSlider;
  private int animationEndTime;
  private int animationStartTime;
  private JPanel layerPanel;
  private JPanel reorderPanel;
  private JTextField angleEdit;
  private JTextField angleAdd;
  private JTextField layerNumber;
  private JComboBox<Integer> addShapeLayerComboBox;
  private JButton addLayer;
  private JButton deleteLayer;
  private JButton reorderLayer;
  private JList<Integer> shapeLayers;
  private Set<Integer> layersSet;
  private JTextField currentLayer;
  private JComboBox<Integer> layersComboBox;

  /**
   * Constructs the editor view to edit and display changes to a visual.
   *
   * @param width - the specified width of the window
   * @param height - the specified height of the window
   * @param originX - the x coordinate origin specified by the user
   * @param originY - the y coordinate origin specified by the user
   */
  public SuperAnimatorExtraCreditEditorView(int width, int height, int originX, int originY,
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
    this.layersSet = layers;

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 8;
    c.gridheight = 1;
    c.weightx = 1;
    c.weighty = 1;
    JPanel scrubPanel = new JPanel();
    scrubPanel.setLayout(new GridBagLayout());
    scrubPanel.setBorder(BorderFactory.createTitledBorder("Animation Scrubbing!"));
    scrubPanel.add(this.animationSlider, c);

    c.gridy = 6;

    c.insets = new Insets(15, 0, 0, 0);
    super.addComponentToGridBagLayoutPanel(super.animationButtonPanel, scrubPanel, c);

    c.gridy++;

    addComponentToGridBagLayoutPanel(super.animationButtonPanel, new JLabel(" "), c);

    updateLayerList(layers);

    setupAddLayerPanelDialogBox();

    setupChangeOrderPanelDialogBox();

  }

  @Override
  public void animationOutput(Appendable output) {
    try {
      output.append("Playing Visual Animation in Animation Super Editor!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addScrubbingChangeSliderListener(ChangeListener listener) {
    this.animationSlider.addChangeListener(listener);
  }

  @Override
  public void addLayerListListener(ListSelectionListener listener) {
    this.shapeLayers.addListSelectionListener(listener);
  }

  @Override
  public void setAnimationEndTime(int endTime) {
    this.animationEndTime = endTime;
    this.animationSlider.setMaximum(endTime);
  }

  @Override
  public void setAnimationStartTime(int startTime) {

    this.animationStartTime = startTime;
    this.animationSlider.setMinimum(startTime);
  }

  @Override
  public void setAnimationSliderValue(int currentTick) {
    this.animationSlider.setValue(currentTick);
  }

  @Override
  protected void setupAttributesPanel() {
    this.createAttributesPanel();
    this.setupXYWHRGB();
    this.attributesPanel.add(new JLabel("Angle:"));
    this.angleEdit = new JTextField(3);
    this.attributesPanel.add(this.angleEdit);
    this.addEditKeyframeButton();
  }

  @Override
  protected void setupAddKeyframePanel() {
    this.createKeyframePanel();
    this.setupTXYWHRGB();
    this.addKeyframePanel.add(new JLabel("Angle:"));
    this.angleAdd = new JTextField(3);
    this.addKeyframePanel.add(this.angleAdd);
    this.addKeyframeButton();
  }

  @Override
  protected void setupSelectionListPanel() {
    this.createSelectionListPanel();
    DefaultListModel<Integer> dataForListOfLayers = new DefaultListModel<>();
    this.shapeLayers = new JList<>(dataForListOfLayers);
    this.shapeLayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.selectionListPanel.add(new JScrollPane(this.shapeLayers));
    this.setupShapeKeyframeList();
  }

  @Override
  protected void createInitialShapeKeyframeList() {
    DefaultListModel<String> dataForListOfShapeNames = new DefaultListModel<>();
    this.listOfShapes = new JList<>(dataForListOfShapeNames);
  }

  @Override
  protected void setupShapeButtons() {
    super.setupShapeButtons();
    this.addLayer = new JButton("Add Layer");
    this.addLayer.setActionCommand("AddLayer");
    this.addLayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.addLayer);
    this.deleteLayer = new JButton("Delete Layer");
    this.deleteLayer.setActionCommand("DeleteLayer");
    this.deleteLayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.deleteLayer);
    this.reorderLayer = new JButton("Reorder Layers");
    this.reorderLayer.setActionCommand("ReorderLayer");
    this.reorderLayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.reorderLayer);
  }

  @Override
  protected void setupAddShapePanelDialogBox() {
    super.setupAddShapePanelDialogBox();
    this.addShapePanel.add(new JLabel("Layer #"));
    this.addShapeLayerComboBox = new JComboBox();
    this.addShapePanel.add(addShapeLayerComboBox);
  }

  private void setupChangeOrderPanelDialogBox() {
    this.reorderPanel = new JPanel();
    this.reorderPanel.setLayout(new BoxLayout(reorderPanel, BoxLayout.Y_AXIS));
    this.reorderPanel.add(new JLabel("Current Layer #:"));
    this.currentLayer = new JTextField(5);
    this.currentLayer.setEnabled(false);
    this.reorderPanel.add(this.currentLayer);
    this.reorderPanel.add(new JLabel("Reorder Layer to #:"));
    this.layersComboBox = new JComboBox<>();
    this.reorderPanel.add(this.layersComboBox);
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {
    super.showKeyframeAttributes(keyframe);
    Map<Integer, Attribute> shapeKeyframe = shapeKeyframes.get(listOfShapes.getSelectedValue());
    Attribute textBoxInfo = shapeKeyframe.get(keyframe);
    this.angleEdit.setText(String.valueOf(textBoxInfo.getAngle()));
  }

  @Override
  public void updateLayerList(Set<Integer> updatedLayers) {
    this.layersSet = updatedLayers;
    setLayerList();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    super.addActionListener(listener);
    this.addLayer.addActionListener(listener);
    this.deleteLayer.addActionListener(listener);
    this.reorderLayer.addActionListener(listener);
  }

  @Override
  protected void setShapeList() {
    if (shapeLayers.getSelectedValue() != null) {
      super.setShapeList();
      DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
      listModel.removeAllElements();
    }
  }

  private void setLayerList() {
    DefaultListModel<Integer> dataForListOfLayers = new DefaultListModel<>();
    for (int layer : layersSet) {
      dataForListOfLayers.addElement(layer);
    }
    shapeLayers.setModel(dataForListOfLayers);
    addShapeLayerComboBox.setModel(new DefaultComboBoxModel(this.layersSet.toArray()));
  }

  @Override
  public DataSubmission removeShapeFromAnimation() {
    if (listOfShapes.getSelectedValue() != null) {
      DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
      listModel.removeAllElements();
      return new ShapeLayerDataSubmission(listOfShapes.getSelectedValue(), ShapeType.OVAL, 0,
          shapeLayers.getSelectedValue());
    }
    return null;
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
          Integer.parseInt(this.angleAdd.getText()), shapeNamesComboBox.getSelectedItem()
          .toString());
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
      } catch (NumberFormatException e) {
        showErrorMessage("Non Number Inputs!");
      }
    }
    return editShapeKeyframeSubmission;
  }

  @Override
  public DataSubmission editLayerPosition() {
    DataSubmission newLayerSubmission = null;
    if (shapeLayers.getSelectedValue() != null) {
      try {
        this.currentLayer.setText(String.valueOf(shapeLayers.getSelectedValue()));
        this.layersComboBox.setModel(new DefaultComboBoxModel(this.layersSet.toArray()));
        int result = JOptionPane.showConfirmDialog(null, reorderPanel,
            "Reorder Layers?", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          newLayerSubmission = new ShapeLayerDataSubmission("", ShapeType.OVAL,
              (int) layersComboBox.getSelectedItem(), shapeLayers.getSelectedValue());
        }
      } catch (NumberFormatException e) {
        this.showErrorMessage("Must enter integer value!");
      }
    }
    return newLayerSubmission;
  }

  private void setupAddLayerPanelDialogBox() {
    this.layerPanel = new JPanel();
    this.layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.Y_AXIS));
    this.layerPanel.add(new JLabel("Layer #:"));
    this.layerNumber = new JTextField(5);
    this.layerPanel.add(layerNumber);
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    DataSubmission newLayerSubmission = null;
    try {
      int result = JOptionPane.showConfirmDialog(null, layerPanel,
          "New Layer?", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        DefaultListModel listModelShapes = (DefaultListModel) listOfShapes.getModel();
        listModelShapes.removeAllElements();
        DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
        listModel.removeAllElements();
        newLayerSubmission = new LayerDataSubmission(Integer.parseInt(layerNumber.getText()));
      }
    } catch (NumberFormatException e) {
      this.showErrorMessage("Must enter integer value!");
    }
    return newLayerSubmission;
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    if (shapeLayers.getSelectedValue() != null) {
      DefaultListModel listModelShapes = (DefaultListModel) listOfShapes.getModel();
      listModelShapes.removeAllElements();
      DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
      listModel.removeAllElements();
      return new LayerDataSubmission(shapeLayers.getSelectedValue());
    }
    return null;
  }

  @Override
  public DataSubmission addShapeToAnimation() {
    DataSubmission newShapeLayerSubmission = null;
    int result = JOptionPane.showConfirmDialog(null, addShapePanel,
        "Shape Name, Type, and Layer?", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      newShapeLayerSubmission = new ShapeLayerDataSubmission(shapeName.getText(),
          (ShapeType) shapeTypeComboBox.getSelectedItem(),
          (int) addShapeLayerComboBox.getSelectedItem(), shapeLayers.getSelectedValue());
    }
    return newShapeLayerSubmission;
  }

}
