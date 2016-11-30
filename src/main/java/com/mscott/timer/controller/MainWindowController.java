package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import com.mscott.timer.scheduling.TurnOverListener;
import com.mscott.timer.scheduling.TurnScheduler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, TurnOverListener, TurnChangedListener {

    private static int MILLISECONDS_IN_MINUTE = 6000;

    private GroupList groupList;

    private TurnScheduler turnScheduler;

    private ChangeTurnWindowController changeTurnWindowController;

    private TextFormatter<Integer> minutesInputFormatter = new TextFormatter<>(new IntegerStringConverter());

    private Stage changeTurnStage;

    public TextField nameInput;
    public ListView<String> nameList;
    public Button addPersonButton;
    public Button removePersonButton;

    public TextField minutesInput;

    public Button startButton;
    public Button stopButton;

    public MainWindowController(GroupList groupList, TurnScheduler turnScheduler, ChangeTurnWindowController changeTurnWindowController) {
        this.groupList = groupList;
        this.turnScheduler = turnScheduler;
        this.changeTurnWindowController = changeTurnWindowController;
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

        long timerDelay = getTimerDelay();
        if (timerDelay > 0 && !groupList.isEmpty()) {
            disableConfigurationComponents();
            switchDeveloper();
        }
    }

    public void stopActionHandler(ActionEvent event) {
        turnScheduler.stopTimer();
        enableConfigurationComponents();
    }

    public void resetActionHandler(ActionEvent event) {

        turnScheduler.stopTimer();
        groupList.clear();

        minutesInput.clear();

        enableConfigurationComponents();
    }

    @PostConstruct
    public void setup() throws IOException {
        turnScheduler.setTurnOverListener(this);
        changeTurnWindowController.setTurnChangedListener(this);
        changeTurnStage = createChangeTurnWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(groupList.getGroupNames());
        minutesInput.setTextFormatter(minutesInputFormatter);
        stopButton.setDisable(true);
    }

    @Override
    public void turnOver() {
        switchDeveloper();
    }

    @Override
    public void turnChanged() {
        changeTurnStage.close();
        turnScheduler.startTimer(getTimerDelay());
    }

    private void enableConfigurationComponents() {

        nameInput.setDisable(false);
        minutesInput.setDisable(false);
        addPersonButton.setDisable(false);
        removePersonButton.setDisable(false);
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    private void disableConfigurationComponents() {

        nameInput.setDisable(true);
        minutesInput.setDisable(true);
        addPersonButton.setDisable(true);
        removePersonButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    private long getTimerDelay() {
        Integer minutes = minutesInputFormatter.getValue();
        if (minutes == null || minutes < 1) {
            return -1;
        } else {
            return minutes * MILLISECONDS_IN_MINUTE;
        }
    }

    private Stage createChangeTurnWindow() throws IOException {
        // TODO: think this could be cleaner
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/changeTurnWindow.fxml"));
        fxmlLoader.setController(changeTurnWindowController);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setScene(new Scene(root));

        return stage;
    }

    private void switchDeveloper() {
        // need to ensure we only update the UI on the platform thread
        Platform.runLater(() -> {
            changeTurnWindowController.displayNextPerson();
            changeTurnStage.show();
        });
    }
}
