package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import com.mscott.timer.TurnEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {

    private static int MILLISECONDS_IN_MINUTE = 60000;

    public TextField nameInput;
    public ListView<String> nameList;
    public Button addPersonButton;
    public Button removePersonButton;

    public TextField minutesInput;

    public Button startButton;
    public Button stopButton;
    public Button resetButton;

    private TextFormatter<Integer> minutesInputFormatter = new TextFormatter<>(new IntegerStringConverter());

    private GroupList groupList;

    private TurnEventListener turnEventListener;

    @Autowired
    public MainWindowController(GroupList groupList) {
        this.groupList = groupList;
    }

    public void setTurnEventListener(TurnEventListener turnEventListener) {
        this.turnEventListener = turnEventListener;
    }

    public void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotEmpty(newName)) {
            groupList.addPerson(newName);
            nameInput.clear();
            nameInput.requestFocus();
        }
    }

    public void removePersonActionHandler(ActionEvent event) {

        String selectedName = nameList.getFocusModel().getFocusedItem();
        if (StringUtils.isNotEmpty(selectedName)) {
            groupList.removePerson(selectedName);
        }
    }

    public void startActionHandler(ActionEvent event) {

        long turnLengthInMs = getTurnLengthInMs();
        if (turnLengthInMs > 0 && !groupList.isEmpty()) {

            disableConfigurationComponents();

            turnEventListener.startTurns(turnLengthInMs);
        }
    }

    public void stopActionHandler(ActionEvent event) {

        turnEventListener.stopTurns();

        enableConfigurationComponents();
    }

    public void resetActionHandler(ActionEvent event) {

        turnEventListener.stopTurns();

        clearConfigurationComponents();
        enableConfigurationComponents();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(groupList.getGroupNames());
        minutesInput.setTextFormatter(minutesInputFormatter);
        stopButton.setDisable(true);
    }

    private void enableConfigurationComponents() {

        nameInput.setDisable(false);
        minutesInput.setDisable(false);
        addPersonButton.setDisable(false);
        removePersonButton.setDisable(false);
        startButton.setDisable(false);
        stopButton.setDisable(true);
        resetButton.setDisable(false);
    }

    private void disableConfigurationComponents() {

        nameInput.setDisable(true);
        minutesInput.setDisable(true);
        addPersonButton.setDisable(true);
        removePersonButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resetButton.setDisable(true);
    }

    private void clearConfigurationComponents() {

        groupList.clear();
        minutesInput.clear();
    }

    private long getTurnLengthInMs() {
        Integer minutes = minutesInputFormatter.getValue();
        if (minutes == null || minutes < 1) {
            return -1;
        } else {
            return minutes * MILLISECONDS_IN_MINUTE;
        }
    }
}
