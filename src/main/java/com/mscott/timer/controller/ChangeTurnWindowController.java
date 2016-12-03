package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import com.mscott.timer.TurnEventListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ChangeTurnWindowController {

    public Label nextPersonLabel;

    private GroupList groupList;

    private TurnEventListener turnEventListener;

    public ChangeTurnWindowController(GroupList groupList) {
        this.groupList = groupList;
    }

    public void setTurnEventListener(TurnEventListener turnEventListener) {
        this.turnEventListener = turnEventListener;
    }

    public void readyActionHandler(ActionEvent event) {
        turnEventListener.startNextTurn();
    }

    public void displayNextPerson() {
        nextPersonLabel.setText(groupList.getNextPerson() + " please sit at the keyboard");
    }
}
