package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.api.ApiClientFactory;
import com.mnov34.CUBES4solo.api.EmployeeApiClient;
import com.mnov34.CUBES4solo.dto.EmployeeDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public class EmployeeListController implements Initializable {

    // FXML-injected UI components
    @FXML
    private TableView<EmployeeDto> userTable;
    @FXML
    private TableColumn<EmployeeDto, String> lastNameColumn, firstNameColumn, phoneColumn, siteColumn;
    @FXML
    private TableColumn<EmployeeDto, Void> editColumn, deleteColumn;
    @FXML
    private Button adminButton, addButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> siteFilter, serviceFilter, userComboBox;
    @FXML
    private TextField lastNameField, firstNameField, phoneField, siteField;

    private final EmployeeApiClient employeeApi = ApiClientFactory.createEmployeeApiClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configure TableView columns with the expected property names in EmployeeDto.
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("site"));

        loadEmployees();
    }

    /**
     * Calls the employee API endpoint asynchronously and updates the TableView with the response.
     */
    private void loadEmployees() {
        Call<List<EmployeeDto>> call = employeeApi.getEmployees();
        call.enqueue(new Callback<List<EmployeeDto>>() {
            @Override
            public void onResponse(Call<List<EmployeeDto>> call, Response<List<EmployeeDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EmployeeDto> employees = response.body();
                    Platform.runLater(() -> userTable.getItems().setAll(employees));
                } else {
                    System.err.println("Failed to load employees: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeDto>> call, Throwable t) {
                System.err.println("Error calling the employee API:");
                t.printStackTrace();
            }
        });
    }

    @FXML
    private void handleAdminPanel() {
        System.out.println("Admin Panel clicked.");
    }

    @FXML
    private void handleSearch() {
        System.out.println("Search clicked. Query: " + searchField.getText());
    }

    @FXML
    private void handleRefresh() {
        loadEmployees();
    }
}
