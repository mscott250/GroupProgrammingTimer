package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ChangeTurnWindowController {

    public Label nextPersonLabel;

    private GroupList groupList;

    private TurnChangedListener turnChangedListener;

    public ChangeTurnWindowController(GroupList groupList) {
        this.groupList = groupList;
    }

    public void setTurnChangedListener(TurnChangedListener turnChangedListener) {
        this.turnChangedListener = turnChangedListener;
    }

    public void readyActionHandler(ActionEvent event) {
        turnChangedListener.turnChanged();
    }

    public void displayNextPerson() {
        nextPersonLabel.setText(groupList.getNextPerson() + " please sit at the keyboard");
    }
}
