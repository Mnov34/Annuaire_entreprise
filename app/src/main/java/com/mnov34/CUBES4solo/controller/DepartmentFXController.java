package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
@FXMLController
public class DepartmentFXController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private void handleAddDepartment() {
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
