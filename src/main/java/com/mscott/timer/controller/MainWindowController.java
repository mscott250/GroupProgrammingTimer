package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import com.mscott.timer.TurnEventListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {

    private static int MILLISECONDS_IN_MINUTE = 60000;

    @FXML
    private TextField nameInput;
    @FXML
    private ListView<String> nameList;
    @FXML
    private Button addPersonButton;
    @FXML
    private Button removePersonButton;
    @FXML
    private TextField minutesInput;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;

    private TextFormatter<Integer> minutesInputFormatter = new TextFormatter<>(new IntegerStringConverter());

    private GroupList groupList;

    private Stage aboutWindowStage;

    private TurnEventListener turnEventListener;

    @Autowired
    public MainWindowController(GroupList groupList, Stage aboutWindowStage) {
        this.groupList = groupList;
        this.aboutWindowStage = aboutWindowStage;
    }

    public void setTurnEventListener(TurnEventListener turnEventListener) {
        this.turnEventListener = turnEventListener;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(groupList.getGroupNames());
        minutesInput.setTextFormatter(minutesInputFormatter);
        stopButton.setDisable(true);
    }

    @FXML
    private void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotEmpty(newName)) {
            groupList.addPerson(newName);
            nameInput.clear();
            nameInput.requestFocus();
        }
    }

    @FXML
    private void removePersonActionHandler(ActionEvent event) {

        String selectedName = nameList.getFocusModel().getFocusedItem();
        if (StringUtils.isNotEmpty(selectedName)) {
            groupList.removePerson(selectedName);
        }
    }

    @FXML
    private void startActionHandler(ActionEvent event) {

        long turnLengthInMs = getTurnLengthInMs();
        if (turnLengthInMs > 0 && !groupList.isEmpty()) {

            disableConfigurationComponents();

            turnEventListener.startTurns(turnLengthInMs);
        }
    }

    @FXML
    private void stopActionHandler(ActionEvent event) {

        turnEventListener.stopTurns();

        enableConfigurationComponents();
    }

    @FXML
    private void resetActionHandler(ActionEvent event) {

        turnEventListener.stopTurns();

        clearConfigurationComponents();
        enableConfigurationComponents();
    }

    @FXML
    private void closeActionHandler(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void aboutActionHandler(ActionEvent event) {
        aboutWindowStage.show();
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
