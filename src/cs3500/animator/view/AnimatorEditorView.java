package cs3500.animator.view;

import cs3500.animator.model.Attribute;
import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Editor View of the Animation. Allows us to visually display the shapes at different states in the
 * animation and edit, add, delete keyframes along with shapes in real time. Allows the ability to
 * export as well.
 */
public class AnimatorEditorView extends JFrame implements AnimatorView {

  private AnimationPanel animationPanel;
  protected JPanel animationButtonPanel;
  protected JPanel addShapePanel;
  protected JPanel attributesPanel;
  protected JPanel addKeyframePanel;
  protected JPanel selectionListPanel;
  protected JPanel shapeButtonPanel;
  private JPanel saveLoadPanel;
  private JLabel speedLabel;
  private JSlider speedSlider;
  private int speed;
  private JButton playPauseButton;
  private JButton rewindButton;
  private JButton loopButton;
  private JButton noLoopButton;
  protected JButton editKeyframe;
  private JButton addShape;
  private JButton deleteShape;
  private JButton addKeyframe;
  private JButton deleteKeyframe;
  private JButton saveAnimation;
  private JButton loadAnimation;
  protected JComboBox<ShapeType> shapeTypeComboBox;
  protected JComboBox<String> shapeNamesComboBox;
  private JComboBox<String> fileType;
  protected JList<String> listOfShapes;
  protected JList<Integer> shapeListOfKeyframes;
  private JScrollPane scrollPane;
  protected JTextField xPositionEdit;
  protected JTextField xPositionAdd;
  protected JTextField yPositionEdit;
  protected JTextField yPositionAdd;
  protected JTextField widthEdit;
  protected JTextField widthAdd;
  protected JTextField heightEdit;
  protected JTextField heightAdd;
  protected JTextField colorREdit;
  protected JTextField colorRAdd;
  protected JTextField colorGEdit;
  protected JTextField colorGAdd;
  protected JTextField colorBEdit;
  protected JTextField colorBAdd;
  protected JTextField addTime;
  protected JTextField shapeName;
  private Set<String> shapeNames;
  protected Map<String, Map<Integer, Attribute>> shapeKeyframes;
  private final JFileChooser fileChooser;

  /**
   * Constructs the editor view to edit and display changes to a visual.
   *
   * @param width - the specified width of the window
   * @param height - the specified height of the window
   * @param originX - the x coordinate origin specified by the user
   * @param originY - the y coordinate origin specified by the user
   */
  public AnimatorEditorView(int width, int height, int originX, int originY, int speed,
      Set<String> shapeMap,
      Map<String, Map<Integer, Attribute>> shapeKeyframes) {
    super();
    if (speed > 1000) {
      this.speed = 1000;
    } else {
      this.speed = speed;
    }
    this.shapeNames = shapeMap;
    this.shapeKeyframes = shapeKeyframes;
    this.fileChooser = new JFileChooser(".");
    this.fileChooser.setAcceptAllFileFilterUsed(false);
    this.setTitle("Animator Editor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.shapeTypeComboBox = new JComboBox(ShapeType.values());
    this.shapeNamesComboBox = new JComboBox(this.shapeNames.toArray());
    this.fileType = new JComboBox(FileType.values());

    setupAddShapePanelDialogBox();

    setupAnimationButtonsPanel();

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 0;
    this.playPauseButton = new JButton("Play/Pause");
    this.playPauseButton.setActionCommand("PlayPause");

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.playPauseButton, c);

    c.gridx = 1;

    this.rewindButton = new JButton("Rewind");
    this.rewindButton.setActionCommand("Rewind");

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.rewindButton, c);

    c.gridx = 2;

