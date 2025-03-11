package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
@FXMLController
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final SceneManager sceneManager;

    @Autowired
    public LoginController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Hardcoded credentials
        if ("admin".equals(username) && "password".equals(password)) {
            messageLabel.setText("Login successful!");
            Platform.runLater(() -> sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST));
        } else {
            messageLabel.setText("Invalid credentials. Please try again.");
        }
    }

    @FXML
    private void handleBack() {
        sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST);
    }
}
