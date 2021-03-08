package cs3500.animator.controller;

import cs3500.animator.model.Animator;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.ImmutableShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.view.DataSubmission;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Represents a mock controller of the Animation used for testing the {@code AnimationController}.
 */
public final class AnimatorMockControllerImpl implements AnimatorController {

  public final Animator model;
  public final AnimatorView view;
  public String typeView;
  public Appendable output;
  public Timer tm;
  public int animationCounter;
  public boolean loop;
  public boolean shapeListSelected;
  public boolean keyframeSelected;
  public boolean changeSpeed;
  public boolean changeAnimationTickScrub;
  public boolean play;
  public boolean clickedRewindButton;
  public boolean playingAnimation;
  public boolean clickedAddShapeButton;
  public boolean clickedDeleteButton;
  public boolean clickedDeleteKeyframeButton;
  public boolean clickedAddKeyframeButton;
  public boolean clickedPlayButton;
  public boolean clickedEditKeyframe;
  public boolean clickedSaveAnimation;
  public boolean clickedLoadAnimation;
  public boolean clickedAddLayerAnimation;
  public boolean clickedRemoveLayerAnimation;
  public boolean clickedReorderLayer;
  public int speed;
  public ButtonListener buttonListener;
  public ActionListener actionListener;
  public ShapeListSelectionListener shapeListSelectionListener;
  public KeyframeListSelectionListener keyframeListSelectionListener;
  public SliderChangeListener sliderChangeListener;
  public ScrubSliderChangeListener scrubSliderChangeListener;

