package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // ==== FXML Components (If you add them in FXML later, variables are ready) ====
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;


    // ===== Initialize Method =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HelloController initialized!");
    }


    // ====== Example Login Action ======
    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.equals("admin") && pass.equals("1234")) {
            statusLabel.setText("Login Successful!");
        } else {
            statusLabel.setText("Invalid username or password!");
        }
    }
}
