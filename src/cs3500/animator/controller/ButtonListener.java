package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * {@code ButtonListener} class that is implemented in {@link AnimationControllerImpl}. This class
 * is used to handle the actions of different events dealing with buttons.
 */
public final class ButtonListener implements ActionListener {

  Map<String, Runnable> buttonClickedActions;

  /**
   * Set the map for Button clicked events.
   */

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {

      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}