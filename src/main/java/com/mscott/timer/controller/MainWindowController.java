package com.mscott.timer.controller;

import com.mscott.timer.TurnEventListener;
import com.mscott.timer.group.GroupList;
import com.mscott.timer.settings.TimerConfiguration;
import com.mscott.timer.settings.TimerConfigurationFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {

    private static final long MILLISECONDS_IN_MINUTE = 60000;

    private static final FileChooser.ExtensionFilter JSON_EXTENSION_FILTER = new FileChooser.ExtensionFilter(
            "JSON Files",
            "*.json");

    public TextField nameInput;
    public ListView<String> nameList;
    public Button addPersonButton;
    public Button removePersonButton;

    public TextField minutesInput;

    public Button startButton;
    public Button stopButton;
    public Button resetButton;

    public MenuItem loadConfigurationMenuItem;
    public MenuItem saveConfigurationMenuItem;

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

    public void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotBlank(newName)) {
            groupList.addNewPerson(newName);
            nameInput.clear();
            nameInput.requestFocus();
        }
    }

    public void removePersonActionHandler(ActionEvent event) {

        String selectedName = nameList.getFocusModel().getFocusedItem();
        if (StringUtils.isNotBlank(selectedName)) {
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

    public void loadConfigurationActionHandler(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(JSON_EXTENSION_FILTER);
        fileChooser.setInitialDirectory(SystemUtils.getUserHome());
        fileChooser.setTitle("Load Timer Configuration");

        File chosenFile = fileChooser.showOpenDialog(getSceneWindow());
        if (chosenFile != null) {

            TimerConfigurationFile timerConfigurationFile = new TimerConfigurationFile(chosenFile);
            try {
                TimerConfiguration timerConfiguration = timerConfigurationFile.loadConfiguration();
                setTimerConfiguration(timerConfiguration);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to load configuration: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void saveConfigurationActionHandler(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(JSON_EXTENSION_FILTER);
        fileChooser.setInitialDirectory(SystemUtils.getUserHome());
        fileChooser.setTitle("Save Timer Configuration");

        File chosenFile = fileChooser.showSaveDialog(getSceneWindow());
        if (chosenFile != null) {

            TimerConfigurationFile timerConfigurationFile = new TimerConfigurationFile(chosenFile);
            TimerConfiguration timerConfiguration = getTimerConfiguration();
            try {
                timerConfigurationFile.saveConfiguration(timerConfiguration);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to save configuration: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void closeActionHandler(ActionEvent event) {
        Platform.exit();
    }

    public void aboutActionHandler(ActionEvent event) {
        aboutWindowStage.show();
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
        loadConfigurationMenuItem.setDisable(false);
        saveConfigurationMenuItem.setDisable(false);
    }

    private void disableConfigurationComponents() {

        nameInput.setDisable(true);
        minutesInput.setDisable(true);
        addPersonButton.setDisable(true);
        removePersonButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resetButton.setDisable(true);
        loadConfigurationMenuItem.setDisable(true);
        saveConfigurationMenuItem.setDisable(true);
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

    private TimerConfiguration getTimerConfiguration() {

        TimerConfiguration timerConfiguration = new TimerConfiguration();
        timerConfiguration.setTurnLengthInMinutes(minutesInputFormatter.getValue());
        timerConfiguration.setNames(new ArrayList<>(groupList.getGroupNames()));
        return timerConfiguration;
    }

    private void setTimerConfiguration(TimerConfiguration timerConfiguration) {

        minutesInputFormatter.setValue(timerConfiguration.getTurnLengthInMinutes());
        groupList.clear();
        groupList.addAllPersons(timerConfiguration.getNames());
    }

    private Window getSceneWindow() {
        // this could be done on any javafx control, but will only return non-null after the initialize method has run
        return nameInput.getScene().getWindow();
    }
}
