package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * Mock Editor View of the Animation. Allows us to create a skeleton of {@link AnimatorEditorView}
 * to test the functionality of a GUI and input dependent view.
 */
public class AnimatorMockEditorView extends JFrame implements AnimatorView {

  public JLabel speedLabel;
  public JSlider speedSlider;
  public int speed;
  public JButton playPauseButton;
  public JButton rewindButton;
  public JButton loopButton;
  public JButton noLoopButton;
  public JButton editKeyframe;
  public JButton addShape;
  public JButton deleteShape;
  public JButton addKeyframe;
  public JButton deleteKeyframe;
  public JButton saveAnimationButton;
  public JButton loadAnimationButton;
  public JTextField xPositionEdit;
  public JTextField xPositionAdd;
  public JTextField yPositionEdit;
  public JTextField yPositionAdd;
  public JTextField widthEdit;
  public JTextField widthAdd;
  public JTextField heightEdit;
  public JTextField heightAdd;
  public JTextField colorREdit;
  public JTextField colorRAdd;
  public JTextField colorGEdit;
  public JTextField colorGAdd;
  public JTextField colorBEdit;
  public JTextField colorBAdd;
  public JTextField addTime;
  public JTextField shapeName;
  public JComboBox<String> shapeNamesComboBox;
  public JComboBox<ShapeType> shapeTypeComboBox;
  public JList<String> listOfShapes;
  public JList<Integer> shapeListOfKeyframes;
  public Set<String> shapeNames;
  public Map<String, Map<Integer, Attribute>> shapeKeyframes;
  public boolean showError;
  public boolean makeVisible;
  public boolean refresh;
  public boolean showKeyframesOfList;
  public boolean showKeyframeProperties;
  public boolean addShapeToAnimation;
  public boolean addKeyframeToShape;
  public boolean updateShapeList;
  public boolean updateKeyframelist;
  public boolean saveAnimation;
  public boolean loadAnimation;
  public boolean removeShapeFromAnimation;
  public boolean removeKeyframe;
  public boolean modifyKeyframe;
  public boolean changeSpeed;
  public boolean updateAnimation;

  /**
   * Constructs a mock view to test the functionality of {@code AnimatorMockEditorView}.
   *
   * @param width - the specified width of the window
   * @param height - the specified height of the window
   * @param originX - the x coordinate origin specified by the user
   * @param originY - the y coordinate origin specified by the user
   */
  public AnimatorMockEditorView(int width, int height, int originX, int originY, int speed,
      Set<String> shapeMap, Map<String, Map<Integer, Attribute>>
      shapeKeyframes) {
    super();
    this.speed = speed;
    this.shapeNames = shapeMap;
    this.shapeKeyframes = shapeKeyframes;
    this.shapeTypeComboBox = new JComboBox(ShapeType.values());
    this.shapeNamesComboBox = new JComboBox(this.shapeNames.toArray());

    this.showError = false;
    this.makeVisible = false;
    this.refresh = false;
    this.showKeyframesOfList = false;
    this.showKeyframeProperties = false;
    this.addShapeToAnimation = false;
    this.addKeyframeToShape = false;
    this.updateKeyframelist = false;
    this.updateShapeList = false;
    this.removeShapeFromAnimation = false;
    this.removeKeyframe = false;
    this.modifyKeyframe = false;
    this.changeSpeed = false;
    this.saveAnimation = false;
    this.loadAnimation = false;
    this.updateAnimation = false;

    this.playPauseButton = new JButton("Play/Pause");
    this.playPauseButton.setActionCommand("PlayPause");

    this.rewindButton = new JButton("Rewind");
    this.rewindButton.setActionCommand("Rewind");

    this.loopButton = new JButton("Enable Looping");
    this.loopButton.setActionCommand("Loop");

    this.noLoopButton = new JButton("Disable Looping");
    this.noLoopButton.setActionCommand("NoLoop");

    this.speedLabel = new JLabel(String.valueOf(this.speed));

    this.speedSlider = new JSlider(1, 1000, speed);
    this.speedSlider.setMajorTickSpacing(50);
    this.speedSlider.setPaintTicks(true);

    this.shapeName = new JTextField(5);

    DefaultListModel<String> dataForListOfShapeNames = new DefaultListModel<>();
    for (String name : this.shapeNames) {
      dataForListOfShapeNames.addElement(name);
    }
    this.listOfShapes = new JList<>(dataForListOfShapeNames);

    DefaultListModel<Integer> dataForShapeListOfKeyframes = new DefaultListModel<>();
    this.shapeListOfKeyframes = new JList<>(dataForShapeListOfKeyframes);

    this.addShape = new JButton("Add Shape");
    this.addShape.setActionCommand("AddShape");

    this.deleteShape = new JButton("Delete Shape");
    this.deleteShape.setActionCommand("DeleteShape");

    this.deleteKeyframe = new JButton("Delete Keyframe");
    this.deleteKeyframe.setActionCommand("DeleteKeyframe");

    this.xPositionEdit = new JTextField(3);
    this.yPositionEdit = new JTextField(3);
    this.widthEdit = new JTextField(3);
    this.heightEdit = new JTextField(3);
    this.colorREdit = new JTextField(3);
    this.colorGEdit = new JTextField(3);
    this.colorBEdit = new JTextField(3);
    this.editKeyframe = new JButton("Edit Keyframe");
    this.editKeyframe.setActionCommand("EditKeyframe");

    this.addTime = new JTextField(3);
    this.xPositionAdd = new JTextField(3);
    this.yPositionAdd = new JTextField(3);
    this.widthAdd = new JTextField(3);
    this.heightAdd = new JTextField(3);
    this.colorRAdd = new JTextField(3);
    this.colorGAdd = new JTextField(3);
    this.colorBAdd = new JTextField(3);
    this.addKeyframe = new JButton("Add Keyframe");
    this.addKeyframe.setActionCommand("AddKeyframe");
    this.saveAnimationButton = new JButton("Export Animation");
    this.saveAnimationButton.setActionCommand("SaveAnimation");
    this.loadAnimationButton = new JButton("Load Animation");
    this.loadAnimationButton.setActionCommand("LoadAnimation");
  }

