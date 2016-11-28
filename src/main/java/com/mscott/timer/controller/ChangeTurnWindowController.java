package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeTurnWindowController implements Initializable {

    public Label nextPersonLabel;

    private GroupList groupList;

    private TurnChangedListener turnChangedListener;

    public ChangeTurnWindowController(GroupList groupList, TurnChangedListener turnChangedListener) {
        this.groupList = groupList;
        this.turnChangedListener = turnChangedListener;
    }

    public void doneActionHandler(ActionEvent event) {
        turnChangedListener.turnChanged();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextPersonLabel.setText(groupList.getNextPerson() + " please sit at the keyboard");
    }
}
