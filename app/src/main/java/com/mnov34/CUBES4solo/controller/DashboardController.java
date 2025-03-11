package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.user.LocalUserData;
import com.mnov34.CUBES4solo.util.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 10/03/2025
 **/
@FXMLController
public class DashboardController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private AnchorPane mainContentArea, sidebarContainer;

    @FXML
    private Button pinButton, adminButton;

    @FXML
    private FontIcon pinIcon;

    @FXML
    private Label currentViewTitle;

    private final SceneManager sceneManager;

    private boolean isPinned;

    @Autowired
    public DashboardController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneManager.setMainContentArea(mainContentArea);
        sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST);

        updatePinButtonState();
    }

    @FXML
    private void handlePinButton() {
        Stage primaryStage = sceneManager.getPrimaryStage();
        isPinned = !isPinned;
        primaryStage.setAlwaysOnTop(isPinned);
        LocalUserData.setProperty("windowPinned", String.valueOf(isPinned));
        updatePinButtonState();
    }

    @FXML
    private void handleAdminAccess() {
        sceneManager.loadView(SceneManager.SceneType.LOGIN);
    }

    private void updatePinButtonState() {

        String currentIcon = pinIcon.getIconCode().getDescription();

        if (isPinned & Objects.equals(currentIcon, "bi-pin-angle-fill")) {
            pinIcon.setIconLiteral("bi-pin-fill");
        } else {
            pinIcon.setIconLiteral("bi-pin-angle-fill");
        }
    }

    @FXML
    public void loadHome() {
        //sceneManager.loadView(SceneManager.SceneType.HOME);
    }

    @FXML
    public void loadEmployees() {
        sceneManager.loadView(SceneManager.SceneType.EMPLOYEE_LIST);
    }

    @FXML
    public void loadSites() {
        sceneManager.loadView(SceneManager.SceneType.SITES);
    }

    @FXML
    public void loadDepartments() {
        sceneManager.loadView(SceneManager.SceneType.DEPARTMENTS);
    }
}
