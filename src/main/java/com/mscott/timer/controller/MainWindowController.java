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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, TurnOverListener, TurnChangedListener {

    private static int MILLISECONDS_IN_MINUTE = 60000;

    private GroupList groupList = new GroupList();

    private TurnScheduler turnScheduler = new TurnScheduler(this);

    private TextFormatter<Integer> minutesInputFormatter = new TextFormatter<>(new IntegerStringConverter());

    public TextField nameInput;
    public ListView<String> nameList;

    public TextField minutesInput;

    public void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotEmpty(newName)) {
            groupList.addPerson(newName);
        }
    }

    public void startActionHandler(ActionEvent event) {

        long timerDelay = getTimerDelay();
        if (timerDelay > 0 && !groupList.isEmpty()) {
            turnScheduler.startTimer(timerDelay);
        }
    }

    public void resetActionHandler(ActionEvent event) {
        turnScheduler.stopTimer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(groupList.getGroupNames());
        minutesInput.setTextFormatter(minutesInputFormatter);
    }

    @Override
    public void turnOver() {
        switchDeveloper();
    }

    @Override
    public void turnChanged() {
        turnScheduler.startTimer(getTimerDelay());
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/changeTurnWindow.fxml"));
        fxmlLoader.setController(new ChangeTurnWindowController(groupList, this));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Change Turn");
        stage.setScene(new Scene(root, 600, 400));

        return stage;
    }

    private void switchDeveloper() {
        // need to ensure we only update the UI on the platform thread
        Platform.runLater(() -> {
            try {
                Stage stage = createChangeTurnWindow();
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
