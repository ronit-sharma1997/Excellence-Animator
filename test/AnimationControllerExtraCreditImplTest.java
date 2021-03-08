import cs3500.animator.controller.AnimatorMockControllerImpl;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.Attribute;
import cs3500.animator.model.AttributeImpl;
import cs3500.animator.model.Position2D;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.SuperAnimatorMockExtraCreditEditorView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AnimationControllerExtraCreditImplTest {

  AnimationBuilder<Animator> animator1;
  Animator animation;
  SuperAnimatorMockExtraCreditEditorView editor;
  AnimatorMockControllerImpl controller;

  @Before
  public void setUpModelControllerView() {
    animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("R", "rectangle")
        .declareShape("C", "ellipse")
        .addMotion("R",
            1, new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build(),
            10, new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build())
        .addMotion("R",
            10, new AttributeImpl.Builder(new Position2D(200, 200), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build(),
            50, new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build())
        .addMotion("R",
            50, new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build(),
            51, new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build())
        .addMotion("R",
            51, new AttributeImpl.Builder(new Position2D(300, 300), 50, 100,
                new Color(255, 0, 0)).setAngle(0).build(),
            70, new AttributeImpl.Builder(new Position2D(300, 300), 25, 100,
                new Color(255, 0, 0)).setAngle(0).build())
        .addMotion("R",
            70, new AttributeImpl.Builder(new Position2D(300, 300), 25, 100,
                new Color(255, 0, 0)).setAngle(0).build(),
            100, new AttributeImpl.Builder(new Position2D(200, 200), 25, 100,
                new Color(255, 0, 0)).setAngle(0).build())
        .addMotion("C",
            6, new AttributeImpl.Builder(new Position2D(440, 70), 120, 60,
                new Color(0, 0, 255)).setAngle(0).build(),
            20, new AttributeImpl.Builder(new Position2D(440, 70), 120, 60,
                new Color(0, 0, 255)).setAngle(0).build())
        .addMotion("C",
            20, new AttributeImpl.Builder(new Position2D(440, 70), 120, 60,
                new Color(0, 0, 255)).setAngle(0).build(),
            50, new AttributeImpl.Builder(new Position2D(440, 250), 120, 60,
                new Color(0, 0, 255)).setAngle(0).build())
        .addMotion("C",
            50, new AttributeImpl.Builder(new Position2D(440, 250), 120, 60,
                new Color(0, 0, 255)).setAngle(0).build(),
            70, new AttributeImpl.Builder(new Position2D(440, 370), 120, 60,
                new Color(0, 170, 85)).setAngle(0).build())
        .addMotion("C",
            70, new AttributeImpl.Builder(new Position2D(440, 370), 120, 60,
                new Color(0, 170, 85)).setAngle(0).build(),
            80, new AttributeImpl.Builder(new Position2D(440, 370), 120, 60,
                new Color(0, 255, 0)).setAngle(0).build())
        .addMotion("C",
            80, new AttributeImpl.Builder(new Position2D(440, 370), 120, 60,
                new Color(0, 255, 0)).setAngle(0).build(),
            100, new AttributeImpl.Builder(new Position2D(440, 370), 120, 60,
                new Color(0, 255, 0)).setAngle(0).build());
    animation = animator1.build();
    editor = new SuperAnimatorMockExtraCreditEditorView(animation.getBoundsWidth(),
        animation.getBoundsHeight(), animation.getBoundsX(), animation.getBoundsY(),
        1, animation.getAnimationStartTime(), animation.getAnimationEndTime(),
        animation.getShapeMap().keySet(), animation.getLayers().keySet(), animation.getKeyframes());
    controller = new AnimatorMockControllerImpl(animation, editor,
        "super", "System.out", 1);
  }

  @Test
  public void AppropriateActionTakenOnSChangingAnimationScrubSlider() {
    //tests the ChangeEventListener for the Animation Scrubbing Slider to see if the correct action
    // is taken when the user wants to change the animation slider
    assertEquals(false, controller.loop);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    editor.animationSlider.setValue(10);
    ChangeEvent changeTick = new ChangeEvent(editor.animationSlider);
    controller.scrubSliderChangeListener.stateChanged(changeTick);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    //only action that should be run is ScrubListener Class
    assertEquals(true, controller.changeAnimationTickScrub);

  }

  @Test
  public void ActionCompletedChangingAnimationScrubSlider() {
    //tests the completion of the action of changing the Animation Scrub Slider to see if the
    // correct results if the user moves the slider
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.showKeyframe);
    assertEquals(false, editor.addKeyframeCompleted);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.setAnimationEndTime);
    assertEquals(false, editor.setAnimationStartTime);
    assertEquals(false, editor.setAnimationSliderValue);
    assertEquals(1, editor.animationSlider.getMinimum());
    assertEquals(100, editor.animationSlider.getMaximum());
    assertEquals(1, editor.animationSlider.getValue());
    editor.animationSlider.setValue(50);
    ChangeEvent changeEvent = new ChangeEvent(editor.animationSlider);
    controller.scrubSliderChangeListener.stateChanged(changeEvent);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
    assertEquals(50, editor.animationSlider.getValue());
  }

  @Test
  public void AppropriateResultTakenOnPlayButtonSChangingAnimationScrubSlider() {
    //tests the play button for the Animation Scrubbing Slider to see if the correct result
    // is taken when the user wants plays the animation; slider should move with tick
    ActionEvent playbutton = new ActionEvent(editor.playPauseButton,
        (int) System.currentTimeMillis(),
        editor.playPauseButton.getActionCommand());
    assertEquals(false, controller.loop);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, editor.setAnimationSliderValue);
    controller.buttonListener.actionPerformed(playbutton);
    assertEquals(0, controller.animationCounter);
    ActionEvent timer = new ActionEvent(controller.tm, (int) System.currentTimeMillis(),
        controller.tm.getActionCommand());
    controller.actionListener.actionPerformed(timer);
    assertEquals(false, controller.loop);
    assertEquals(true, controller.clickedPlayButton);
    //only action that should be run is Play Button
    assertEquals(true, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(true, editor.setAnimationSliderValue);
    assertEquals(1, editor.animationSlider.getValue());
    controller.actionListener.actionPerformed(timer);
    assertEquals(2, controller.animationCounter);
    assertEquals(1, editor.animationSlider.getValue());
    controller.actionListener.actionPerformed(timer);
    assertEquals(2, editor.animationSlider.getValue());
  }

  @Test
  public void ActionCompletedOnRewindButtonChangingAnimationScrubSlider() {
    //tests the completion of the action of clicking the Rewind Button to see if the correct
    // results if the user wants to rewind an animation. Slider should go back to beginning
    controller.animationCounter = 99;
    ActionEvent timer = new ActionEvent(controller.tm, (int) System.currentTimeMillis(),
        controller.tm.getActionCommand());
    controller.actionListener.actionPerformed(timer);
    assertEquals(99, this.editor.animationSlider.getValue());
    assertEquals(true, editor.refresh);
    editor.refresh = false;
    ActionEvent rewindButton = new ActionEvent(editor.rewindButton,
        (int) System.currentTimeMillis(),
        editor.rewindButton.getActionCommand());
    controller.buttonListener.actionPerformed(rewindButton);
    assertEquals(1, controller.animationCounter);
    assertEquals(true, editor.refresh);
    assertEquals(1, editor.animationSlider.getValue());
  }

  @Test
  public void ActionCompletedAddKeyframeButtonWithAngle() {
    //tests the completion of the action of clicking the Add Keyframe Button to see if the
    // correct results if the user clicks the button. Testing to see that angles are incorporated
    editor.shapeNamesComboBox.setSelectedItem("R");
    assertEquals(6, editor.shapeKeyframes.get("R").size());
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(1));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(10));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(50));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(51));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(70));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(100));
    editor.addTime.setText("110");
    editor.xPositionAdd.setText("20");
    editor.yPositionAdd.setText("100");
    editor.widthAdd.setText("50");
    editor.heightAdd.setText("100");
    editor.colorRAdd.setText("255");
    editor.colorGAdd.setText("100");
    editor.colorBAdd.setText("120");
    editor.angleEdit.setText("100");
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
    ActionEvent addKeyframe = new ActionEvent(editor.addKeyframe,
        (int) System.currentTimeMillis(),
        editor.addKeyframe.getActionCommand());
    controller.buttonListener.actionPerformed(addKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(true, editor.addKeyframeCompleted);
    assertEquals(false, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(7, editor.shapeKeyframes.get("R").size());
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(1));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(10));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(50));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(51));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(70));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(100));
    assertEquals(true, editor.shapeKeyframes.get("R").keySet().contains(110));
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
    assertArrayEquals(new int[]{20, 100, 50, 100, 255, 100, 120, 100},
        editor.shapeKeyframes.get("R").get(110).getAttributes());

  }

  @Test
  public void ActionCompletedEditKeyframeButton() {
    //tests the completion of the action of clicking the Edit Keyframe Button to see if the
    // correct results if the user clicks the button
    editor.listOfShapes.setSelectedValue("C", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes, 0,
        2, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(6, false);
    assertEquals("C", editor.listOfShapes.getSelectedValue());
    assertEquals(6, (int) editor.shapeListOfKeyframes.getSelectedValue());
    Attribute attribute = editor.shapeKeyframes.get("C").get(6);
    assertArrayEquals(new int[]{440, 70, 120, 60, 0, 0, 255, 0}, attribute.getAttributes());
    editor.xPositionEdit.setText("20");
    editor.yPositionEdit.setText("100");
    editor.widthEdit.setText("50");
    editor.heightEdit.setText("100");
    editor.colorREdit.setText("255");
    editor.colorGEdit.setText("100");
    editor.colorBEdit.setText("120");
    editor.angleEdit.setText("40");
    editor.showKeyframesOfList = false;
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    ActionEvent editKeyframe = new ActionEvent(editor.editKeyframe,
        (int) System.currentTimeMillis(),
        editor.editKeyframe.getActionCommand());
    controller.buttonListener.actionPerformed(editKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(true, editor.editKeyframeCompleted);
    attribute = editor.shapeKeyframes.get("C").get(6);
    assertArrayEquals(new int[]{20, 100, 50, 100, 255, 100, 120, 40}, attribute.getAttributes());
  }

  @Test
  public void AppropriateActionTakenOnClickingAddLayerButton() {
    //tests the ButtonListener  to see if the correct action
    // is taken when the user wants to add layer
    assertEquals(false, controller.loop);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, controller.clickedAddLayerAnimation);
    assertEquals(false, controller.clickedRemoveLayerAnimation);
    assertEquals(false, controller.clickedReorderLayer);
    ActionEvent addLayer = new ActionEvent(editor.addLayer, (int) System.currentTimeMillis(),
        editor.addLayer.getActionCommand());
    controller.buttonListener.actionPerformed(addLayer);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    //only action should be clicking add layer button
    assertEquals(true, controller.clickedAddLayerAnimation);
    assertEquals(false, controller.clickedRemoveLayerAnimation);
    assertEquals(false, controller.clickedReorderLayer);

  }

  @Test
  public void ActionCompletedClickingAddLayerButton() {
    //tests the completion of the action of clicking the Add Layer Button to see if the
    // correct results if the user clicks the button
    editor.layerNumber.setText("1");
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    assertEquals(false, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(1, animation.getLayers().size());
    ActionEvent addLayer = new ActionEvent(editor.addLayer,
        (int) System.currentTimeMillis(),
        editor.addLayer.getActionCommand());
    controller.buttonListener.actionPerformed(addLayer);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(true, editor.addLayerCompleted);
    assertEquals(false, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(2, animation.getLayers().size());
    assertEquals(true, animation.getLayers().keySet().contains(1));
    assertEquals(true, animation.getLayers().keySet().contains(0));
  }

  @Test
  public void AppropriateActionTakenOnClickingDeleteLayerButton() {
    //tests the ButtonListener  to see if the correct action
    // is taken when the user wants to delete layer
    assertEquals(false, controller.loop);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, controller.clickedAddLayerAnimation);
    assertEquals(false, controller.clickedRemoveLayerAnimation);
    assertEquals(false, controller.clickedReorderLayer);
    editor.shapeLayers.setSelectedValue(0, false);
    ActionEvent deleteLayer = new ActionEvent(editor.deleteLayer, (int) System.currentTimeMillis(),
        editor.deleteLayer.getActionCommand());
    controller.buttonListener.actionPerformed(deleteLayer);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, controller.clickedAddLayerAnimation);
    //only action should be clicking delete layer button
    assertEquals(true, controller.clickedRemoveLayerAnimation);
    assertEquals(false, controller.clickedReorderLayer);

  }

  @Test
  public void ActionCompletedClickingDeleteLayerButton() {
    //tests the completion of the action of clicking the Delete Layer Button to see if the
    // correct results if the user clicks the button
    assertEquals(1, animation.getLayers().size());
    editor.layerNumber.setText("1");
    ActionEvent addLayer = new ActionEvent(editor.addLayer,
        (int) System.currentTimeMillis(),
        editor.addLayer.getActionCommand());
    controller.buttonListener.actionPerformed(addLayer);
    assertEquals(2, animation.getLayers().size());
    editor.shapeLayers.setSelectedValue(0, false);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(true, editor.addLayerCompleted);
    assertEquals(false, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    editor.addLayerCompleted = false;
    ActionEvent deleteLayer = new ActionEvent(editor.deleteLayer, (int) System.currentTimeMillis(),
        editor.deleteLayer.getActionCommand());
    controller.buttonListener.actionPerformed(deleteLayer);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    //should refresh the screen
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    //the layer should be removed
    assertEquals(true, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(1, animation.getLayers().size());
    assertEquals(true, animation.getLayers().keySet().contains(1));

  }

  @Test
  public void AppropriateActionTakenOnClickingReorderLayerButton() {
    //tests the ButtonListener  to see if the correct action
    // is taken when the user wants to reorder layer
    assertEquals(false, controller.loop);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, controller.clickedAddLayerAnimation);
    assertEquals(false, controller.clickedRemoveLayerAnimation);
    assertEquals(false, controller.clickedReorderLayer);
    ActionEvent reorderLayer = new ActionEvent(editor.reorderLayer,
        (int) System.currentTimeMillis(),
        editor.reorderLayer.getActionCommand());
    editor.layersComboBox.setSelectedItem(0);
    editor.shapeLayers.setSelectedValue(0, false);
    controller.buttonListener.actionPerformed(reorderLayer);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.changeAnimationTickScrub);
    assertEquals(false, controller.clickedAddLayerAnimation);
    assertEquals(false, controller.clickedRemoveLayerAnimation);
    //only action should be clicking reorder layer button
    assertEquals(true, controller.clickedReorderLayer);
  }

  @Test
  public void ActionCompletedSettingAnimationStartTime() {
    //tests the completion of the action of Deleting a Keyframe and updating that affects the
    // animation start time
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    assertEquals(false, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(false, editor.setAnimationStartTime);
    assertEquals(false, editor.setAnimationEndTime);
    assertEquals(false, editor.setAnimationSliderValue);
    assertEquals(1, animation.getAnimationStartTime());
    ActionEvent deleteKeyframe = new ActionEvent(editor.deleteKeyframe,
        (int) System.currentTimeMillis(), editor.deleteKeyframe.getActionCommand());
    assertEquals(6, animation.getKeyframes().get("R").keySet().size());
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes, 0,
        2, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(1, false);
    assertEquals("R", editor.listOfShapes.getSelectedValue());
    assertEquals(1, (int) editor.shapeListOfKeyframes.getSelectedValue());
    controller.buttonListener.actionPerformed(deleteKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    //should refresh the screen
    assertEquals(true, editor.refresh);
    assertEquals(true, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    //the layer should be removed
    assertEquals(true, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(5, animation.getKeyframes().get("R").keySet().size());
    assertEquals(false, animation.getKeyframes().get("R").keySet().contains(1));
    assertEquals(6, animation.getAnimationStartTime());

  }

  @Test
  public void ActionCompletedSettingAnimationEndTime() {
    //tests the completion of the action of Adding a Keyframe and updating that affects the
    // animation end time
    editor.shapeNamesComboBox.setSelectedItem("R");
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    assertEquals(false, editor.removeLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(false, editor.setAnimationStartTime);
    assertEquals(false, editor.setAnimationEndTime);
    assertEquals(false, editor.setAnimationSliderValue);
    assertEquals(100, animation.getAnimationEndTime());
    editor.addTime.setText("110");
    editor.xPositionAdd.setText("20");
    editor.yPositionAdd.setText("100");
    editor.widthAdd.setText("50");
    editor.heightAdd.setText("100");
    editor.colorRAdd.setText("255");
    editor.colorGAdd.setText("100");
    editor.colorBAdd.setText("120");
    editor.angleEdit.setText("10");
    ActionEvent addKeyframe = new ActionEvent(editor.addKeyframe,
        (int) System.currentTimeMillis(), editor.addKeyframe.getActionCommand());
    assertEquals(6, animation.getKeyframes().get("R").keySet().size());
    controller.buttonListener.actionPerformed(addKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    //should refresh the screen
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.editKeyframeCompleted);
    assertEquals(false, editor.addLayerCompleted);
    assertEquals(false, editor.reorderLayerCompleted);
    assertEquals(7, animation.getKeyframes().get("R").keySet().size());

  }


}
