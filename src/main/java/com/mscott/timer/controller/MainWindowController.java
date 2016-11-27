package com.mscott.timer.controller;

import com.mscott.timer.scheduling.TurnOverListener;
import com.mscott.timer.scheduling.TurnScheduler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, TurnOverListener {

    private static int MILLISECONDS_IN_MINUTE = 60000;

    private ObservableList<String> developerNames = FXCollections.observableArrayList();

    private TextFormatter<Integer> minutesInputFormatter = new TextFormatter<>(new IntegerStringConverter());

    private TurnScheduler turnScheduler = new TurnScheduler(this);

    public TextField nameInput;
    public ListView<String> nameList;

    public TextField minutesInput;

    public void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotEmpty(newName) && !developerNames.contains(newName)) {
            developerNames.add(newName);
        }
    }

    public void startActionHandler(ActionEvent event) {

        long timerDelay = getTimerDelay();
        if (timerDelay > 0 && !developerNames.isEmpty()) {
            turnScheduler.startTimer(timerDelay);
        }
    }

    public void resetActionHandler(ActionEvent event) {
        turnScheduler.stopTimer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(developerNames);
        minutesInput.setTextFormatter(minutesInputFormatter);
    }

    @Override
    public void turnOver() {
        switchDeveloper();
    }

    private long getTimerDelay() {
        Integer minutes = minutesInputFormatter.getValue();
        if (minutes == null || minutes < 1) {
            return -1;
        } else {
            return minutes * MILLISECONDS_IN_MINUTE;
        }
    }

    private void switchDeveloper() {
        // need to ensure we only update the UI on the platform thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // TODO: bring up a popup etc
            }
        });
    }
}
