package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.api.ApiClient;
import com.mnov34.CUBES4solo.model.Department;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
@FXMLController
@Slf4j
public class DepartmentFXController implements Initializable {

    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department, String> departmentIdColumn, departmentNameColumn;
    @FXML
    private TableColumn<Department, Void> editColumn, deleteColumn;
    @FXML
    private Button addButton;

    @FXML
    private TextField departmentNameField;

    private Department selectedDepartment;

    private final ApiClient departmentApiService;
    private final ObservableList<Department> departments = FXCollections.observableArrayList();

    @Autowired
    public DepartmentFXController(Retrofit retrofit) {
        this.departmentApiService = retrofit.create(ApiClient.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        refreshData();
        setupCellFactories();
    }

    private void setupTableColumns() {
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        departmentTable.setItems(departments);
    }

    private void setupCellFactories() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Department department = getTableView().getItems().get(getIndex());
                    populateFormForEditing(department);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Department department = getTableView().getItems().get(getIndex());
                    handleDeleteDepartment(department);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });
    }

    private void populateFormForEditing(Department department) {
        selectedDepartment = department;
        Platform.runLater(() -> {
            departmentNameField.setText(department.getDepartmentName());
            addButton.setText("Update");
        });
    }

    private void refreshData() {
        departmentApiService.getServices().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Platform.runLater(() -> {
                        departments.setAll(response.body());
                        selectedDepartment = null;
                        addButton.setText("Ajouter");
                    });
                } else {
                    showError("Failed to delete department", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                showError("Failed to load department", t);
            }
        });
    }


    @FXML
    private void handleAddDepartment() {
        if (selectedDepartment == null) {
            addDepartment();
        } else {
            updateDepartment();
        }
    }

    private void addDepartment() {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(departmentNameField.getText());

        departmentApiService.createService(newDepartment).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        refreshData();
                        clearForm();
                    });
                } else {
                    showError("Failed to add department", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                showError("Failed to add department", t);
            }
        });
    }

    private void updateDepartment() {
        if (selectedDepartment == null) return;
        selectedDepartment.setDepartmentName(departmentNameField.getText());

        departmentApiService.updateService(selectedDepartment.getId(), selectedDepartment).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        refreshData();
                        clearForm();
                    });
                } else {
                    showError("Failed to update department", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                showError("Failed to update department", t);
            }
        });
    }

    private void handleDeleteDepartment(Department department) {
        departmentApiService.deleteService(department.getId()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> refreshData());
                } else {
                    showError("Failed to delete department", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showError("Failed to delete department", t);
            }
        });
    }

    private void clearForm() {
        Platform.runLater(() -> {
            departmentNameField.clear();
            selectedDepartment = null;
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
