package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import com.mscott.timer.TurnEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeTurnWindowController {

    @FXML
    private Label nextPersonLabel;

    private GroupList groupList;

    private TurnEventListener turnEventListener;

    @Autowired
    public ChangeTurnWindowController(GroupList groupList) {
        this.groupList = groupList;
    }

    public void setTurnEventListener(TurnEventListener turnEventListener) {
        this.turnEventListener = turnEventListener;
    }

    public void displayNextPerson() {
        nextPersonLabel.setText(groupList.getNextPerson() + " please sit at the keyboard");
    }

    @FXML
    private void readyActionHandler(ActionEvent event) {
        turnEventListener.startNextTurn();
    }
}