    this.loopButton = new JButton("Enable Looping");
    this.loopButton.setActionCommand("Loop");

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.loopButton, c);

    c.gridx = 3;

    this.noLoopButton = new JButton("Disable Looping");
    this.noLoopButton.setActionCommand("NoLoop");
    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.noLoopButton, c);

    c.gridx = 5;

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, new JLabel("Speed : "), c);

    c.gridx = 6;
    this.speedLabel = new JLabel(String.valueOf(this.speed));

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.speedLabel, c);

    c.gridx = 7;

    this.speedSlider = new JSlider(1, 1000, this.speed);
    this.speedSlider.setMajorTickSpacing(50);
    this.speedSlider.setPaintTicks(true);

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.speedSlider, c);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 8;
    c.insets = new Insets(15, 0, 0, 0);

    setupSelectionListPanel();

    setupShapeButtonPanel();

    this.selectionListPanel.add(this.shapeButtonPanel);

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.selectionListPanel, c);

    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 8;
    c.gridheight = 1;

    setupAttributesPanel();

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.attributesPanel, c);

    c.gridy = 4;
    c.gridheight = 1;
    setupAddKeyframePanel();
    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.addKeyframePanel, c);

    c.gridy = 5;
    setupSaveLoadButtonPanel();
    addComponentToGridBagLayoutPanel(this.animationButtonPanel, this.saveLoadPanel, c);

    c.weighty = 1;
    c.weightx = 1;
    c.gridy++;

    addComponentToGridBagLayoutPanel(this.animationButtonPanel, new JLabel(" "), c);

    animationPanel = new AnimationPanel(originX, originY);
    animationPanel.setPreferredSize(new Dimension(width, height));

    setupScrollPanel();
    setupSplitPanel();

    this.pack();


  }

  protected void setupAddShapePanelDialogBox() {
    this.addShapePanel = new JPanel();
    this.addShapePanel.setLayout(new BoxLayout(addShapePanel, BoxLayout.Y_AXIS));
    this.addShapePanel.add(new JLabel("Shape Name:"));
    this.shapeName = new JTextField(5);
    this.addShapePanel.add(shapeName);
    this.addShapePanel.add(new JLabel("Shape Type:"));
    this.addShapePanel.add(shapeTypeComboBox);
  }

  protected void setupAnimationButtonsPanel() {
    this.animationButtonPanel = new JPanel();
    this.animationButtonPanel.setLayout(new GridBagLayout());
  }

  protected void setupSplitPanel() {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, animationButtonPanel,
        scrollPane);
    splitPane.setEnabled(false);
    this.add(splitPane);
  }

  protected void setupScrollPanel() {
    this.scrollPane = new JScrollPane(animationPanel);
  }

  protected void setupSelectionListPanel() {
    createSelectionListPanel();
    setupShapeKeyframeList();
  }

  protected void createSelectionListPanel() {
    this.selectionListPanel = new JPanel();
    this.selectionListPanel.setBorder(BorderFactory.createTitledBorder("Layer, Shape, and "
        + "Keyframe List"));
    this.selectionListPanel.setLayout(new BoxLayout(this.selectionListPanel, BoxLayout.X_AXIS));
  }

  protected void setupShapeKeyframeList() {

    this.createInitialShapeKeyframeList();
    this.listOfShapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.selectionListPanel.add(new JScrollPane(this.listOfShapes));

    DefaultListModel<Integer> dataForShapeListOfKeyframes = new DefaultListModel<>();
    this.shapeListOfKeyframes = new JList<>(dataForShapeListOfKeyframes);
    this.shapeListOfKeyframes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.selectionListPanel.add(new JScrollPane(this.shapeListOfKeyframes));
  }

  protected void createInitialShapeKeyframeList() {
    DefaultListModel<String> dataForListOfShapeNames = new DefaultListModel<>();
    for (String name : this.shapeNames) {
      dataForListOfShapeNames.addElement(name);
    }
    this.listOfShapes = new JList<>(dataForListOfShapeNames);
  }

  protected void setupShapeButtonPanel() {
    this.createShapeButtonPanel();
    this.setupShapeButtons();
  }

  protected void createShapeButtonPanel() {
    this.shapeButtonPanel = new JPanel();
    this.shapeButtonPanel.setLayout(new BoxLayout(this.shapeButtonPanel, BoxLayout.Y_AXIS));
  }

  protected void setupShapeButtons() {
    this.addShape = new JButton("Add Shape");
    this.addShape.setActionCommand("AddShape");
    this.addShape.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.addShape);
    this.deleteShape = new JButton("Delete Shape");
    this.deleteShape.setActionCommand("DeleteShape");
    this.deleteShape.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.deleteShape);
    this.deleteKeyframe = new JButton("Delete Keyframe");
    this.deleteKeyframe.setActionCommand("DeleteKeyframe");
    this.deleteKeyframe.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.shapeButtonPanel.add(this.deleteKeyframe);
  }

  protected void setupAttributesPanel() {
    this.createAttributesPanel();
    this.setupXYWHRGB();
    this.addEditKeyframeButton();
  }

  protected void createAttributesPanel() {
    this.attributesPanel = new JPanel();
    this.attributesPanel
        .setBorder(BorderFactory.createTitledBorder("Selected Keyframe Attributes"));
    this.attributesPanel.setLayout(new FlowLayout());
  }

  protected void setupXYWHRGB() {
    this.attributesPanel.add(new JLabel("X:"));
    this.xPositionEdit = new JTextField(3);
    this.attributesPanel.add(this.xPositionEdit);
    this.attributesPanel.add(new JLabel("Y:"));
    this.yPositionEdit = new JTextField(3);
    this.attributesPanel.add(this.yPositionEdit);
    this.attributesPanel.add(new JLabel("Width:"));
    this.widthEdit = new JTextField(3);
    this.attributesPanel.add(this.widthEdit);
    this.attributesPanel.add(new JLabel("Height:"));
    this.heightEdit = new JTextField(3);
    this.attributesPanel.add(this.heightEdit);
    this.attributesPanel.add(new JLabel("R:"));
    this.colorREdit = new JTextField(3);
    this.attributesPanel.add(this.colorREdit);
    this.attributesPanel.add(new JLabel("G:"));
    this.colorGEdit = new JTextField(3);
    this.attributesPanel.add(this.colorGEdit);
    this.attributesPanel.add(new JLabel("B:"));
    this.colorBEdit = new JTextField(3);
    this.attributesPanel.add(this.colorBEdit);
  }

  protected void addEditKeyframeButton() {
    this.editKeyframe = new JButton("Edit Keyframe");
    this.editKeyframe.setActionCommand("EditKeyframe");
    this.attributesPanel.add(this.editKeyframe);
  }

  protected void setupAddKeyframePanel() {
    this.createKeyframePanel();
    this.setupTXYWHRGB();
    this.addKeyframeButton();
  }

  protected void createKeyframePanel() {
    this.addKeyframePanel = new JPanel();
    this.addKeyframePanel.setBorder(BorderFactory.createTitledBorder("Add Keyframe Attributes"));
    this.addKeyframePanel.setLayout(new FlowLayout());
  }

  protected void addKeyframeButton() {
    this.addKeyframe = new JButton("Add Keyframe");
    this.addKeyframe.setActionCommand("AddKeyframe");
    this.addKeyframePanel.add(this.addKeyframe);
  }

  protected void setupTXYWHRGB() {
    this.addKeyframePanel.add(new JLabel("Shape:"));
    this.addKeyframePanel.add(this.shapeNamesComboBox);
    this.addKeyframePanel.add(new JLabel("Time:"));
    this.addTime = new JTextField(3);
    this.addKeyframePanel.add(this.addTime);
    this.addKeyframePanel.add(new JLabel("X:"));
    this.xPositionAdd = new JTextField(3);
    this.addKeyframePanel.add(this.xPositionAdd);
    this.addKeyframePanel.add(new JLabel("Y:"));
    this.yPositionAdd = new JTextField(3);
    this.addKeyframePanel.add(this.yPositionAdd);
    this.addKeyframePanel.add(new JLabel("Width:"));
    this.widthAdd = new JTextField(3);
    this.addKeyframePanel.add(this.widthAdd);
    this.addKeyframePanel.add(new JLabel("Height:"));
    this.heightAdd = new JTextField(3);
    this.addKeyframePanel.add(this.heightAdd);
    this.addKeyframePanel.add(new JLabel("R:"));
    this.colorRAdd = new JTextField(3);
    this.addKeyframePanel.add(this.colorRAdd);
    this.addKeyframePanel.add(new JLabel("G:"));
    this.colorGAdd = new JTextField(3);
    this.addKeyframePanel.add(this.colorGAdd);
    this.colorBAdd = new JTextField(3);
    this.addKeyframePanel.add(new JLabel("B:"));
    this.addKeyframePanel.add(this.colorBAdd);
  }

  protected void setupSaveLoadButtonPanel() {
    this.saveLoadPanel = new JPanel();
    this.saveLoadPanel.setBorder(BorderFactory.createTitledBorder("Export/Load Options"));
    this.saveLoadPanel.setLayout(new FlowLayout());
    this.saveLoadPanel.add(new JLabel("Export As:"));
    this.saveLoadPanel.add(this.fileType);
    this.saveAnimation = new JButton("Export Animation");
    this.saveAnimation.setActionCommand("SaveAnimation");
    this.saveLoadPanel.add(this.saveAnimation);
    this.loadAnimation = new JButton("Load Animation");
    this.loadAnimation.setActionCommand("LoadAnimation");
    this.saveLoadPanel.add(this.loadAnimation);
  }

  protected void addComponentToGridBagLayoutPanel(JPanel panel, Component component,
      GridBagConstraints constraints) {
    panel.add(component, constraints);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public void makeVisible() {

    this.setVisible(true);
  }


  @Override
  public void refresh(List<ImmutableShape> shapes) {
    this.animationPanel.setCurrentShapes(shapes);
    this.repaint();
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
    saveAnimation.addActionListener(listener);
    loadAnimation.addActionListener(listener);
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
    this.speed = speed;
    speedLabel.setText(String.valueOf(speed));
  }

  @Override
  public void showShapeListKeyframe(String name) {
    DefaultListModel<Integer> newData = new DefaultListModel<>();
    Map<Integer, Attribute> shapeKeyframe = shapeKeyframes.get(name);
    for (Integer i : shapeKeyframe.keySet()) {
      newData.addElement(i);
    }
    shapeListOfKeyframes.setModel(newData);
  }

  @Override
  public void showKeyframeAttributes(int keyframe) {
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
    DataSubmission newShapeSubmission = null;
    int result = JOptionPane.showConfirmDialog(null, addShapePanel,
        "Shape Name and Type?", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      newShapeSubmission = new ShapeDataSubmission(shapeName.getText(),
          (ShapeType) shapeTypeComboBox.getSelectedItem());
    }
    return newShapeSubmission;
  }

  @Override
  public DataSubmission addLayerToAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported!");
  }

  @Override
  public DataSubmission addKeyframetoAnimation() {
    DataSubmission newKeyframeSubmission = null;
    try {
      newKeyframeSubmission = new KeyframeDataSubmission(Integer.parseInt(addTime.getText()),
          new Position2D(Double.parseDouble(xPositionAdd.getText()),
              Double.parseDouble(yPositionAdd.getText())), Integer.parseInt(widthAdd.getText()),
          Integer.parseInt(heightAdd.getText()), new Color(Integer.parseInt(colorRAdd.getText()),
          Integer.parseInt(colorGAdd.getText()), Integer.parseInt(colorBAdd.getText())),
          shapeNamesComboBox.getSelectedItem().toString());
    } catch (NumberFormatException e) {
      showErrorMessage("Non Number Inputs!");
    }
    return newKeyframeSubmission;
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
    if (listOfShapes.getSelectedValue() != null) {
      DefaultListModel listModel = (DefaultListModel) shapeListOfKeyframes.getModel();
      listModel.removeAllElements();
      return new ShapeDataSubmission(listOfShapes.getSelectedValue(), ShapeType.OVAL);
    }
    return null;
  }

  @Override
  public DataSubmission removeLayerFromAnimation() {
    throw new UnsupportedOperationException("Operation Not Supported!");
  }

  @Override
  public DataSubmission editKeyframe() {
    DataSubmission editShapeKeyframeSubmission = null;
    if (listOfShapes.getSelectedValue() != null
        && shapeListOfKeyframes.getSelectedValue() != null) {
      try {
        editShapeKeyframeSubmission = new KeyframeDataSubmission(shapeListOfKeyframes.
            getSelectedValue(), new Position2D(Double.parseDouble(xPositionEdit.getText()),
            Double.parseDouble(yPositionEdit.getText())), Integer.parseInt(widthEdit.getText()),
            Integer.parseInt(heightEdit.getText()), new Color(Integer.
            parseInt(colorREdit.getText()), Integer.parseInt(colorGEdit.getText()),
            Integer.parseInt(colorBEdit.getText())), listOfShapes.getSelectedValue());
      } catch (NumberFormatException e) {
        showErrorMessage("Non Number Inputs!");
      }
    }
    return editShapeKeyframeSubmission;
  }

  @Override
  public DataSubmission editLayerPosition() {
    return null;
  }

  @Override
  public File getSaveAnimationType() {
    File animationFile = null;
    String fileExtension;
    if (fileType.getSelectedItem().equals(FileType.SVG)) {
      this.fileChooser.setFileFilter(new FileNameExtensionFilter("SVG Animation File",
          "svg"));
      fileExtension = ".svg";
    } else {
      this.fileChooser.setFileFilter(new FileNameExtensionFilter("Text Animation File",
          "txt"));
      fileExtension = ".txt";
    }
    int retvalue = this.fileChooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      String filePath = this.fileChooser.getSelectedFile().getPath() + fileExtension;
      animationFile = new File(filePath);
    }
    this.fileChooser.resetChoosableFileFilters();
    return animationFile;
  }

  @Override
  public File loadAnimation() {
    File animationFile = null;

    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
        "Animation Text Files", "txt");
    this.fileChooser.setFileFilter(fileFilter);
    int retvalue = this.fileChooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      animationFile = this.fileChooser.getSelectedFile();
    }
    this.fileChooser.resetChoosableFileFilters();
    return animationFile;
  }

  @Override
  public DataSubmission removeKeyframe() {
    DataSubmission removeKeyframeSubmission = null;
    if (listOfShapes.getSelectedValue() != null
        && shapeListOfKeyframes.getSelectedValue() != null) {
      Attribute keyframe = shapeKeyframes.get(listOfShapes.getSelectedValue())
          .get(shapeListOfKeyframes.getSelectedValue());
      removeKeyframeSubmission = new KeyframeDataSubmission(shapeListOfKeyframes.getSelectedValue(),
          new Position2D(keyframe.getX(), keyframe.getY()), keyframe.getW(), keyframe.getH(),
          new Color(keyframe.getR(), keyframe.getG(), keyframe.getB()),
          listOfShapes.getSelectedValue());
    }
    return removeKeyframeSubmission;
  }

  @Override
  public void updateAnimationPanel(int width, int height, int originX, int originY) {
    this.animationPanel.updateAnimationPanel(originX, originY);
    this.animationPanel.setPreferredSize(new Dimension(width, height));
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

  protected void setShapeList() {
    DefaultListModel<String> dataForListOfShapeNames = new DefaultListModel<>();
    for (String name : shapeNames) {
      dataForListOfShapeNames.addElement(name);
    }
    listOfShapes.setModel(dataForListOfShapeNames);
    shapeNamesComboBox.setModel(new DefaultComboBoxModel(this.shapeNames.toArray()));
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
  }
}
