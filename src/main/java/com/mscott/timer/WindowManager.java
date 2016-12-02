package com.mscott.timer;

import com.mscott.timer.controller.ChangeTurnWindowController;
import com.mscott.timer.controller.MainWindowController;
import com.mscott.timer.controller.ReadyForTurnListener;
import com.mscott.timer.scheduling.TurnOverListener;
import com.mscott.timer.scheduling.TurnScheduler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager implements ReadyForTurnListener, TurnOverListener {

    private TurnScheduler turnScheduler;

    private MainWindowController mainWindowController;
    private ChangeTurnWindowController changeTurnWindowController;

    private Stage changeTurnWindowStage;

    public WindowManager(TurnScheduler turnScheduler,
                         MainWindowController mainWindowController,
                         ChangeTurnWindowController changeTurnWindowController) {

        this.turnScheduler = turnScheduler;
        this.mainWindowController = mainWindowController;
        this.changeTurnWindowController = changeTurnWindowController;

        initChangeTurnStage();
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
            // TODO: do this properly
            System.err.println(e.getMessage());
        }
    }

    public void showMainWindow(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainWindow.fxml"));
        fxmlLoader.setController(mainWindowController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showChangeTurnWindow() {
        changeTurnWindowStage.show();
    }

    @Override
    public void turnOver() {
        switchDeveloper();
    }

    @Override
    public void readyForTurn() {
        changeTurnWindowStage.close();
        turnScheduler.startTimer();
    }

    private void switchDeveloper() {
        // need to ensure we only update the UI on the platform thread
        Platform.runLater(() -> {
            changeTurnWindowController.displayNextPerson();
            changeTurnWindowStage.show();
        });
    }
}
