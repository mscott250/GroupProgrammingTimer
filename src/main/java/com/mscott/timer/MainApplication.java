package com.mscott.timer;

import com.mscott.timer.config.BaseConfig;
import com.mscott.timer.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApplication extends Application {

    private AbstractApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        context = new AnnotationConfigApplicationContext(BaseConfig.class);

        MainWindowController mainWindowController = context.getBean(MainWindowController.class);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainWindow.fxml"));
        fxmlLoader.setController(mainWindowController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
