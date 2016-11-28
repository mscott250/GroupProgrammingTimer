package com.mscott.timer;

import com.mscott.timer.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // TODO: think this could be cleaner
        // stop the scheduler to ensure any threads are cancelled so we can exit cleanly
        ((MainWindowController) fxmlLoader.getController()).stopScheduler();
    }
}
