package com.mscott.timer.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private ObservableList<String> developerNames = FXCollections.observableArrayList();

    public TextField nameInput;
    public ListView<String> nameList;

    public TextField minutesInput;

    public void addPersonActionHandler(ActionEvent event) {

        String newName = nameInput.getText();
        if (StringUtils.isNotEmpty(newName) && !developerNames.contains(newName)) {
            developerNames.add(newName);
        }
    }

    public void startActionHandler(ActionEvent event) {

    }

    public void resetActionHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameList.setItems(developerNames);
        minutesInput.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }
}
