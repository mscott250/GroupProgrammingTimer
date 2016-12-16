package com.mscott.timer;

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
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
