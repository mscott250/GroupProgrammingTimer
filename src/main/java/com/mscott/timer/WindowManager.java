package com.mscott.timer;

import com.mscott.timer.controller.ChangeTurnWindowController;
import com.mscott.timer.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {

    private MainWindowController mainWindowController;
    private ChangeTurnWindowController changeTurnWindowController;

    private Stage changeTurnWindowStage;

    public WindowManager(MainWindowController mainWindowController,
                         ChangeTurnWindowController changeTurnWindowController) {
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
}
