package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ChangeTurnWindowController {

    public Label nextPersonLabel;

    private GroupList groupList;

    private ReadyForTurnListener readyForTurnListener;

    public ChangeTurnWindowController(GroupList groupList) {
        this.groupList = groupList;
    }

    public void setReadyForTurnListener(ReadyForTurnListener readyForTurnListener) {
        this.readyForTurnListener = readyForTurnListener;
    }

    public void readyActionHandler(ActionEvent event) {
        readyForTurnListener.readyForTurn();
    }

    public void displayNextPerson() {
        nextPersonLabel.setText(groupList.getNextPerson() + " please sit at the keyboard");
    }
}
