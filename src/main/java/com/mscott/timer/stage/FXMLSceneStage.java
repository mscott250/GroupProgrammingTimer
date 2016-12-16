package com.mscott.timer.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLSceneStage extends Stage {

    private Object controller;

    public FXMLSceneStage(String uri, Object controller) {
        super();

        this.controller = controller;

        loadScene(uri);
    }

    public Object getController() {
        return controller;
    }

    private void loadScene(String uri) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(uri));
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
