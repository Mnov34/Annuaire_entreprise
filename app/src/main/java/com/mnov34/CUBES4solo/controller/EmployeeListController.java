package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.api.ApiClient;
import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.model.Site;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Slf4j
@FXMLController
public class EmployeeListController implements Initializable {
    @FXML
    private TableView<Employee> userTable;
    @FXML
    private TableColumn<Employee, String> lastNameColumn, firstNameColumn, phoneColumn, mobilePhoneColumn, emailColumn, siteColumn, departmentColumn;
    @FXML
    private TableColumn<Employee, Void> editColumn, deleteColumn;

    @FXML
    private ComboBox<String> siteFilter, serviceFilter;
    @FXML
    private ComboBox<Department> departmentCombo;
    @FXML
    private ComboBox<Site> siteCombo;
    @FXML
    private Button refreshButton, addButton;

    @FXML
    private TextField searchField, lastNameField, firstNameField, mobilePhoneField, emailField, phoneField;

    private final ApiClient employeeApiService;
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    private Employee selectedEmployee;

    public EmployeeListController(Retrofit retrofit) {
        this.employeeApiService = retrofit.create(ApiClient.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        loadFilterOptions();
        refreshData();
        setupCellFactories();
        setupListeners();
    }

    @FXML
    private void handleRefresh() {
        refreshData();
    }

    @FXML
    private void handleAddEmployee() {
        if (selectedEmployee == null) {
            addEmployee();
        } else updateEmployee();
    }

    private void setupListeners() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> refreshData());
        siteFilter.valueProperty().addListener((obs, oldVal, newVal) -> refreshData());
        serviceFilter.valueProperty().addListener((obs, oldVal, newVal) -> refreshData());
    }

    private void setupTableColumns() {
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mobilePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("site"));
        userTable.setItems(employees);
    }

    private void setupCellFactories() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Employee employee = getTableView().getItems().get(getIndex());
                    populateFormForEditing(employee);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });

        // Delete button column
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Employee employee = getTableView().getItems().get(getIndex());
                    handleDeleteEmployee(employee);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });
    }

    private void loadFilterOptions() {
        employeeApiService.getSites().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> siteNames = response.body().stream()
                            .map(Site::getSite)
                            .collect(Collectors.toList());
                    Platform.runLater(() -> siteFilter.getItems().setAll(siteNames));
                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                showError("Failed to load sites", t);
            }
        });

        employeeApiService.getServices().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> serviceNames = response.body().stream()
                            .map(Department::getDepartmentName)
                            .collect(Collectors.toList());
                    Platform.runLater(() -> serviceFilter.getItems().setAll(serviceNames));
                }
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                showError("Failed to load services", t);
            }
        });
    }

    private void refreshData() {
        employeeApiService.getEmployees(
                searchField.getText(),
                siteFilter.getValue(),
                serviceFilter.getValue()
        ).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Platform.runLater(() -> {
                        employees.setAll(response.body());
                        selectedEmployee = null;
                        addButton.setText("Ajouter");
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                showError("Failed to load employees", t);
            }
        });
    }

    private void populateFormForEditing(Employee employee) {
        selectedEmployee = employee;
        Platform.runLater(() -> {
            firstNameField.setText(employee.getFirstName());
            lastNameField.setText(employee.getLastName());
            phoneField.setText(employee.getPhone());
            mobilePhoneField.setText(employee.getMobilePhone());
            emailField.setText(employee.getEmail());
            siteCombo.setValue(employee.getSite());
            departmentCombo.setValue(employee.getDepartment());
            addButton.setText("Update");
        });
    }

    private void addEmployee() {

        Employee newEmployee = new Employee(
                null,
                firstNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                mobilePhoneField.getText(),
                emailField.getText(),
                departmentCombo.getValue(),
                siteCombo.getValue()
        );

        employeeApiService.createEmployee(newEmployee).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        refreshData();
                        clearForm();
                    });
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                showError("Failed to add employee", t);
            }
        });
    }

    private void updateEmployee() {
        if (selectedEmployee == null) return;
        Employee updatedEmployee = new Employee(
                selectedEmployee.getId(),
                firstNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                mobilePhoneField.getText(),
                emailField.getText(),
                departmentCombo.getValue(),
                siteCombo.getValue()
        );

        employeeApiService.updateEmployee(selectedEmployee.getId(), updatedEmployee)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if (response.isSuccessful()) {
                            Platform.runLater(() -> {
                                refreshData();
                                clearForm();
                            });
                        } else {
                            showError("Failed to update employee", new RuntimeException("Response code: " + response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        showError("Failed to update employee", t);
                    }
                });
    }

    private void handleDeleteEmployee(Employee employee) {
        employeeApiService.deleteEmployee(employee.getId()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> refreshData());
                } else {
                    showError("Failed to delete employee", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showError("Failed to delete employee", t);
            }
        });
    }

    private void clearForm() {
        Platform.runLater(() -> {
            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
            mobilePhoneField.clear();
            emailField.clear();
            siteCombo.setValue(null);
            departmentCombo.setValue(null);
            selectedEmployee = null;
            addButton.setText("Ajouter");
        });
    }

    private void showError(String message, Throwable throwable) {
        if (Platform.isFxApplicationThread()) {
            log.error(message, throwable);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(message);
            alert.setContentText(throwable.getMessage());
            alert.showAndWait();
        } else {
            Platform.runLater(() -> showError(message, throwable));
        }
    }
}