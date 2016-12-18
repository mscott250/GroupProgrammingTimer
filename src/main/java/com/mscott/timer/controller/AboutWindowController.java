package com.mscott.timer.controller;

import com.mscott.timer.HostServicesWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AboutWindowController implements Initializable {

    @FXML
    private Label versionValueLabel;
    @FXML
    private Hyperlink homePageHyperlink;

    private HostServicesWrapper hostServicesWrapper;

    private String applicationVersion;

    @Autowired
    public AboutWindowController(HostServicesWrapper hostServicesWrapper,
                                 @Value("${application.version}")
                                 String applicationVersion) {
        this.hostServicesWrapper = hostServicesWrapper;
        this.applicationVersion = applicationVersion;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionValueLabel.setText(applicationVersion);
    }

    @FXML
    private void clickHomePageLinkActionHandler(ActionEvent event) {
        hostServicesWrapper.showDocument(homePageHyperlink.getText());
    }

    @FXML
    private void closeActionHandler(ActionEvent event) {
        // nasty chaining, but it means we don't need the window being passed in to us though
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }
}