  /**
   * Represents an implementation of a mock controller of an Animation. The mock controller
   * communicates between a mock view and the model.
   *
   * @param model - a read only model of the Animation
   * @param view - renders the Animation
   * @param outputFile - String representing file to write data from the view to to display the
   *        Animation
   */
  public AnimatorMockControllerImpl(Animator model, AnimatorView view, String typeView,
      String outputFile, int speed) {
    this.model = model;
    this.view = view;
    this.typeView = typeView;
    this.animationCounter = 0;
    this.loop = false;
    this.play = false;
    this.clickedPlayButton = false;
    this.shapeListSelected = false;
    this.keyframeSelected = false;
    this.clickedSaveAnimation = false;
    this.clickedLoadAnimation = false;
    this.changeSpeed = false;
    this.clickedRewindButton = false;
    this.playingAnimation = false;
    this.clickedAddShapeButton = false;
    this.clickedDeleteButton = false;
    this.clickedDeleteKeyframeButton = false;
    this.clickedAddKeyframeButton = false;
    this.clickedEditKeyframe = false;
    this.changeAnimationTickScrub = false;
    this.clickedReorderLayer = false;
    this.clickedAddLayerAnimation = false;
    this.clickedRemoveLayerAnimation = false;
    this.speed = speed;
    configureButtonListener();
    configureShapeListListener();
    configureKeyframeListListener();
    configureSliderChangeListener();
    configureScrubSliderChangeListener();
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
    actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (animationCounter == model.getAnimationEndTime()) {
          if (loop) {
            animationCounter = 0;
          } else {
            //if not loop timer should stop meaning, this is last iteration so animationcounter
            // wil be max animation end time + 1
          }
        }
        view.refresh(model.getForwardAnimationStateTick(animationCounter));
        view.setAnimationSliderValue(animationCounter);
        animationCounter++;
      }
    };
    this.tm = new Timer(1000 / speed, actionListener);
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    buttonListener = new ButtonListener();
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

  private void configureShapeListListener() {
    shapeListSelectionListener = new ShapeListSelectionListener();
    this.view.addShapeListListener(shapeListSelectionListener);
  }

  private void configureKeyframeListListener() {
    keyframeListSelectionListener = new KeyframeListSelectionListener();
    this.view.addKeyframeListListener(keyframeListSelectionListener);
  }

  private void configureSliderChangeListener() {
    sliderChangeListener = new SliderChangeListener();
    this.view.addSpeedChangeSliderListener(sliderChangeListener);
  }

  /**
   * Configures the Scrubbing JSlider Listener class used for Change Events.
   */
  private void configureScrubSliderChangeListener() {
    scrubSliderChangeListener = new ScrubSliderChangeListener();
    this.view.addScrubbingChangeSliderListener(scrubSliderChangeListener);
  }

  @Override
  public void playAnimation() {
    view.animationOutput(output);
    playingAnimation = true;
    view.makeVisible();
    if (typeView.equals("visual")) {
      tm.start();
    }
  }


  public class ScrubSliderChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();
      animationCounter = slider.getValue();
      view.refresh(model.getForwardAnimationStateTick(animationCounter));
      changeAnimationTickScrub = true;
    }
  }

  public class ShapeListSelectionListener implements javax.swing.event.ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        JList list = (JList) e.getSource();
        view.showShapeListKeyframe(list.getModel().getElementAt(list.getSelectedIndex()).
            toString());
        shapeListSelected = true;
      }
    }

  }

  class SaveAnimationButtonAction implements Runnable {

    @Override
    public void run() {
      clickedSaveAnimation = true;
      File animationFile = view.getSaveAnimationType();
    }
  }

  class LoadAnimationButtonAction implements Runnable {

    @Override
    public void run() {
      clickedLoadAnimation = true;
      File animationFile = view.loadAnimation();
    }
  }

  public class KeyframeListSelectionListener implements javax.swing.event.ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        JList list = (JList) e.getSource();
        view.refresh(null);
        view.showKeyframeAttributes((int) list.getModel().getElementAt(list.getSelectedIndex()));
        keyframeSelected = true;
      }
    }
  }

  public class SliderChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();
      if (!slider.getValueIsAdjusting()) {
        changeSpeed = true;
        speed = slider.getValue();
        tm.setDelay(1000 / speed);
        view.changeSpeed(speed);
      }

    }
  }

  class PlayPauseButtonAction implements Runnable {

    @Override
    public void run() {
      clickedPlayButton = true;
      if (!play) {
        play = true;
      } else {
        play = false;
      }
    }
  }

  class RewindButtonAction implements Runnable {

    @Override
    public void run() {
      animationCounter = 0;
      clickedRewindButton = true;
      view.refresh(model.getForwardAnimationStateTick(0));
      view.setAnimationSliderValue(model.getAnimationStartTime());
    }
  }

  class LoopButtonAction implements Runnable {

    @Override
    public void run() {
      loop = true;
    }
  }

  class NoLoopButtonAction implements Runnable {

    @Override
    public void run() {
      loop = false;
    }
  }

  class AddShapeButtonAction implements Runnable {

    @Override
    public void run() {
      DataSubmission shapeToAdd = view.addShapeToAnimation();
      model.addShape(shapeToAdd.getShapeName(), shapeToAdd.getShapeType().getShapeType());
      clickedAddShapeButton = true;
      view.updateShapeList(model.getShapeMap().keySet());
      view.updateKeyframeList(model.getKeyframes());

    }
  }

  class DeleteShapeButtonAction implements Runnable {

    @Override
    public void run() {
      int startTime = model.getAnimationStartTime();
      int endTime = model.getAnimationEndTime();
      DataSubmission shapeName = view.removeShapeFromAnimation();
      model.deleteShape(shapeName.getShapeName());
      view.updateShapeList(model.getShapeMap().keySet());
      clickedDeleteButton = true;
      if (model.getAnimationStartTime() != startTime) {
        view.setAnimationStartTime(model.getAnimationStartTime());
      } else if (model.getAnimationEndTime() != endTime) {
        view.setAnimationEndTime(model.getAnimationEndTime());
      }
    }
  }

  class DeleteKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      int startTime = model.getAnimationStartTime();
      int endTime = model.getAnimationEndTime();
      DataSubmission shapeRemoveKeyframe = view.removeKeyframe();
      model.deleteKeyframe(shapeRemoveKeyframe.getShapeName(),
          shapeRemoveKeyframe.getKeyframeTime());
      view.updateKeyframeList(model.getKeyframes());
      List<ImmutableShape> emptyList = new ArrayList<>();
      view.refresh(emptyList);
      if (model.getAnimationEndTime() != endTime) {
        view.setAnimationEndTime(model.getAnimationEndTime());
      } else if (model.getAnimationStartTime() != startTime) {
        view.setAnimationStartTime(model.getAnimationStartTime());
      }
      clickedDeleteKeyframeButton = true;
    }
  }

  class AddKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      int startTime = model.getAnimationStartTime();
      int endTime = model.getAnimationEndTime();
      DataSubmission keyframeToAddSubmission = view.addKeyframetoAnimation();
      model.addKeyframe(keyframeToAddSubmission.getShapeName(),
          keyframeToAddSubmission.getKeyframeTime(), new AttributeImpl.
              Builder(new Position2D(keyframeToAddSubmission.getXValue(),
              keyframeToAddSubmission.getYValue()), keyframeToAddSubmission.getWidth(),
              keyframeToAddSubmission.getHeight(), keyframeToAddSubmission.getColor())
              .setAngle(keyframeToAddSubmission.getAngle()).build());
      view.updateKeyframeList(model.getKeyframes());
      if (keyframeToAddSubmission.getKeyframeTime() > endTime) {
        view.setAnimationEndTime(keyframeToAddSubmission.getKeyframeTime());
      } else if (keyframeToAddSubmission.getKeyframeTime() < startTime) {
        view.setAnimationStartTime(keyframeToAddSubmission.getKeyframeTime());
      }
      view.refresh(model.getForwardAnimationStateTick(keyframeToAddSubmission.getKeyframeTime()));
      clickedAddKeyframeButton = true;
    }
  }

  class EditKeyframeButtonAction implements Runnable {

    @Override
    public void run() {
      DataSubmission keyframeToEditSubmission = view.editKeyframe();
      model.editKeyframe(keyframeToEditSubmission.getShapeName(),
          keyframeToEditSubmission.getKeyframeTime(), new AttributeImpl.
              Builder(new Position2D(keyframeToEditSubmission.getXValue(),
              keyframeToEditSubmission.getYValue()), keyframeToEditSubmission.getWidth(),
              keyframeToEditSubmission.getHeight(), keyframeToEditSubmission.getColor())
              .setAngle(keyframeToEditSubmission.getAngle()).build());
      view.updateKeyframeList(model.getKeyframes());
      List<ImmutableShape> emptyList = new ArrayList<>();
      view.refresh(emptyList);
      clickedEditKeyframe = true;
    }
  }

  public class AddLayerButtonAction implements Runnable {

    @Override
    public void run() {
      clickedAddLayerAnimation = true;
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

  public class ReorderLayerButtonAction implements Runnable {

    @Override
    public void run() {
      clickedReorderLayer = true;
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

  public class DeleteLayerButtonAction implements Runnable {

    @Override
    public void run() {
      clickedRemoveLayerAnimation = true;
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
}

