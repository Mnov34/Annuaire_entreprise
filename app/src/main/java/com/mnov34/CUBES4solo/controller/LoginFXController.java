package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.api.ApiClient;
import com.mnov34.CUBES4solo.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
@FXMLController
@Slf4j
public class LoginFXController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final SceneManager sceneManager;
    private final ApiClient apiClient;

    @Autowired
    public LoginFXController(SceneManager sceneManager, Retrofit retrofit) {
        this.sceneManager = sceneManager;
        this.apiClient = retrofit.create(ApiClient.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String credentials = username + ":" + password;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        apiClient.login(basicAuth).enqueue(new Callback<String>() {
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        messageLabel.setText("Login successful!");
                        sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST);
                    });
                } else {
                    Platform.runLater(() -> messageLabel.setText("Invalid credentials."));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                log.error("Login error:{}", t.getMessage());
                Platform.runLater(() -> messageLabel.setText("Login error: " + t.getMessage()));
            }
        });
    }

    @FXML
    private void handleBack() {
        sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST);
    }
}
