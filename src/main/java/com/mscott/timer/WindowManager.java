package com.mscott.timer;

import com.mscott.timer.controller.ChangeTurnWindowController;
import com.mscott.timer.controller.MainWindowController;
import com.mscott.timer.scheduling.TurnScheduler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager implements TurnEventListener {

    private TurnScheduler turnScheduler;

    private MainWindowController mainWindowController;
    private ChangeTurnWindowController changeTurnWindowController;

    private Stage mainWindowStage;
    private Stage changeTurnWindowStage;

    private long turnLengthInMs = -1;

    public WindowManager(TurnScheduler turnScheduler,
                         MainWindowController mainWindowController,
                         ChangeTurnWindowController changeTurnWindowController) {

        this.turnScheduler = turnScheduler;
        this.mainWindowController = mainWindowController;
        this.changeTurnWindowController = changeTurnWindowController;

        mainWindowController.setTurnEventListener(this);
        changeTurnWindowController.setTurnEventListener(this);

        initChangeTurnStage();
    }

    public void showMainWindow(Stage primaryStage) throws IOException {

        mainWindowStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainWindow.fxml"));
        fxmlLoader.setController(mainWindowController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void startTurns(long turnLengthInMs) {

        this.turnLengthInMs = turnLengthInMs;

        hideMainWindow();
        // ask the first person to start their turn
        switchPerson();
    }

    @Override
    public void stopTurns() {
        turnScheduler.stopTurn();
    }

    @Override
    public void startNextTurn() {
        changeTurnWindowStage.close();
        turnScheduler.startTurn(this::switchPerson, turnLengthInMs);
    }

    private void initChangeTurnStage() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/changeTurnWindow.fxml"));
            fxmlLoader.setController(changeTurnWindowController);
            Parent root = fxmlLoader.load();

            changeTurnWindowStage = new Stage();
            changeTurnWindowStage.setFullScreen(true);
            changeTurnWindowStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void hideMainWindow() {
        Platform.runLater(() -> mainWindowStage.setIconified(true));
    }

    private void switchPerson() {
        Platform.runLater(() -> {
            changeTurnWindowController.displayNextPerson();
            changeTurnWindowStage.show();
        });
    }
}
