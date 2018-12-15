package com.mscott.timer;

import com.mscott.timer.scheduling.TurnScheduler;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApplication extends Application {

    private AbstractApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        context = new AnnotationConfigApplicationContext("com.mscott.timer");

        HostServicesWrapper hostServicesWrapper = context.getBean(HostServicesWrapper.class);
        hostServicesWrapper.setHostServices(getHostServices());

        WindowManager windowManager = context.getBean(WindowManager.class);

        windowManager.showMainWindow();
    }

    @Override
    public void stop() throws Exception {
        // stop the turn scheduler to ensure any child threads started by the timer are terminated before exiting,
        // if this isn't done then the application window closes but the process continues running until its killed
        TurnScheduler turnScheduler = context.getBean(TurnScheduler.class);
        turnScheduler.stopTurn();

        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
