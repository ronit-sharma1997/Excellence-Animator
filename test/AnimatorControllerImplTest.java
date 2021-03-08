import cs3500.animator.controller.AnimatorMockControllerImpl;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.Attribute;
import cs3500.animator.model.ShapeType;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.AnimatorMockEditorView;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AnimatorControllerImplTest {

  AnimationBuilder<Animator> animator1;
  Animator animation;
  AnimatorMockEditorView editor;
  AnimatorMockControllerImpl controller;

  @Before
  public void setUpModelControllerView() {
    animator1 = new AnimatorImpl.Builder().setBounds(0, 0,
        500, 500)
        .declareShape("R", "rectangle")
        .declareShape("C", "ellipse")
        .addMotion("R",
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("R",
            10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R", 50, 300, 300, 50, 100, 255, 0, 0,
            51, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R", 51, 300, 300, 50, 100, 255, 0, 0,
            70, 300, 300, 25, 100, 255, 0, 0)
        .addMotion("R", 70, 300, 300, 25, 100, 255, 0, 0,
            100, 200, 200, 25, 100, 255, 0, 0)
        .addMotion("C",
            6, 440, 70, 120, 60, 0, 0, 255,
            20, 440, 70, 120, 60, 0, 0, 255)
        .addMotion("C",
            20, 440, 70, 120, 60, 0, 0, 255,
            50, 440, 250, 120, 60, 0, 0, 255)
        //multiple changes occur here
        .addMotion("C", 50, 440, 250, 120, 60, 0, 0, 255,
            70, 440, 370, 120, 60, 0, 170, 85)
        .addMotion("C", 70, 440, 370, 120, 60, 0, 170, 85,
            80, 440, 370, 120, 60, 0, 255, 0)
        .addMotion("C", 80, 440, 370, 120, 60, 0, 255, 0,
            100, 440, 370, 120, 60, 0, 255, 0);
    animation = animator1.build();
    editor = new AnimatorMockEditorView(animation.getBoundsWidth(),
        animation.getBoundsHeight(), animation.getBoundsX(), animation.getBoundsY(),
        1, animation.getShapeMap().keySet(), animation.getKeyframes());
    controller = new AnimatorMockControllerImpl(animation, editor,
        "edit", "System.out", 1);
  }

  @Test
  public void AppropriateActionTakenOnPlayPauseButton() {
    //tests the actionListener for the PlayPauseButton to see if the correct action is taken when
    // the user clicks the play button
    ActionEvent playbutton = new ActionEvent(editor.playPauseButton,
        (int) System.currentTimeMillis(),
        editor.playPauseButton.getActionCommand());
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(playbutton);
    assertEquals(false, controller.loop);
    //only action that should be run is PlayPauseButtonAction Class
    assertEquals(true, controller.clickedPlayButton);
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

  }

  @Test
  public void ActionCompletedOnPlayPauseButtonPlayingAnimation() {
    //tests the completion of the action of clicking the PlayPauseButton to see if the correct
    // results if the user wants to play an animation
    assertEquals(false, controller.play);
    ActionEvent playbutton = new ActionEvent(editor.playPauseButton,
        (int) System.currentTimeMillis(),
        editor.playPauseButton.getActionCommand());
    controller.buttonListener.actionPerformed(playbutton);
    //animation should be playing after button is clicked
    assertEquals(true, controller.play);
  }

  @Test
  public void ActionCompletedOnPlayPauseButtonPausingAnimation() {
    //tests the completion of the action of clicking the PlayPauseButton to see if the correct
    // results if the user wants to pause an animation
    assertEquals(false, controller.play);
    ActionEvent playbutton = new ActionEvent(editor.playPauseButton,
        (int) System.currentTimeMillis(),
        editor.playPauseButton.getActionCommand());
    controller.buttonListener.actionPerformed(playbutton);
    assertEquals(true, controller.play);
    controller.buttonListener.actionPerformed(playbutton);
    //animation should be playing after button is clicked
    assertEquals(false, controller.play);
  }

  @Test
  public void AppropriateActionTakenOnRewindButton() {
    //tests the actionListener for the Rewind Button to see if the correct action is taken when the
    // user wants to rewind the animation
    ActionEvent rewindButton = new ActionEvent(editor.rewindButton,
        (int) System.currentTimeMillis(),
        editor.rewindButton.getActionCommand());
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(rewindButton);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    //only action that should be run is RewindButtonAction Class
    //animation should be rewinded now
    assertEquals(true, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
  }

  @Test
  public void ActionCompletedOnRewindButton() {
    //tests the completion of the action of clicking the Rewind Button to see if the correct
    // results if the user wants to rewind an animation
    controller.animationCounter = 100;
    assertEquals(false, editor.refresh);
    ActionEvent rewindButton = new ActionEvent(editor.rewindButton,
        (int) System.currentTimeMillis(),
        editor.rewindButton.getActionCommand());
    controller.buttonListener.actionPerformed(rewindButton);
    assertEquals(0, controller.animationCounter);
    assertEquals(true, editor.refresh);
    //animation should be playing after button is clicked
    assertEquals(false, controller.play);
  }

  @Test
  public void AppropriateActionTakenOnEnableLoopButton() {
    //tests the actionListener for the Enable Loop Button to see if the correct action is taken when
    // the user wants to loop the animation
    ActionEvent loopButton = new ActionEvent(editor.loopButton,
        (int) System.currentTimeMillis(),
        editor.loopButton.getActionCommand());
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(loopButton);
    //only action that should be run is LoopButtonAction Class
    //enabled loop should be set now
    assertEquals(true, controller.loop);
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
  }

  @Test
  public void ActionCompletedOnLoopButton() {
    //tests the completion of the action of clicking the Loop Button to see if the correct
    // results if the user wants to loop an animation
    controller.animationCounter = 99;
    assertEquals(false, controller.loop);
    assertEquals(false, editor.refresh);
    ActionEvent loopButton = new ActionEvent(editor.loopButton,
        (int) System.currentTimeMillis(),
        editor.loopButton.getActionCommand());
    controller.buttonListener.actionPerformed(loopButton);
    ActionEvent timer = new ActionEvent(controller.tm, (int) System.currentTimeMillis(),
        controller.tm.getActionCommand());
    assertEquals(true, controller.loop);
    //animationcounter shouldn't be affected by clicking loop button until the max end time
    // is reached at 100
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    editor.refresh = false;
    assertEquals(100, controller.animationCounter);
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    //counter is reset because we clicked the loop button
    assertEquals(1, controller.animationCounter);
  }

  @Test
  public void ActionCompletedOnLoopButtonThenNoLoopButton() {
    //tests the completion of the action of clicking the Loop Button to see if the correct
    // results if the user wants to loop an animation. We loop once and then click noloop button
    // which should have the animation stop at the end
    controller.animationCounter = 99;
    assertEquals(false, controller.loop);
    assertEquals(false, editor.refresh);
    ActionEvent loopButton = new ActionEvent(editor.loopButton,
        (int) System.currentTimeMillis(),
        editor.loopButton.getActionCommand());
    controller.buttonListener.actionPerformed(loopButton);
    ActionEvent timer = new ActionEvent(controller.tm, (int) System.currentTimeMillis(),
        controller.tm.getActionCommand());
    assertEquals(true, controller.loop);
    //animationcounter shouldn't be affected by clicking loop button until the max end time
    // is reached at 100
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    assertEquals(100, controller.animationCounter);
    editor.refresh = false;
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    //counter is reset because we clicked the loop button
    assertEquals(1, controller.animationCounter);
    controller.animationCounter = 99;
    ActionEvent noLoopButton = new ActionEvent(editor.noLoopButton,
        (int) System.currentTimeMillis(),
        editor.noLoopButton.getActionCommand());
    controller.buttonListener.actionPerformed(noLoopButton);
    editor.refresh = false;
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    assertEquals(100, controller.animationCounter);
    editor.refresh = false;
    controller.actionListener.actionPerformed(timer);
    assertEquals(true, editor.refresh);
    //if not loop timer should stop meaning, this is last iteration so animationcounter
    // wil be max animation end time + 1
    assertEquals(101, controller.animationCounter);
  }

  @Test
  public void AppropriateActionTakenOnDisableLoopButton() {
    //tests the actionListener for the Disable Loop Button to see if the correct action is taken
    // when the user wants to disable loop the animation
    ActionEvent loopButton = new ActionEvent(editor.loopButton,
        (int) System.currentTimeMillis(),
        editor.loopButton.getActionCommand());
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(loopButton);
    //only action that should be run is LoopButtonAction Class
    //enabled loop should be set now
    assertEquals(true, controller.loop);
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    ActionEvent noLoopButton = new ActionEvent(editor.noLoopButton,
        (int) System.currentTimeMillis(),
        editor.noLoopButton.getActionCommand());
    controller.buttonListener.actionPerformed(noLoopButton);
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
  }

  @Test
  public void ActionCompletedNoLoopButton() {
    //tests the completion of the action of clicking the No Loop Button to see if the correct
    // results if the user clicks the button(nothing should change)
    controller.animationCounter = 99;
    assertEquals(false, controller.loop);
    ActionEvent noLoopButton = new ActionEvent(editor.noLoopButton,
        (int) System.currentTimeMillis(),
        editor.noLoopButton.getActionCommand());
    controller.buttonListener.actionPerformed(noLoopButton);
    assertEquals(false, controller.loop);
    controller.buttonListener.actionPerformed(noLoopButton);
    assertEquals(false, controller.loop);
  }

  @Test
  public void AppropriateActionTakenOnAddShapeButton() {
    //tests the actionListener for the Add Shape Button to see if the correct action is taken when
    // the user wants to add a shape to the animation
    ActionEvent addShapeButton = new ActionEvent(editor.addShape,
        (int) System.currentTimeMillis(),
        editor.addShape.getActionCommand());
    editor.shapeTypeComboBox.setSelectedItem(ShapeType.RECTANGLE);
    editor.shapeName.setText("testRectangle");
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    controller.buttonListener.actionPerformed(addShapeButton);

    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    //only action that should be run is AddShapeButtonAction Class
    assertEquals(true, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
  }

  @Test
  public void ActionCompletedAddShapeButton() {
    //tests the completion of the action of clicking the Add Shape Button to see if the correct
    // results if the user clicks the button
    editor.shapeTypeComboBox.setSelectedItem(ShapeType.RECTANGLE);
    editor.shapeName.setText("test");
    assertEquals(2, editor.listOfShapes.getModel().getSize());
    assertEquals("R", editor.listOfShapes.getModel().getElementAt(0));
    assertEquals("C", editor.listOfShapes.getModel().getElementAt(1));
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
    ActionEvent addShapeButton = new ActionEvent(editor.addShape,
        (int) System.currentTimeMillis(),
        editor.addShape.getActionCommand());
    controller.buttonListener.actionPerformed(addShapeButton);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(true, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(true, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    assertEquals(3, editor.listOfShapes.getModel().getSize());
    assertEquals("R", editor.listOfShapes.getModel().getElementAt(0));
    assertEquals("C", editor.listOfShapes.getModel().getElementAt(1));
    assertEquals("test", editor.listOfShapes.getModel().getElementAt(2));

  }

  @Test
  public void AppropriateActionTakenOnDeleteShapeButton() {
    //tests the actionListener for the Delete Shape Button to see if the correct action is taken
    // when the user wants to delete a shape from the animation
    ActionEvent deleteShapeButton = new ActionEvent(editor.deleteShape,
        (int) System.currentTimeMillis(),
        editor.deleteShape.getActionCommand());
    editor.listOfShapes.setSelectedValue("R", false);
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(deleteShapeButton);

    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    //only action that should be run is DeleteShapeButtonAction Class
    assertEquals(true, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
  }

  @Test
  public void ActionCompletedDeleteShapeButton() {
    //tests the completion of the action of clicking the Delete Shape Button to see if the correct
    // results if the user clicks the button
    editor.listOfShapes.setSelectedValue("R", false);
    assertEquals(2, editor.listOfShapes.getModel().getSize());
    assertEquals("R", editor.listOfShapes.getModel().getElementAt(0));
    assertEquals("C", editor.listOfShapes.getModel().getElementAt(1));
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
    ActionEvent deleteShapeButton = new ActionEvent(editor.deleteShape,
        (int) System.currentTimeMillis(),
        editor.deleteShape.getActionCommand());
    controller.buttonListener.actionPerformed(deleteShapeButton);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(true, editor.updateShapeList);
    assertEquals(false, editor.updateKeyframelist);
    assertEquals(true, editor.removeShapeFromAnimation);
    assertEquals(false, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
    assertEquals(1, editor.listOfShapes.getModel().getSize());
    assertEquals("C", editor.listOfShapes.getModel().getElementAt(0));

  }

  @Test
  public void AppropriateActionTakenOnDeleteKeyframeButton() {
    //tests the actionListener for the Delete Keyframe Button to see if the correct action is taken
    // when the user wants to delete a keyframe from the animation
    ActionEvent deleteKeyframeButton = new ActionEvent(editor.deleteKeyframe,
        (int) System.currentTimeMillis(),
        editor.deleteKeyframe.getActionCommand());
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes,
        0, 1, true);
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(10, false);
    ListSelectionEvent selectKeyframe = new ListSelectionEvent(editor.shapeListOfKeyframes,
        0, 1, true);
    controller.buttonListener.actionPerformed(deleteKeyframeButton);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(true, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    //only action that should be run is DeleteKeyframeButtonAction Class
    assertEquals(true, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);

  }

  @Test
  public void ActionCompletedDeleteKeyframeButton() {
    //tests the completion of the action of clicking the Delete Keyframe Button to see if the
    // correct results if the user clicks the button
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes,
        0, 1, true);
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
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(10, false);
    ListSelectionEvent selectKeyframe = new ListSelectionEvent(editor.shapeListOfKeyframes,
        0, 1, true);
    ActionEvent removeKeyframe = new ActionEvent(editor.deleteKeyframe,
        (int) System.currentTimeMillis(),
        editor.deleteKeyframe.getActionCommand());
    controller.buttonListener.actionPerformed(removeKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(true, editor.refresh);
    assertEquals(true, editor.showKeyframesOfList);
    assertEquals(false, editor.showKeyframeProperties);
    assertEquals(false, editor.addShapeToAnimation);
    assertEquals(false, editor.addKeyframeToShape);
    assertEquals(false, editor.updateShapeList);
    assertEquals(true, editor.updateKeyframelist);
    assertEquals(false, editor.removeShapeFromAnimation);
    assertEquals(true, editor.removeKeyframe);
    assertEquals(false, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
  }

  @Test
  public void AppropriateActionTakenOnAddKeyframeButton() {
    //tests the actionListener for the Add Keyframe Button to see if the correct action is taken
    // when the user wants to add a keyframe to the animation
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
    ActionEvent addKeyframeButton = new ActionEvent(editor.addKeyframe,
        (int) System.currentTimeMillis(),
        editor.addKeyframe.getActionCommand());
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
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);
    controller.buttonListener.actionPerformed(addKeyframeButton);
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
    //only action that should be run is AddKeyframeButtonAction Class
    assertEquals(true, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);

  }

  @Test
  public void ActionCompletedAddKeyframeButton() {
    //tests the completion of the action of clicking the Add Keyframe Button to see if the
    // correct results if the user clicks the button
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
    assertEquals(true, editor.addKeyframeToShape);
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
    assertArrayEquals(new int[]{20, 100, 50, 100, 255, 100, 120, 0},
        editor.shapeKeyframes.get("R").get(110).getAttributes());

  }

  @Test
  public void AppropriateActionTakenOnEditKeyframeButton() {
    //tests the actionListener for the Edit Keyframe Button to see if the correct action is taken
    // when the user wants to edit a keyframe to the animation
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes, 0,
        2, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    controller.shapeListSelected = false;
    editor.xPositionEdit.setText("20");
    editor.yPositionEdit.setText("100");
    editor.widthEdit.setText("50");
    editor.heightEdit.setText("100");
    editor.colorREdit.setText("255");
    editor.colorGEdit.setText("100");
    editor.colorBEdit.setText("120");
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    editor.shapeListOfKeyframes.setSelectedValue(10, false);
    ActionEvent editKeyframeButton = new ActionEvent(editor.editKeyframe,
        (int) System.currentTimeMillis(),
        editor.editKeyframe.getActionCommand());
    controller.buttonListener.actionPerformed(editKeyframeButton);
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
    //only action that should be run is EditKeyframeButtonAction Class
    assertEquals(true, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);

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
    assertEquals(true, editor.modifyKeyframe);
    assertEquals(false, editor.changeSpeed);
    assertEquals(false, editor.loadAnimation);
    assertEquals(false, editor.saveAnimation);
    attribute = editor.shapeKeyframes.get("C").get(6);
    assertArrayEquals(new int[]{20, 100, 50, 100, 255, 100, 120, 0}, attribute.getAttributes());


  }

  @Test
  public void AppropriateActionTakenOnSelectingShapeFromShapeJList() {
    //tests the ListSelectionListener for the Shape JList to see if the correct action is taken
    // when the user wants to select a shape from the list
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes,
        0, 1, true);
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
    controller.shapeListSelectionListener.valueChanged(selectShape);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    //only action that should be run is ShapeListSelectionListener Class
    assertEquals(true, controller.shapeListSelected);
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

  }

  @Test
  public void ActionCompletedSelectingShapeFromList() {
    //tests the completion of the action of clicking Shape List to see if the
    // correct results if the user clicks a shape
    editor.listOfShapes.setSelectedValue("C", false);
    assertEquals(0, editor.shapeListOfKeyframes.getModel().getSize());
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
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes, 0,
        2, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    assertEquals(6, editor.shapeListOfKeyframes.getModel().getSize());
    assertEquals(6, (int) editor.shapeListOfKeyframes.getModel().getElementAt(0));
    assertEquals(20, (int) editor.shapeListOfKeyframes.getModel().getElementAt(1));
    assertEquals(50, (int) editor.shapeListOfKeyframes.getModel().getElementAt(2));
    assertEquals(70, (int) editor.shapeListOfKeyframes.getModel().getElementAt(3));
    assertEquals(80, (int) editor.shapeListOfKeyframes.getModel().getElementAt(4));
    assertEquals(100, (int) editor.shapeListOfKeyframes.getModel().getElementAt(5));
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(false, editor.refresh);
    assertEquals(true, editor.showKeyframesOfList);
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
  }

  @Test
  public void AppropriateActionTakenOnSelectingKeyframeFromKeyframeJList() {
    //tests the ListSelectionListener for the Keyframe JList to see if the correct action is taken
    // when the user wants to select a keyframe from the list
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes,
        0, 1, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(10, false);
    ListSelectionEvent selectKeyframe = new ListSelectionEvent(editor.shapeListOfKeyframes,
        0, 1, true);
    editor.showKeyframesOfList = false;
    controller.shapeListSelected = false;
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    controller.keyframeListSelectionListener.valueChanged(selectKeyframe);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    //only action that should be run is KeyframeSelectionListener Class
    assertEquals(true, controller.keyframeSelected);
    assertEquals(false, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);

  }

  @Test
  public void ActionCompletedSelectingKeyframe() {
    //tests the completion of the action of selecting a keyframe from the list to see if the
    // correct results if the user selects a keyframe
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
    editor.listOfShapes.setSelectedValue("R", false);
    ListSelectionEvent selectShape = new ListSelectionEvent(editor.listOfShapes,
        0, 1, true);
    controller.shapeListSelectionListener.valueChanged(selectShape);
    editor.shapeListOfKeyframes.setSelectedValue(10, false);
    ListSelectionEvent selectKeyframe = new ListSelectionEvent(editor.shapeListOfKeyframes,
        0, 1, true);
    editor.showKeyframesOfList = false;
    controller.keyframeListSelectionListener.valueChanged(selectKeyframe);
    assertEquals(false, editor.showError);
    assertEquals(false, editor.makeVisible);
    assertEquals(true, editor.refresh);
    assertEquals(false, editor.showKeyframesOfList);
    assertEquals(true, editor.showKeyframeProperties);
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
  }

  @Test
  public void AppropriateActionTakenOnSChangingSpeedSlider() {
    //tests the ChangeEventListener for the Speed Slider to see if the correct action is taken when
    // the user wants to change speed
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
    editor.speedSlider.setValue(10);
    ChangeEvent changeSpeed = new ChangeEvent(editor.speedSlider);
    controller.sliderChangeListener.stateChanged(changeSpeed);
    assertEquals(false, controller.loop);
    assertEquals(false, controller.clickedPlayButton);
    assertEquals(false, controller.play);
    assertEquals(false, controller.shapeListSelected);
    assertEquals(false, controller.keyframeSelected);
    //only action that should be run is ChangeSpeedListener Class
    assertEquals(true, controller.changeSpeed);
    assertEquals(false, controller.clickedRewindButton);
    assertEquals(false, controller.playingAnimation);
    assertEquals(false, controller.clickedAddShapeButton);
    assertEquals(false, controller.clickedDeleteButton);
    assertEquals(false, controller.clickedDeleteKeyframeButton);
    assertEquals(false, controller.clickedAddKeyframeButton);
    assertEquals(false, controller.clickedEditKeyframe);
    assertEquals(false, controller.clickedLoadAnimation);
    assertEquals(false, controller.clickedSaveAnimation);

  }

  @Test
  public void ActionCompletedChangingSpeedSlider() {
    //tests the completion of the action of changing the Speed to see if the
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
    assertEquals(1, Integer.parseInt(editor.speedLabel.getText()));
    assertEquals(1000, controller.tm.getDelay());
    editor.speedSlider.setValue(100);
    ChangeEvent changeEvent = new ChangeEvent(editor.speedSlider);
    controller.sliderChangeListener.stateChanged(changeEvent);
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
    assertEquals(true, editor.changeSpeed);
    assertEquals(false, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
    assertEquals(100, Integer.parseInt(editor.speedLabel.getText()));
    assertEquals(10, controller.tm.getDelay());
  }

  @Test
  public void AppropriateActionTakenOnSavingAnimation() {
    //tests the ButtonListener for the Save Animation to see if the correct action is taken when
    // the user wants to save animation
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    ActionEvent saveAnimation = new ActionEvent(editor.saveAnimationButton, (int)
        System.currentTimeMillis(), editor.saveAnimationButton.getActionCommand());
    controller.buttonListener.actionPerformed(saveAnimation);
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
    assertEquals(true, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);

  }

  @Test
  public void ActionCompletedSaveAnimation() {
    //tests the completion of the action of saving an animation to see if the
    // correct results if the user selects the button
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
    ActionEvent saveAnimation = new ActionEvent(editor.saveAnimationButton,
        (int) System.currentTimeMillis(), editor.saveAnimationButton.getActionCommand());
    controller.buttonListener.actionPerformed(saveAnimation);
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
    assertEquals(true, editor.saveAnimation);
    assertEquals(false, editor.loadAnimation);
  }

  @Test
  public void AppropriateActionTakenOnLoadingAnimation() {
    //tests the ButtonListener for the Load Animation to see if the correct action is taken when
    // the user wants to load animation
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(false, controller.clickedLoadAnimation);
    ActionEvent loadAnimation = new ActionEvent(editor.loadAnimationButton, (int)
        System.currentTimeMillis(), editor.loadAnimationButton.getActionCommand());
    controller.buttonListener.actionPerformed(loadAnimation);
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
    assertEquals(false, controller.clickedSaveAnimation);
    assertEquals(true, controller.clickedLoadAnimation);

  }

  @Test
  public void ActionCompletedLoadAnimation() {
    //tests the completion of the action of loading an animation to see if the
    // correct results if the user selects the button
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
    ActionEvent loadAnimation = new ActionEvent(editor.loadAnimationButton,
        (int) System.currentTimeMillis(), editor.loadAnimationButton.getActionCommand());
    controller.buttonListener.actionPerformed(loadAnimation);
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
    assertEquals(true, editor.loadAnimation);
  }

  @Test
  public void testPlayAnimation() {
    //tests the playing animation method. This method makes a gui visible if the view has one
    assertEquals(false, editor.makeVisible);
    assertEquals(false, controller.playingAnimation);
    controller.playAnimation();
    assertEquals(true, editor.makeVisible);
    assertEquals(true, controller.playingAnimation);
  }
}