  @Override
  public void showErrorMessage(String error) {
    showError = true;
  }


  @Override
  public void makeVisible() {

    makeVisible = true;
  }


  @Override
  public void refresh(List<ImmutableShape> shapes) {
    refresh = true;
  }

  @Override
  public void animationOutput(Appendable output) {
    try {
      output.append("Playing Visual Animation in Animation Editor!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addActionListener(ActionListener listener) {
    playPauseButton.addActionListener(listener);
    rewindButton.addActionListener(listener);
    loopButton.addActionListener(listener);
    noLoopButton.addActionListener(listener);
    addShape.addActionListener(listener);
    deleteShape.addActionListener(listener);
    deleteKeyframe.addActionListener(listener);
    addKeyframe.addActionListener(listener);
    editKeyframe.addActionListener(listener);
    saveAnimationButton.addActionListener(listener);
    loadAnimationButton.addActionListener(listener);
  }

  @Override
  public void addShapeListListener(ListSelectionListener listener) {
    listOfShapes.addListSelectionListener(listener);
  }

  @Override
  public void addSpeedChangeSliderListener(ChangeListener listener) {
    speedSlider.addChangeListener(listener);
  }

  @Override
  public void addKeyframeListListener(ListSelectionListener listener) {
    shapeListOfKeyframes.addListSelectionListener(listener);
  }

  @Override
  public void addLayerListListener(ListSelectionListener listener) {
    return;
  }

  @Override
  public void changeSpeed(int speed) {
    changeSpeed = true;
    this.speed = speed;
    speedLabel.setText(String.valueOf(speed));
  }

  @Override
  public void showShapeListKeyframe(String name) {

    showKeyframesOfList = true;
    DefaultListModel<Integer> newData = new DefaultListModel<>();
    Map<Integer, Attribute> shapeKeyframe = shapeKeyframes.get(name);
    for (Integer i : shapeKeyframe.keySet()) {
      newData.addElement(i);
    }
    shapeListOfKeyframes.setModel(newData);
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {

    showKeyframeProperties = true;
    Map<Integer, Attribute> shapeKeyframe = shapeKeyframes.get(listOfShapes.getSelectedValue());
    Attribute textBoxInfo = shapeKeyframe.get(keyframe);
    this.xPositionEdit.setText(String.valueOf(textBoxInfo.getX()));
    this.yPositionEdit.setText(String.valueOf(textBoxInfo.getY()));
    this.widthEdit.setText(String.valueOf(textBoxInfo.getW()));
    this.heightEdit.setText(String.valueOf(textBoxInfo.getH()));
    this.colorREdit.setText(String.valueOf(textBoxInfo.getR()));
    this.colorGEdit.setText(String.valueOf(textBoxInfo.getG()));
    this.colorBEdit.setText(String.valueOf(textBoxInfo.getB()));
  }

  @Override
  public DataSubmission addShapeToAnimation() {
    DataSubmission newShape;
    addShapeToAnimation = true;

    newShape = new ShapeDataSubmission(shapeName.getText(),
        (ShapeType)shapeTypeComboBox.getSelectedItem());
    return newShape;
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    return null;
  }

  @Override
  public DataSubmission addKeyframetoAnimation() {
    DataSubmission shapeToAddKeyframe = null;
    try {
      shapeToAddKeyframe = new KeyframeDataSubmission(Integer.parseInt(addTime.getText()),
          new Position2D(Double.parseDouble(xPositionAdd.getText()),
              Double.parseDouble(yPositionAdd.getText())), Integer.parseInt(widthAdd.getText()),
          Integer.parseInt(heightAdd.getText()), new Color(Integer.parseInt(colorRAdd.getText()),
          Integer.parseInt(colorGAdd.getText()), Integer.parseInt(colorBAdd.getText())),
          shapeNamesComboBox.getSelectedItem().toString());
    } catch (NumberFormatException e) {
      showErrorMessage("Non Number Inputs!");
    }
    addKeyframeToShape = true;
    return shapeToAddKeyframe;
  }

  @Override
  public void updateShapeList(Set<String> updatedShapes) {
    this.shapeNames = updatedShapes;
    setShapeList();

  }

  @Override
  public void updateLayerList(Set<Integer> updatedLayers) {
    return;
  }

  @Override
  public void updateKeyframeList(Map<String, Map<Integer, Attribute>> shapeKeyframes) {
    this.shapeKeyframes = shapeKeyframes;
    setKeyframeList();
  }

  @Override
  public DataSubmission removeShapeFromAnimation() {
    removeShapeFromAnimation = true;
    return new ShapeDataSubmission(listOfShapes.getSelectedValue(), ShapeType.OVAL);
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    return null;
  }

  @Override
  public DataSubmission editKeyframe() {
    DataSubmission shapeToEditKeyframe = null;
    modifyKeyframe = true;
    if (listOfShapes.getSelectedValue() != null
        && shapeListOfKeyframes.getSelectedValue() != null) {
      try {
        shapeToEditKeyframe = new KeyframeDataSubmission(shapeListOfKeyframes.
            getSelectedValue(), new Position2D(Double.parseDouble(xPositionEdit.getText()),
            Double.parseDouble(yPositionEdit.getText())), Integer.parseInt(widthEdit.getText()),
            Integer.parseInt(heightEdit.getText()), new Color(Integer.
            parseInt(colorREdit.getText()), Integer.parseInt(colorGEdit.getText()),
            Integer.parseInt(colorBEdit.getText())), listOfShapes.getSelectedValue());
      } catch (NumberFormatException e) {
        showErrorMessage("Non Number Inputs!");
      }
    }
    return shapeToEditKeyframe;
  }

  @Override
  public DataSubmission editLayerPosition() {
    return null;
  }

  @Override
  public File getSaveAnimationType() {
    saveAnimation = true;
    return null;
  }

  @Override
  public File loadAnimation() {
    loadAnimation = true;
    return null;
  }

  @Override
  public void updateAnimationPanel(int height, int width, int originX, int originY) {
    updateAnimation = true;
  }

  @Override
  public void addScrubbingChangeSliderListener(ChangeListener listener) {
    return;
  }

  @Override
  public void setAnimationEndTime(int endTime) {
    return;
  }

  @Override
  public void setAnimationStartTime(int startTime) {
    return;
  }

  @Override
  public void setAnimationSliderValue(int currentTick) {
    return;
  }

  @Override
  public DataSubmission removeKeyframe() {
    DataSubmission shapeToRemoveKeyframe = null;
    removeKeyframe = true;
    if (listOfShapes.getSelectedValue() != null
        && shapeListOfKeyframes.getSelectedValue() != null) {
      Attribute keyframe = shapeKeyframes.get(listOfShapes.getSelectedValue())
          .get(shapeListOfKeyframes.getSelectedValue());
      shapeToRemoveKeyframe = new KeyframeDataSubmission(shapeListOfKeyframes.getSelectedValue(),
          new Position2D(keyframe.getX(), keyframe.getY()), keyframe.getW(), keyframe.getH(),
          new Color(keyframe.getR(), keyframe.getG(), keyframe.getB()),
          listOfShapes.getSelectedValue());
    }
    return shapeToRemoveKeyframe;
  }

  private void setShapeList() {
    DefaultListModel<String> dataForListOfShapeNames = new DefaultListModel<>();
    for (String name : shapeNames) {
      dataForListOfShapeNames.addElement(name);
    }
    listOfShapes.setModel(dataForListOfShapeNames);
    shapeNamesComboBox.setModel(new DefaultComboBoxModel(this.shapeNames.toArray()));
    updateShapeList = true;
  }

  private void setKeyframeList() {
    if (listOfShapes.getSelectedValue() != null) {
      DefaultListModel<Integer> dataForListOfKeyframe = new DefaultListModel<>();
      for (int keyframe : shapeKeyframes.get(listOfShapes.getSelectedValue()).keySet()) {
        dataForListOfKeyframe.addElement(keyframe);
      }
      shapeListOfKeyframes.setModel(dataForListOfKeyframe);
    } else {
      DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
      listModel.removeAllElements();
    }
    updateKeyframelist = true;
  }
}

