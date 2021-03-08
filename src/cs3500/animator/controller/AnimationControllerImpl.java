package cs3500.animator.controller;

import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.AnimatorTextView;
import cs3500.animator.view.DataSubmission;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import cs3500.animator.view.AnimatorView;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;


/**
 * Represents a controller of the Animation. The controller is the medium between the Animator Model
 * and Animator Views and delegates info from model to view.
 */
public class AnimationControllerImpl implements AnimatorController {

  private Animator model;
  private AnimatorView view;
  private String typeView;
  private Appendable output;
  private Timer tm;
  private int animationCounter;
  private boolean loop;
  private boolean play;
  private int speed;

  /**
   * Represents an implementation of a controller of an Animation. The controller communicates
   * between the view and the model.
   *
   * @param model - editable version model of the Animation
   * @param view - renders the Animation
   * @param outputFile - String representing file to write data from the view to to display the
   *        Animation
   */
  public AnimationControllerImpl(Animator model, AnimatorView view, String typeView,
      String outputFile, int speed) {
    this.model = model;
    this.view = view;
    this.typeView = typeView;
    this.animationCounter = 0;
    this.loop = false;
    this.play = false;
    this.speed = speed;
    configureButtonListener();
    configureShapeListListener();
    configureKeyframeListListener();
    configureSliderChangeListener();
    configureScrubSliderChangeListener();
    configureLayerListListener();
    checkFileOutType(outputFile);
    setTimer();
  }

  /**
   * Configures the Scrubbing JSlider Listener class used for Change Events.
   */
  private void configureScrubSliderChangeListener() {
    ScrubSliderChangeListener listener = new ScrubSliderChangeListener();
    this.view.addScrubbingChangeSliderListener(listener);
  }

