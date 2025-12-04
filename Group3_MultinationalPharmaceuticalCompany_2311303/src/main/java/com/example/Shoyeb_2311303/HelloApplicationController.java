package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplicationController implements Initializable {

    @FXML
    private TextField inputField;

    @FXML
    private Button clickButton;

    @FXML
    private Label outputLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HelloApplicationController Loaded Successfully!");
    }

    @FXML
    private void handleClick() {
        String text = inputField.getText();

        if (text.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Input");
            alert.setContentText("Please enter something!");
            alert.show();
            return;
        }

        outputLabel.setText("You typed: " + text);
    }
}
