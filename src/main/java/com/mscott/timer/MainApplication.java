package com.mscott.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