  /**
   * {@code ScrubSliderChangeListener} class that is implemented in {@link AnimationControllerImpl}.
   * This class is used to handle the action of moving the animation Slider.
   */
  private class ScrubSliderChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();
      animationCounter = slider.getValue();
      view.refresh(model.getForwardAnimationStateTick(animationCounter));
    }
  }

  /**
   * Determines and creates file output type based on input.
   *
   * @param outputFile - type of output file
   */
  private void checkFileOutType(String outputFile) {
    if (outputFile.equals("System.out")) {
      output = new PrintStream(System.out);
    } else {
      try {
        output = new PrintStream(new FileOutputStream(outputFile));
      } catch (IOException e1) {
        this.view.showErrorMessage("Invalid Output File");
        System.exit(0);
      }
    }
  }

  /**
   * Configures the Timer used for Action Driven Events.
   */
  private void setTimer() {
    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (pastMaxAnimationTime()) {
          if (loop) {
            animationCounter = 0;
          } else {
            tm.stop();
            play = false;
          }
        }
        refreshAnimation(animationCounter);
        animationCounter++;
      }
    };
    this.tm = new Timer(1000 / speed, listener);
  }

  /**
   * Configures the Button Listener class used for Action Driven Events.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put("PlayPause", new PlayPauseButtonAction());
    buttonClickedMap.put("Rewind", new RewindButtonAction());
    buttonClickedMap.put("Loop", new LoopButtonAction());
    buttonClickedMap.put("NoLoop", new NoLoopButtonAction());
    buttonClickedMap.put("AddShape", new AddShapeButtonAction());
    buttonClickedMap.put("DeleteShape", new DeleteShapeButtonAction());
    buttonClickedMap.put("DeleteKeyframe", new DeleteKeyframeButtonAction());
    buttonClickedMap.put("AddKeyframe", new AddKeyframeButtonAction());
    buttonClickedMap.put("EditKeyframe", new EditKeyframeButtonAction());
    buttonClickedMap.put("SaveAnimation", new SaveAnimationButtonAction());
    buttonClickedMap.put("LoadAnimation", new LoadAnimationButtonAction());
    buttonClickedMap.put("AddLayer", new AddLayerButtonAction());
    buttonClickedMap.put("DeleteLayer", new DeleteLayerButtonAction());
    buttonClickedMap.put("ReorderLayer", new ReorderLayerButtonAction());
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  /**
   * Signals to refresh the visual by calling the view's refresh method.
   */
  private void refreshAnimation(int currentTick) {
    this.view.refresh(this.model.getForwardAnimationStateTick(currentTick));
    this.view.setAnimationSliderValue(currentTick);
  }

  /**
   * Determines whether the max end time of the animation has been reached.
   *
   * @return - whether the end of the animation has been reached
   */
  private boolean pastMaxAnimationTime() {
    return animationCounter == this.model.getAnimationEndTime();
  }

  /**
   * Configures the Shape JList Listener class used for List Selection Events.
   */
  private void configureShapeListListener() {
    ShapeListSelectionListener listener = new ShapeListSelectionListener();
    this.view.addShapeListListener(listener);
  }

  /**
   * Configures the Keyframe JList Listener class used for List Selection Events.
   */
  private void configureKeyframeListListener() {
    KeyframeListSelectionListener listener = new KeyframeListSelectionListener();
    this.view.addKeyframeListListener(listener);
  }

  /**
   * Configures the Keyframe JList Listener class used for List Selection Events.
   */
  private void configureLayerListListener() {
    LayerListSelectionListener listener = new LayerListSelectionListener();
    this.view.addLayerListListener(listener);
  }

  /**
   * Configures the Speed JSlider Listener class used for Change Events.
   */
  private void configureSliderChangeListener() {
    SliderChangeListener listener = new SliderChangeListener();
    this.view.addSpeedChangeSliderListener(listener);
  }

  @Override
  public void playAnimation() {
    view.animationOutput(output);
    view.makeVisible();
    if (typeView.equals("visual")) {
      tm.start();
    }
  }

  /**
   * {@code ShapeListSelectionListener} class that is implemented in {@link AnimationControllerImpl}
   * . This class is used to handle the action of selection of the Shape JList.
   */
  private class ShapeListSelectionListener implements javax.swing.event.ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        JList list = (JList) e.getSource();
        view.showShapeListKeyframe(list.getModel().getElementAt(list.getSelectedIndex())
            .toString());
      }

    }
  }

  /**
   * {@code SaveAnimationButtonAction} class that is implemented in {@link AnimationControllerImpl}
   * . This class is used to handle the action of clicking the Export Animation button.
   */
  private class SaveAnimationButtonAction implements Runnable {

    @Override
    public void run() {
      File animationFile = view.getSaveAnimationType();
      if (animationFile != null) {
        try {
          output = new PrintStream(new FileOutputStream(animationFile));
        } catch (FileNotFoundException e) {
          view.showErrorMessage("Unable to save animation");
          return;
        }
        model.resetCurrentMotions();
        model.getForwardAnimationStateTick(model.getAnimationStartTime());
        if (animationFile.getName().contains(".svg")) {
          AnimatorSVGView exportSVG = new AnimatorSVGView(model, speed);
          exportSVG.animationOutput(output);
        } else if (animationFile.getName().contains(".txt")) {
          AnimatorTextView exportText = new AnimatorTextView(model);
          exportText.animationOutput(output);
        }
      }
    }
  }

  /**
   * {@code LoadAnimationButtonAction} class that is implemented in {@link AnimationControllerImpl}
   * . This class is used to handle the action of clicking the Load Animation button.
   */
  private class LoadAnimationButtonAction implements Runnable {

    @Override
    public void run() {
      File animationFile = view.loadAnimation();
      Readable input;
      AnimationBuilder<Animator> newAnimationBuilder = new AnimatorImpl.Builder();
      if (animationFile != null) {
        try {
          input = new BufferedReader(new FileReader(animationFile));

        model = AnimationReader.parseFile(input, newAnimationBuilder);
        view.updateAnimationPanel(model.getBoundsWidth(), model.getBoundsHeight(),
            model.getBoundsX(), model.getBoundsY());
        view.updateLayerList(model.getLayers().keySet());
        view.updateShapeList(model.getShapeMap().keySet());
        view.updateKeyframeList(model.getKeyframes());
        animationCounter = model.getAnimationStartTime();
        view.refresh(model.getForwardAnimationStateTick(animationCounter));
        view.setAnimationSliderValue(animationCounter);
        view.setAnimationStartTime(model.getAnimationStartTime());
        view.setAnimationEndTime(model.getAnimationEndTime());
        } catch (FileNotFoundException e) {
          view.showErrorMessage("File Could Not Be Found");
          return;
        } catch (IllegalArgumentException f) {
          view.showErrorMessage(f.getMessage());
        }
      }
    }
  }

  /**
   * {@code KeyframeListSelectionListener} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking Keyframe JList.
   */
  private class KeyframeListSelectionListener implements javax.swing.event.ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        JList list = (JList) e.getSource();
        view.refresh(model.getForwardAnimationStateTick(
            (int) list.getModel().getElementAt(list.getSelectedIndex())));
        animationCounter = (int) list.getModel().getElementAt(list.getSelectedIndex());
        view.showKeyframeAttributes((int) list.getModel().getElementAt(list.getSelectedIndex()));
      }

    }
  }

  /**
   * {@code LayerListSelectionListener} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking Layer JList.
   */
  private class LayerListSelectionListener implements javax.swing.event.ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        JList list = (JList) e.getSource();
        view.updateShapeList(new LinkedHashSet<>(model.getLayers()
            .get(list.getModel().getElementAt(list.getSelectedIndex()))));
      }

    }
  }

  /**
   * {@code SliderChangeListener} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of moving the Speed Slider.
   */
  private class SliderChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();
      if (!slider.getValueIsAdjusting()) {
        speed = slider.getValue();
        tm.setDelay(1000 / speed);
        view.changeSpeed(speed);
      }
    }
  }

  /**
   * {@code PlayPauseButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the Play Button.
   */
  private class PlayPauseButtonAction implements Runnable {

    @Override
    public void run() {
      if (!play) {
        tm.start();
        play = true;
      } else {
        tm.stop();
        play = false;
      }
    }
  }

  /**
   * {@code RewindButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the Rewind Button.
   */
  private class RewindButtonAction implements Runnable {

    @Override
    public void run() {
      animationCounter = model.getAnimationStartTime();
      view.refresh(model.getForwardAnimationStateTick(animationCounter));
      view.setAnimationSliderValue(model.getAnimationStartTime());
    }
  }

  /**
   * {@code LoopButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the Loop Button.
   */
  private class LoopButtonAction implements Runnable {

    @Override
    public void run() {
      loop = true;
    }
  }

  /**
   * {@code NoLoopButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the No Loop Button.
   */
  private class NoLoopButtonAction implements Runnable {

    @Override
    public void run() {
      loop = false;
    }
  }

  /**
   * {@code AddShapeButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the Add Shape Button.
   */
  private class AddShapeButtonAction implements Runnable {

    @Override
    public void run() {
      try {
        DataSubmission shapeToAddSubmission = view.addShapeToAnimation();
        if (shapeToAddSubmission != null) {
          model.addShape(shapeToAddSubmission.getShapeName(),
              shapeToAddSubmission.getShapeType().getShapeType(), shapeToAddSubmission.getLayer());
          view.updateShapeList(new LinkedHashSet<>(model.getLayers()
              .get(shapeToAddSubmission.getCurrentLayerSelected())));
          view.updateKeyframeList(model.getKeyframes());
          view.updateKeyframeList(model.getKeyframes());
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * {@code AddLayerButtonAction} class that is implemented in {@link AnimationControllerImpl}.This
   * class is used to handle the action of clicking the Add Layer Button.
   */
  private class AddLayerButtonAction implements Runnable {

    @Override
    public void run() {
      try {
        DataSubmission layerToAnimation = view.addLayerToAnimation();
        if (layerToAnimation != null) {
          model.addLayer(layerToAnimation.getLayer());
          view.updateLayerList(model.getLayers().keySet());
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * {@code ReorderLayerButtonAction} class that is implemented in {@link AnimationControllerImpl}.
   * This class is used to handle the action of clicking the Reorder Layer Button.
   */
  private class ReorderLayerButtonAction implements Runnable {

    @Override
    public void run() {
      try {
        DataSubmission reorderLayers = view.editLayerPosition();
        if (reorderLayers != null) {
          model.changeLayer(reorderLayers.getCurrentLayerSelected(), reorderLayers.getLayer());
          view.updateLayerList(model.getLayers().keySet());
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * {@code DeleteShapeButtonAction} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking the Delete Shape
   * Button.
   */
  private class DeleteShapeButtonAction implements Runnable {

    @Override
    public void run() {
      deleteShape();
      view.refresh(model.getForwardAnimationStateTick(animationCounter));
    }
  }

  private void deleteShape() {
    DataSubmission shapeName = view.removeShapeFromAnimation();
    int startTime = model.getAnimationStartTime();
    int endTime = model.getAnimationEndTime();
    if (shapeName != null) {
      model.deleteShape(shapeName.getShapeName());
      view.updateShapeList(new LinkedHashSet<>(model.getLayers().get(shapeName
          .getCurrentLayerSelected())));
      if (model.getAnimationStartTime() != startTime) {
        this.view.setAnimationStartTime(model.getAnimationStartTime());
      } else if (model.getAnimationEndTime() != endTime) {
        this.view.setAnimationEndTime(model.getAnimationEndTime());
      }
    }
  }

  /**
   * {@code DeleteLayerButtonAction} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking the Delete Layer
   * Button.
   */
  private class DeleteLayerButtonAction implements Runnable {

    @Override
    public void run() {
      deleteLayer();
    }
  }

  private void deleteLayer() {
    DataSubmission layer = view.removeLayerFromAnimation();
    int startTime = model.getAnimationStartTime();
    int endTime = model.getAnimationEndTime();
    if (layer != null) {
      model.deleteLayer(layer.getLayer());
      view.updateLayerList(model.getLayers().keySet());
      if (model.getAnimationStartTime() != startTime) {
        this.view.setAnimationStartTime(model.getAnimationStartTime());
      } else if (model.getAnimationEndTime() != endTime) {
        this.view.setAnimationEndTime(model.getAnimationEndTime());
      }
      view.refresh(model.getForwardAnimationStateTick(animationCounter));
    }
  }

  /**
   * {@code DeleteKeyframeButtonAction} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking the Delete
   * Keyframe Button.
   */
  private class DeleteKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      deleteKeyframe();
    }
  }

  private void deleteKeyframe() {
    DataSubmission shapeRemoveKeyframeSubmission = view.removeKeyframe();
    if (shapeRemoveKeyframeSubmission != null) {
      int startTime = model.getAnimationStartTime();
      int endTime = model.getAnimationEndTime();
      model.deleteKeyframe(shapeRemoveKeyframeSubmission.getShapeName(),
          shapeRemoveKeyframeSubmission.getKeyframeTime());
      view.updateKeyframeList(model.getKeyframes());
      List<ImmutableShape> emptyList = new ArrayList<>();
      view.refresh(emptyList);
      if (model.getAnimationEndTime() != endTime) {
        this.view.setAnimationEndTime(model.getAnimationEndTime());
      } else if (model.getAnimationStartTime() != startTime) {
        this.view.setAnimationStartTime(model.getAnimationStartTime());
      }
    }
  }

  /**
   * {@code AddKeyframeButtonAction} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking the Add Keyframe
   * Button.
   */
  private class AddKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      addKeyframe();
    }
  }

  private void addKeyframe() {
    try {
      int startTime = model.getAnimationStartTime();
      int endTime = model.getAnimationEndTime();
      DataSubmission keyframeToAddSubmission = view.addKeyframetoAnimation();
      if (keyframeToAddSubmission != null) {
        model.addKeyframe(keyframeToAddSubmission.getShapeName(),
            keyframeToAddSubmission.getKeyframeTime(),
            new AttributeImpl.Builder(new Position2D(keyframeToAddSubmission.getXValue(),
                keyframeToAddSubmission.getYValue()), keyframeToAddSubmission.getWidth(),
                keyframeToAddSubmission.getHeight(), new Color(keyframeToAddSubmission.getColor()
                .getRed(), keyframeToAddSubmission.getColor().getGreen(),
                keyframeToAddSubmission.getColor().getBlue())).setAngle(keyframeToAddSubmission
                .getAngle()).build());
        view.updateKeyframeList(model.getKeyframes());
        if (keyframeToAddSubmission.getKeyframeTime() > endTime) {
          this.view.setAnimationEndTime(keyframeToAddSubmission.getKeyframeTime());
        } else if (keyframeToAddSubmission.getKeyframeTime() < startTime) {
          this.view.setAnimationStartTime(keyframeToAddSubmission.getKeyframeTime());
        }
        view.refresh(model.getForwardAnimationStateTick(keyframeToAddSubmission.getKeyframeTime()));
      }
    } catch (IllegalArgumentException e) {
      view.showErrorMessage(e.getMessage());
    }
  }

  /**
   * {@code EditKeyframeButtonAction} class that is implemented in {@link
   * AnimationControllerImpl}.This class is used to handle the action of clicking the Edit Keyframe
   * Button.
   */
  private class EditKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      try {
        DataSubmission keyframeToEditSubmission = view.editKeyframe();
        if (keyframeToEditSubmission != null) {
          model.editKeyframe(keyframeToEditSubmission.getShapeName(),
              keyframeToEditSubmission.getKeyframeTime(),
              new AttributeImpl.Builder(new Position2D(keyframeToEditSubmission.getXValue(),
                  keyframeToEditSubmission.getYValue()), keyframeToEditSubmission.getWidth(),
                  keyframeToEditSubmission.getHeight(),
                  new Color(keyframeToEditSubmission.getColor()
                      .getRed(), keyframeToEditSubmission.getColor().getGreen(),
                      keyframeToEditSubmission.getColor().getBlue()))
                  .setAngle(keyframeToEditSubmission
                      .getAngle()).build());
          view.updateKeyframeList(model.getKeyframes());
          view.refresh(model.getForwardAnimationStateTick(keyframeToEditSubmission
              .getKeyframeTime()));
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage(e.getMessage());
      }
    }

  }
}


